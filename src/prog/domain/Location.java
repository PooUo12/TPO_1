package prog.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String room;
    private String furniture;

    public Location(String room, String furniture){
        this.room = room;
        this.furniture = furniture;
    }
}
