package prog.exceptions;

public class HeadNotFoundException extends RuntimeException{
    public HeadNotFoundException(String name){
        super(name + "doesn't have this head");
    }
}
