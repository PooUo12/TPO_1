package prog.domain;

import lombok.Getter;
import prog.exceptions.CreationException;
import prog.exceptions.HeadNotFoundException;
import prog.exceptions.ImpossibleMoveException;

import java.util.List;

@Getter
public class Human {
    private final String name;
    private final List<Head> heads;
    private Emotions emotion = Emotions.normal;
    private final Location location;

    public Human(String name, List<Head> heads, Location location) throws ImpossibleMoveException {
        if (heads.isEmpty()){
            throw new CreationException("have zero heads");
        }
        this.name = name;
        this.heads = heads;
        this.location = location;
    }

    public void sitOnChair() throws ImpossibleMoveException{
        if (location.getRoom().equals("Street")){
            throw new ImpossibleMoveException("sit on chair in the street");
        }
        location.setFurniture("Chair");
    }

    public void enterNewRoom(String room){
        location.setRoom(room);
    }

    public void changeEmotion(Emotions emotion) throws ImpossibleMoveException{
        if (emotion.equals(Emotions.chilling) && location.getFurniture() == null){
            throw new ImpossibleMoveException("get chilling without chair");
        }
        this.emotion = emotion;
    }

    public void jawFall(String name) throws HeadNotFoundException {
        for (Head head: heads){
            if (head.getName().equals(name)){
                head.jawFall();
                return;
            }
        }
        throw new HeadNotFoundException(this.name);
    }

    public void teethScratch(String name) throws HeadNotFoundException {
        for (Head head: heads){
            if (head.getName().equals(name)){
                head.teethScratch();
                return;
            }
        }
        throw new HeadNotFoundException(this.name);
    }
}
