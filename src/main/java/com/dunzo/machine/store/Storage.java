package com.dunzo.machine.store;

import java.util.Collection;

/**
 * 
 * In memory storage for our ingredients in the machine.
 * @author Shahid
 *
 * @param <T> - Ingredient
 */
public interface Storage<T> {

  /**
   * Add or Update the ingredients in the machine.
   * @param name
   * @param amount
   * @return
   */
  public T addOrUpdateIngredient(String name, Integer amount);

  /**
   * Remove the ingredient from the machine.
   * @param name
   * @return
   */
  public T removeIngredient(String name);
  
  public T getIngredient(String name);

  public boolean contains(String name);

  public Collection<T> getAllIngredients();

}
