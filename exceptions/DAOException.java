package exceptions;

@SuppressWarnings("serial")
public class DAOException extends Exception {

  public DAOException(String error) {
    super(error);
  }

  public DAOException(String error, Throwable cause) {
    super(error, cause);
  }

}
