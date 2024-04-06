package prog.domain;

import lombok.Getter;
import lombok.Setter;
import prog.exceptions.ImpossibleMoveException;
import prog.exceptions.CreationException;
import prog.exceptions.NotEnoughTeethException;

@Getter
public class Head {
    private final String name;
    private final int teeth;
    private final boolean jaw;
    private final int eyes;
    @Setter
    private boolean dirt;
    @Setter
    private Conditions jawCondition;

    public Head(String name, int teeth, boolean jaw, int eyes) throws NotEnoughTeethException, CreationException {
        if (teeth == 0 && jaw){
            throw new NotEnoughTeethException("have jaw");
        }
        if (teeth < 0){
            throw new CreationException("negative amount of teeth");
        }
        if (eyes < 0){
            throw new CreationException("negative amount of eyes");
        }
        if (jaw){
            setJawCondition(Conditions.normal);
        }
        if (teeth > 0){
            setDirt(true);
        }
        this.name = name;
        this.eyes = eyes;
        this.jaw = jaw;
        this.teeth = teeth;
    }

    public void jawFall() throws ImpossibleMoveException {
        if (jaw && jawCondition == Conditions.normal){
            setJawCondition(Conditions.fell);
        } else {
            throw new ImpossibleMoveException("fall jaw");
        }
    }

    public void teethScratch() throws ImpossibleMoveException{
        if (teeth > 0){
            setDirt(false);
        } else {
            throw new ImpossibleMoveException("scratch teeth");
        }
    }

    private enum Conditions{
        normal,
        fell
    }
}
