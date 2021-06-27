package com.dunzo.machine.model.impl;

import com.dunzo.machine.exception.StorageException;
import com.dunzo.machine.exception.UserDefinedException;
import com.dunzo.machine.model.Beverage;
import com.dunzo.machine.model.BeverageDecorator;
import com.dunzo.machine.store.Storage;

public class DefaultDecorator implements BeverageDecorator {

  private Beverage beverage;

  private Storage<Ingredient> storage;

  private String description;

  public DefaultDecorator(Beverage beverage, Storage<Ingredient> storage) {
    this.beverage = beverage;
    this.storage = storage;
  }

  @Override
  public Beverage decorate(Ingredient ingredient) {
    if (!storage.contains(ingredient.getName())) {
      throw new UserDefinedException(
          beverage.getName() + " cannot be prepared because " + ingredient.getName() + " is not available");
    }
    try {
      Ingredient ing = storage.getIngredient(ingredient.getName());
      if (ing.getQuantity() < ingredient.getQuantity()) {
        throw new UserDefinedException(
            beverage.getName() + " cannot be prepared because " + ingredient.getName() + " is not available");
      }
      storage.addOrUpdateIngredient(ingredient.getName(), -ingredient.getQuantity());
      this.description = ingredient.getName();
      return this;
    } catch (StorageException e) {
      throw new UserDefinedException(
          beverage.getName() + " cannot be prepared because " + ingredient.getName() + " is not available");
    }
  }

  @Override
  public String getDescription() {
    return beverage.getDescription() + " : " + description;
  }

  @Override
  public String getName() {
    return beverage.getName();
  }

}
