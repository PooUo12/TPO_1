package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import prog.domain.Emotions;
import prog.domain.Head;
import prog.domain.Human;
import prog.domain.Location;
import prog.exceptions.CreationException;
import prog.exceptions.HeadNotFoundException;
import prog.exceptions.ImpossibleMoveException;
import prog.exceptions.NotEnoughTeethException;

import java.util.ArrayList;
import java.util.List;

public class DomainTest {

    private Human mainHero;
    private Human twoHeadHero;

    @Before
    public void SetUp() {
        List<Head> mainHeroHeads = new ArrayList<>();
        mainHeroHeads.add(new Head("main", 30, true, 2));

        List<Head> creatureHeads = new ArrayList<>();
        creatureHeads.add(new Head("left", 0, false, 2));
        creatureHeads.add(new Head("right", 30, true, 2));

        mainHero = new Human("Oleg", mainHeroHeads, new Location("Street", null));
        twoHeadHero = new Human("Ryan", creatureHeads, new Location("Room", null));

    }

    @Test
    public void wrongCreationTest() {
        // Нельзя иметь -1 зубов
        Assertions.assertThrows(CreationException.class, () -> new Head("test", -1, false, 0));
        // Нельзя иметь -1 глаз
        Assertions.assertThrows(CreationException.class, () -> new Head("test", 30, false, -1));
        // Нельзя иметь челюсть при 0 зубах
        Assertions.assertThrows(NotEnoughTeethException.class, () -> new Head("test", 0, true, 0));
        // Нельзя иметь 0 голов
        Assertions.assertThrows(CreationException.class, () -> new Human("test", new ArrayList<>(), new Location("Street", null)));
        // проверки с нулл
        Assertions.assertAll(() -> new Human(null, mainHero.getHeads(), null));
        Assertions.assertAll(() -> new Head(null, 0, false, 0));
    }

    @Test
    public void wrongMoveTest() {
        // Нельзя сесть на стул на улице
        Assertions.assertThrows(ImpossibleMoveException.class, mainHero::sitOnChair);
        // Нельзя развалиться без стула
        Assertions.assertThrows(ImpossibleMoveException.class, () -> mainHero.changeEmotion(Emotions.chilling));
        // Нельзя ковыряться в зубах, когда их нет
        Assertions.assertThrows(ImpossibleMoveException.class, () -> twoHeadHero.teethScratch("left"));
        // Нельзя поковыряться в зубах, которые не в твоей голове
        Assertions.assertThrows(HeadNotFoundException.class, () -> twoHeadHero.teethScratch("main"));
        // Челюсть не может отвиснуть, если уже отвисла
        mainHero.jawFall("main");
        Assertions.assertThrows(ImpossibleMoveException.class, () -> mainHero.jawFall("main"));
    }

    @Test
    public void mainScenarioTest() {

        mainHero.changeEmotion(Emotions.nervous);
        Assertions.assertEquals(Emotions.nervous, mainHero.getEmotion());

        mainHero.enterNewRoom("Room");
        Assertions.assertEquals("Room", mainHero.getLocation().getRoom());

        mainHero.changeEmotion(Emotions.nervous);
        Assertions.assertEquals(Emotions.nervous, mainHero.getEmotion());

        twoHeadHero.sitOnChair();
        Assertions.assertEquals("Chair", twoHeadHero.getLocation().getFurniture());

        twoHeadHero.changeEmotion(Emotions.smiling);
        Assertions.assertEquals(Emotions.smiling, twoHeadHero.getEmotion());

        twoHeadHero.changeEmotion(Emotions.chilling);
        Assertions.assertEquals(Emotions.chilling, twoHeadHero.getEmotion());

        Assertions.assertAll(() -> twoHeadHero.teethScratch("right"));
        Assertions.assertAll(() -> mainHero.jawFall("main"));
    }
}
