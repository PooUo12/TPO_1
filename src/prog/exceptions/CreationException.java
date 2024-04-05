package prog.exceptions;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(String part){
        super("Amount of " + part + "can't be negative");
    }
}
