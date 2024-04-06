package prog.exceptions;

public class CreationException extends RuntimeException {
    public CreationException(String part){
        super("You can't have " + part);
    }
}
