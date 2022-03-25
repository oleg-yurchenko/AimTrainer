package model;

import ui.AimTrainer;

import java.util.Random;

// A game mode with moving targets
public class MovingGame extends Game {
    public static final float MOVE_SPEED = 0.5f;
    private float speedFactorX;
    private float speedFactorY;
    private float lastTargetTime;
    Random random;

    // Effects: Creates a new Game with the given profile and time, and sets the lastTargetTime to the initial time
    // Modifies: this
    public MovingGame(Profile profile, int time) {
        super(profile, time, "black");
        lastTargetTime = time;
        random = new Random();
    }

    // Effects: In addition to the super's tick function, it moves the target by the randomized speed, and checks if
    //          the target is out of bounds (to generate a new one).
    //          If the time from the last target is greater than a second, it counts as a miss
    // Modifies: this, target, profile
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

    // Effects: Gets a new, randomized direction and sets the lastTargetTime to the current time. Then creates a new
    //          target with the given radius
    // Modifies: this
    // Requires: radius >= 0.0f
    @Override
    protected Target makeTarget(float radius) {
        getNewDirection();
        lastTargetTime = time;
        return super.makeTarget(radius);
    }

    // Effects: Generates a new, random direction in both the x and y axes.
    // Modifies: this
    private void getNewDirection() {
        speedFactorX = random.nextFloat() - 0.5f;
        speedFactorY = random.nextFloat() - 0.5f;
    }

    // Effects: Returns true if the target is outside the screen's bounds.
    public boolean isOutOfBounds() {
        boolean boundsLeft = target.getPosX() < 2 * target.getRadius();
        boolean boundsRight = target.getPosX() > AimTrainer.getFrameWidth() - 2 * target.getRadius();
        boolean boundsUp = target.getPosY() < 2 * target.getRadius();
        boolean boundsDown = target.getPosY() > AimTrainer.getFrameHeight() - 2 * target.getRadius();
        return boundsLeft || boundsRight || boundsUp || boundsDown;
    }

    public float getSpeedFactorX() {
        return speedFactorX;
    }

    public float getSpeedFactorY() {
        return speedFactorY;
    }

    public void setSpeedFactorX(float speedFactorX) {
        this.speedFactorX = speedFactorX;
    }

    public void setSpeedFactorY(float speedFactorY) {
        this.speedFactorY = speedFactorY;
    }
}
