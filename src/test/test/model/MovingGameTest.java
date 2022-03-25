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
    MovingGame movingGame;

    @BeforeEach
    public void beforeEach() {
        game = new MovingGame(new Profile("test"), 10000);
        game.gameStart(); // generates target
        movingGame = (MovingGame) game;
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

        assertEquals(positionXHolder + movingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED, game.getTarget().getPosX());
        assertEquals(positionYHolder + movingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED, game.getTarget().getPosY());
    }


    /*
        boolean outOfLeftBounds = nextXPos <= 2 * game.getTarget().getRadius();
        boolean outOfRightBounds = nextXPos >= AimTrainer.getFrameWidth() - 2 * game.getTarget().getRadius();
        boolean outOfBotBounds = nextYPos <= 2 * game.getTarget().getRadius();
        boolean outOfTopBounds = nextYPos >= AimTrainer.getFrameHeight() - 2 * game.getTarget().getRadius();
         */

    @Test
    public void tickTargetOffScreenLeftTest() {
        Target oldTarget = game.getTarget();
        movingGame.setSpeedFactorX(-1.0f);
        movingGame.setSpeedFactorY(0.0f);
        float nextXPos = game.getTarget().getPosX() + movingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED;
        while (!(nextXPos <= 2 * game.getTarget().getRadius())) {
            assertEquals(oldTarget, game.getTarget());
            nextXPos = game.getTarget().getPosX() + movingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED;
            game.tick();
        }
        game.tick();
        assertNotEquals(oldTarget, game.getTarget());
    }

    @Test
    public void tickTargetOffScreenRightTest() {
        Target oldTarget = game.getTarget();
        movingGame.setSpeedFactorX(1.0f);
        movingGame.setSpeedFactorY(0.0f);
        float nextXPos = game.getTarget().getPosX() + movingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED;
        while (!(nextXPos >= AimTrainer.getFrameWidth() - 2 * game.getTarget().getRadius())) {
            assertEquals(oldTarget, game.getTarget());
            nextXPos = game.getTarget().getPosX() + movingGame.getSpeedFactorX() * MovingGame.MOVE_SPEED;
            game.tick();
        }
        game.tick();
        assertNotEquals(oldTarget, game.getTarget());
    }

    @Test
    public void tickTargetOffScreenUpTest() {
        Target oldTarget = game.getTarget();
        movingGame.setSpeedFactorX(0.0f);
        movingGame.setSpeedFactorY(-1.0f);
        float nextYPos = game.getTarget().getPosY() + movingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED;
        while (!(nextYPos <= 2 * game.getTarget().getRadius())) {
            assertEquals(oldTarget, game.getTarget());
            nextYPos = game.getTarget().getPosY() + movingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED;
            game.tick();
        }
        game.tick();
        assertNotEquals(oldTarget, game.getTarget());
    }

    @Test
    public void tickTargetOffScreenDownTest() {
        Target oldTarget = game.getTarget();
        movingGame.setSpeedFactorX(0.0f);
        movingGame.setSpeedFactorY(1.0f);
        float nextYPos = game.getTarget().getPosY() + movingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED;
        while (!(nextYPos >= AimTrainer.getFrameHeight() - 2 * game.getTarget().getRadius())) {
            assertEquals(oldTarget, game.getTarget());
            nextYPos = game.getTarget().getPosY() + movingGame.getSpeedFactorY() * MovingGame.MOVE_SPEED;
            game.tick();
        }
        game.tick();
        assertNotEquals(oldTarget, game.getTarget());
    }



    @Test
    public void onClickMidMoveTest() {
        float oldSpeedFactorX = movingGame.getSpeedFactorX();
        float oldSpeedFactorY = movingGame.getSpeedFactorY();
        game.tick();
        game.tick();
        game.onClick(-1, -1); // -1, -1 represents a test value for a miss
        assertNotEquals(oldSpeedFactorX, movingGame.getSpeedFactorX());
        assertNotEquals(oldSpeedFactorY, movingGame.getSpeedFactorY());
    }
}
