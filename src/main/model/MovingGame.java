package model;

import ui.AimTrainer;

import java.util.Random;

public class MovingGame extends Game {
    public static final float MOVE_SPEED = 0.5f;
    private static float speedFactorX;
    private static float speedFactorY;
    private float lastTargetTime;
    Random random;

    public MovingGame(Profile profile, int time) {
        super(profile, time, "black");
        lastTargetTime = time;
        random = new Random();
    }

    @Override
    public void tick() {
        super.tick();
        target.moveX(speedFactorX * MOVE_SPEED);
        target.moveY(speedFactorY * MOVE_SPEED);
        if (isOutOfBounds()) {
            if (lastTargetTime - time > 1000) {
                profile.click();
            }
            target = makeTarget(START_RADIUS);
        }
    }

    @Override
    protected Target makeTarget(float radius) {
        getNewDirection();
        lastTargetTime = time;
        return super.makeTarget(radius);
    }

    private void getNewDirection() {
        speedFactorX = random.nextFloat() - 0.5f;
        speedFactorY = random.nextFloat() - 0.5f;
    }

    public boolean isOutOfBounds() {
        boolean boundsLeft = target.getPosX() < 2 * target.getRadius();
        boolean boundsRight = target.getPosX() > AimTrainer.getFrameWidth() - 2 * target.getRadius();
        boolean boundsUp = target.getPosY() < 2 * target.getRadius();
        boolean boundsDown = target.getPosY() > AimTrainer.getFrameHeight() - 2 * target.getRadius();
        return boundsLeft || boundsRight || boundsUp || boundsDown;
    }

    public static float getSpeedFactorX() {
        return speedFactorX;
    }

    public static float getSpeedFactorY() {
        return speedFactorY;
    }
}
