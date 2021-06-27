package com.dunzo.machine.service;

import com.dunzo.machine.model.Beverage;
import com.dunzo.machine.model.BeverageVO;
import com.dunzo.machine.model.impl.Ingredient;

import java.util.List;

/**
 * Coffee Machine Service with the Apis
 * 
 * @author Shahid
 *
 */
public interface CoffeeMachine {

  /**
   * Refill the machine with the given ingredient details Use below command when preparing from terminal e.g. 
   * Refill hot_water 100
   * 
   * @param ingredientList
   * @return
   */
  public String refill(List<Ingredient> ingredientList);

  /**
   * Prepare Beverages and return the list. Use below command when preparing from terminal e.g. 
   * Prepare hot_water 100 hot_milk 50 ginger_syrup 10 ...
   * 
   * @param beverageVO
   * @return
   */
  public List<Beverage> prepareBeverages(List<BeverageVO> beverageVO);

  /**
   * Act as indicator. Use below command and press enter.
   * indicator
   * 
   * @return
   */
  public List<Ingredient> showIngedients();

}
