package com.dunzo.machine.service;

import com.dunzo.machine.model.Beverage;
import com.dunzo.machine.model.BeverageVO;
import com.dunzo.machine.model.Outlet;
import com.dunzo.machine.model.impl.Ingredient;
import com.dunzo.machine.store.Storage;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Coffe Machine Service
 * @author Shahid
 *
 */
public class CoffeMachineImpl implements CoffeeMachine{

  private Storage<Ingredient> storage;

  private Outlet outlets;

  public CoffeMachineImpl(Storage<Ingredient> storage, Outlet outlets) {
    this.storage = storage;
    this.outlets = outlets;
  }

  @Override
  public String refill(List<Ingredient> ingredientList) {
    ingredientList.stream().forEach(ing -> storage.addOrUpdateIngredient(ing.getName(), ing.getQuantity()));
    return "Successfully refilled";
  }

  @Override
  public List<Beverage> prepareBeverages(List<BeverageVO> beverageVO) {
    List<CompletableFuture<Beverage>> beveragesFuture = beverageVO.stream().map(vo -> outlets.pour(vo, storage))
        .collect(Collectors.toList());
    return beveragesFuture.stream().map(CompletableFuture::join).filter(Objects::nonNull).collect(Collectors.toList());
  }
  
  @Override
  public List<Ingredient> showIngedients(){
    return storage.getAllIngredients().stream().collect(Collectors.toList());
  }

}
