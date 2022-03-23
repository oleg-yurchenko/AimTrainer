package test.model;

import model.DefaultGame;
import model.Profile;
import model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultGameTest extends GameTest {
    @BeforeEach
    public void beforeEach() {
        game = new DefaultGame(new Profile("test"), 30);
    }

    @Override
    public void constructorTest() {
        super.constructorTest();
        assertEquals(30, game.getTime());
    }

}
