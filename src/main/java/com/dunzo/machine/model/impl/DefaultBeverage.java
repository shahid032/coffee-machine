package com.dunzo.machine.model.impl;

import com.dunzo.machine.model.Beverage;

public class DefaultBeverage implements Beverage{
  
  private String name;

  public DefaultBeverage(String name) {
    super();
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String getDescription() {
    return "Beverage "+this.getName()+" prepared with Ingredient";
  }

}
