package za.co.bank.atm.app.exception;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
public class ApplicationException extends Exception{

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

