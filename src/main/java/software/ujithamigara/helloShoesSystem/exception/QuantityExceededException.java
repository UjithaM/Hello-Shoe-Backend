package software.ujithamigara.helloShoesSystem.exception;

public class QuantityExceededException extends RuntimeException {
    public QuantityExceededException(String message) {
        super(message);
    }
}