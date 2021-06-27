package com.dunzo.machine.exception;

public class StorageException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public static final String EXCEPTION_NAME = "StorageException";

  public StorageException(String message) {
    super(message);
  }

  public StorageException() {
    super("Unable to show the data for unknown reasons, please contact tech team for support");
  }

  public StorageException(String message, Throwable e) {
    super(message, e);
  }

  public StorageException(Throwable e) {
    super(e);
  }

}
