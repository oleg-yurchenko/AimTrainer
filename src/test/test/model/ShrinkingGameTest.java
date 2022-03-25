package test.model;

import model.Profile;
import model.ShrinkingGame;
import model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShrinkingGameTest extends GameTest {
    @BeforeEach
    public void beforeEach() {
        game = new ShrinkingGame(new Profile("test"), 10000);
        game.gameStart(); // generates target
    }

    @Override
    @Test
    public void constructorTest() {
        super.constructorTest();
        assertEquals(10000, game.getTime());
    }

    @Override
    @Test
    public void tickTest() {
        float radiusHolder = game.getTarget().getRadius();
        assertEquals(radiusHolder, game.getTarget().getRadius());

        super.tickTest();

        assertEquals(radiusHolder - ShrinkingGame.RADIUS_REDUCTION, game.getTarget().getRadius());
    }

    @Test
    public void tickTargetExpiredTest() {
        Target oldTarget = game.getTarget();
        while (game.getTarget().getRadius() > ShrinkingGame.RADIUS_REDUCTION) {
            assertEquals(oldTarget, game.getTarget());
            game.tick();
        }
        game.tick();
        assertNotEquals(oldTarget, game.getTarget());
    }

    @Test
    public void onClickMidShrinkTest() {
        float desiredRadius = game.getTarget().getRadius();
        game.tick();
        game.tick();
        assertEquals(Math.round(desiredRadius - ShrinkingGame.RADIUS_REDUCTION * 2), Math.round(game.getTarget().getRadius()));
        game.onClick(-1, -1); // -1, -1 represents a test value for a miss
        assertEquals(desiredRadius, game.getTarget().getRadius());
    }
}
