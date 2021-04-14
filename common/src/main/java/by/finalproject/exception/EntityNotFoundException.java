package by.finalproject.exception;

public class EntityNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -6201562974266615960L;

  public EntityNotFoundException() {
    super();
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
