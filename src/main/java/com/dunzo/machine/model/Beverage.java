package com.dunzo.machine.model;

/**
 * Beverage Interface
 * @author Shahid
 *
 */
public interface Beverage {

  public default String getDescription() {
    return "Unknown beverage";
  }

  public String getName();

}
