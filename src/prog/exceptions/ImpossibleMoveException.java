package prog.exceptions;

public class ImpossibleMoveException extends RuntimeException{
    public ImpossibleMoveException(String move){
        super("You can't " + move);
    }
}
