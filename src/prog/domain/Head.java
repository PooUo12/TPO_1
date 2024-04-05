package prog.domain;

import lombok.Getter;
import lombok.Setter;
import prog.exceptions.ImpossibleMoveException;
import prog.exceptions.NegativeNumberException;
import prog.exceptions.NotEnoughTeethException;

@Getter
public class Mouth {
    private final int teeth;
    private final boolean chelust;
    @Setter
    private boolean dirt;
    @Setter
    private Conditions chelustCondition;

    public Mouth(int teeth, boolean chelust) throws NotEnoughTeethException, NegativeNumberException {
        if (teeth == 0 && chelust){
            throw new NotEnoughTeethException("have chelust");
        }
        if (teeth < 0){
            throw new NegativeNumberException("teeth");
        }
        if (chelust){
            setChelustCondition(Conditions.normal);
        }
        if (teeth > 0){
            setDirt(true);
        }
        this.chelust = chelust;
        this.teeth = teeth;
    }

    public void chelustFall() throws ImpossibleMoveException {
        if (chelust && chelustCondition == Conditions.normal){
            setChelustCondition(Conditions.fell);
        } else {
            throw new ImpossibleMoveException("fall chelust");
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
