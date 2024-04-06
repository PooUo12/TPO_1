package prog.exceptions;

public class NotEnoughTeethException extends RuntimeException{
    public NotEnoughTeethException(String action){
        super("Impossible to " + action + " ,not enough teeth");
    }
}
