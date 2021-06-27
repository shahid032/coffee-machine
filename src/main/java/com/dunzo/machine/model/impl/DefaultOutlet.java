package com.dunzo.machine.model.impl;

import com.dunzo.machine.exception.UserDefinedException;
import com.dunzo.machine.model.Beverage;
import com.dunzo.machine.model.BeverageVO;
import com.dunzo.machine.model.Outlet;
import com.dunzo.machine.store.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultOutlet implements Outlet {

  private ExecutorService taskExecutor;

  public DefaultOutlet(int maxoutlets) {
    taskExecutor = Executors.newFixedThreadPool(1);
  }

  @Override
  public CompletableFuture<Beverage> pour(BeverageVO beverageVO, Storage<Ingredient> storage) {
    return CompletableFuture.supplyAsync(() -> pourBeverage(beverageVO, storage), taskExecutor);
  }

  private Beverage pourBeverage(BeverageVO beverageVO, Storage<Ingredient> storage) {
    List<Ingredient> ingredientAdded = new ArrayList<>();
    try {
      Beverage beverage = new DefaultBeverage(beverageVO.getName());
      for (Ingredient ingredient : beverageVO.getIngredientList()) {
        beverage = new DefaultDecorator(beverage, storage).decorate(ingredient);
        ingredientAdded.add(ingredient);
      }
      return beverage;
    } catch (UserDefinedException e) {
      ingredientAdded.stream().forEach(ing -> storage.addOrUpdateIngredient(ing.getName(), ing.getQuantity()));
      return new DefaultBeverage(e.getMessage());
    }
  }

  @Override
  public void shutDown() {
    if (!taskExecutor.isShutdown()) {
      taskExecutor.shutdown();
    }
  }

}
