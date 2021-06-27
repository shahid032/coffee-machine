package com.dunzo.machine.exception;

public class UserDefinedException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final String EXCEPTION_NAME = "UserDefinedException";

  public UserDefinedException(String message) {
    super(message);
  }

  public UserDefinedException() {
    super("Unable to show the data for unknown reasons, please contact tech team for support");
  }

  public UserDefinedException(String message, Throwable e) {
    super(message, e);
  }

  public UserDefinedException(Throwable e) {
    super(e);
  }
}
