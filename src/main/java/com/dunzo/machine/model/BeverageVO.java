package com.dunzo.machine.model;

import com.dunzo.machine.model.impl.Ingredient;

import java.util.List;

/**
 * Model Object to take input from the user.
 * @author Shahid
 *
 */
public class BeverageVO {

  String name;

  List<Ingredient> ingredientList;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Ingredient> getIngredientList() {
    return ingredientList;
  }

  public void setIngredientList(List<Ingredient> ingredientList) {
    this.ingredientList = ingredientList;
  }

}
