package test.model;

import model.MovingGame;
import model.Profile;
import model.ShrinkingGame;
import model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AimTrainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovingGameTest extends GameTest {
    @BeforeEach
    public void beforeEach() {
        game = new MovingGame(new Profile("test"), 10000);
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
        float positionXHolder = game.getTarget().getPosX();
        float positionYHolder = game.getTarget().getPosY();
        assertEquals(positionXHolder, game.getTarget().getPosX());
        assertEquals(positionYHolder, game.getTarget().getPosY());

        super.tickTest();

        assertEquals(positionXHolder + MovingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED, game.getTarget().getPosX());
        assertEquals(positionYHolder + MovingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED, game.getTarget().getPosY());
    }

    @Test
    public void tickTargetOffScreenTest() {
        Target oldTarget = game.getTarget();
        float nextXPos = game.getTarget().getPosX() + MovingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED;
        float nextYPos = game.getTarget().getPosY() + MovingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED;
        boolean outOfLeftBounds = nextXPos <= 2 * game.getTarget().getRadius();
        boolean outOfRightBounds = nextXPos >= AimTrainer.getFrameWidth() - 2 * game.getTarget().getRadius();
        boolean outOfBotBounds = nextYPos <= 2 * game.getTarget().getRadius();
        boolean outOfTopBounds = nextYPos >= AimTrainer.getFrameHeight() - 2 * game.getTarget().getRadius();
        while (!(outOfLeftBounds || outOfRightBounds || outOfBotBounds || outOfTopBounds)) {
            assertEquals(oldTarget, game.getTarget());
            nextXPos = game.getTarget().getPosX() + MovingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED;
            nextYPos = game.getTarget().getPosY() + MovingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED;
            outOfLeftBounds = nextXPos <= 2 * game.getTarget().getRadius();
            outOfRightBounds = nextXPos >= AimTrainer.getFrameWidth() - 2 * game.getTarget().getRadius();
            outOfBotBounds = nextYPos <= 2 * game.getTarget().getRadius();
            outOfTopBounds = nextYPos >= AimTrainer.getFrameHeight() - 2 * game.getTarget().getRadius();
            game.tick();
        }
        game.tick();
        assertNotEquals(oldTarget, game.getTarget());
    }

    @Test
    public void onClickMidMove() {
        float oldSpeedFactorX = MovingGame.getSpeedFactorX();
        float oldSpeedFactorY = MovingGame.getSpeedFactorY();
        game.tick();
        game.tick();
        game.onClick(-1, -1); // -1, -1 represents a test value for a miss
        assertNotEquals(oldSpeedFactorX, MovingGame.getSpeedFactorX());
        assertNotEquals(oldSpeedFactorY, MovingGame.getSpeedFactorY());
    }
}
