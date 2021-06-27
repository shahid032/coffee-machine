package com.dunzo.machine.store.impl;

import com.dunzo.machine.exception.StorageException;
import com.dunzo.machine.model.impl.Ingredient;
import com.dunzo.machine.store.Storage;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IngredientMapStorage implements Storage<Ingredient> {

  private Map<String, Ingredient> mapStorage;

  private int maxCapacity;

  public IngredientMapStorage(Integer maxCapacity) {
    this.mapStorage = Collections.synchronizedMap(new HashMap<String, Ingredient>());
    
    this.maxCapacity = maxCapacity;
  }

  @Override
  public Ingredient addOrUpdateIngredient(String name, Integer amount) {
    if (mapStorage.size() == maxCapacity) {
      throw new StorageException("Storage is full ingredient can't be added.");
    }
    Ingredient ingredient = new Ingredient(amount, name);
    if (mapStorage.containsKey(name)) {
      ingredient = mapStorage.get(name);
      ingredient.updateAmount(amount);
    } else {
      mapStorage.put(name, ingredient);
    }
    return ingredient;
  }

  @Override
  public Ingredient removeIngredient(String name) {
    if (!mapStorage.containsKey(name)) {
      throw new StorageException("No ingredient existing for this name");
    }
    return mapStorage.remove(name);
  }

  @Override
  public Ingredient getIngredient(String name) {
    if (!mapStorage.containsKey(name)) {
      throw new StorageException("No ingredient existing for this name");
    }
    return mapStorage.get(name);
  }

  @Override
  public boolean contains(String name) {
    return mapStorage.containsKey(name);
  }

  @Override
  public Collection<Ingredient> getAllIngredients() {
    return mapStorage.values();
  }

}
