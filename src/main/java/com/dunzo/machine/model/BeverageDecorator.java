package com.dunzo.machine.model;

import com.dunzo.machine.model.impl.Ingredient;

/**
 * Beverage decorator to decorate different ingredients.
 * @author Shahid
 *
 */
public interface BeverageDecorator extends Beverage{
  
  @Override
  public String getDescription();

  /**
   * Decorate the given ingredient and update the storage
   * @param ingredient
   * @return - decorated beverage
   */
  public Beverage decorate(Ingredient ingredient);

}
