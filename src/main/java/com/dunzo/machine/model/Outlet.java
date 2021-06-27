package com.dunzo.machine.model;

import com.dunzo.machine.model.impl.Ingredient;
import com.dunzo.machine.store.Storage;

import java.util.concurrent.CompletableFuture;

/**
 * 
 * @author Shahid
 *
 */
public interface Outlet {

  /**
   * Pour the beverage from one of the available outlets.
   * 
   * @param beverageVO
   * @param storage
   * @return
   */
  public CompletableFuture<Beverage> pour(BeverageVO beverageVO, Storage<Ingredient> storage);

  /**
   * shutdown the outlet when the program terminates.
   */
  public void shutDown();

}
