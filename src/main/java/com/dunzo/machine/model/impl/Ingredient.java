package com.dunzo.machine.model.impl;

/**
 * Ingredient details
 * @author shahid
 *
 */
public class Ingredient {

  private Integer amount = 0;

  private String name;

  public Ingredient(Integer amount, String name) {
    this.amount = amount;
    this.name = name;
  }

  public void updateAmount(Integer amount) {
    this.amount += amount;
  }

  public Integer getQuantity() {
    return this.amount;
  }

  public String getName() {
    return this.name;
  }

}
