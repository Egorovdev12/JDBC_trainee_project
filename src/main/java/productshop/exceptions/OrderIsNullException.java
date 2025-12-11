package productshop.exceptions;

public class OrderIsNullException extends RuntimeException {
    public OrderIsNullException(String message) {
        super(message);
    }
}
