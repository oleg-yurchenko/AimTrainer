package test.model;

import exceptions.GameOverException;
import model.Game;
import model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class GameTest {
    Game game;

    @Test
    public void constructorTest() {
        assertNotEquals(null, game.getTarget());
        assertNotEquals(null, game.getProfile());
    }

    @Test
    public void tickTest() {
        int timeHolder = game.getTime();
        assertEquals(timeHolder, game.getTime());
        game.tick();
        assertEquals(timeHolder - 1, game.getTime());
    }

    @Test
    public void gameOverTest() {
        while (game.getTime() > 1) {
            game.tick();
        }
        try {
            game.tick();
            fail("Should throw GameOverException");
        } catch (GameOverException e) {
            assertEquals(0, game.getTime());
            // should end here
        }
    }

    @Test
    public void onClickMissTest() {
        game.gameStart();
        Target targetHolder = game.getTarget();
        game.onClick(-1, -1); // -1 and -1 are never displayed, therefore this should always be a miss
        assertNotEquals(targetHolder, game.getTarget());
    }

    @Test
    public void onClickHitTest() {
        game.gameStart();
        Target targetHolder = game.getTarget();
        // the getRadius is added to make sure it's not on the boundaries of the circle, but inside the circle
        game.onClick((int)(targetHolder.getPosX() + targetHolder.getRadius()/2), (int)(targetHolder.getPosY() + targetHolder.getRadius()/2));
        assertNotEquals(targetHolder, game.getTarget());
    }

    @Test
    public void gameStartTest() {
        game.gameStart();
        assertNotEquals(null, game.getTarget());
    }
}
