package model;

import exceptions.GameOverException;
import ui.AimTrainer;

import java.util.Random;

public abstract class Game {
    public static final float START_RADIUS = 20.0f;
    protected Random random;
    protected Target target;
    protected Profile profile;
    protected String colour;
    protected int time;

    public Game(Profile profile, int time, String colour) {
        this.target = null;
        this.profile = profile;
        this.time = time;
        this.colour = colour;
        random = new Random();
    }

    public void tick() {
        if (--time <= 0) {
            throw new GameOverException();
        }
    }

    protected Target makeTarget(float targetRadius) {
        int positionX = random.nextInt(AimTrainer.getFrameWidth() - 3 * (int)targetRadius) + (int)targetRadius;
        int positionY = random.nextInt(AimTrainer.getFrameHeight() - 3 * (int)targetRadius) + (int)targetRadius;
        if (this.target == null) {
            return new Target(positionX, positionY, colour, targetRadius);
        }
        return new Target(positionX, positionY, target.getColour(), targetRadius);
    }

    // -1, -1 input means miss
    public void onClick(int cursorX, int cursorY) {
        if (target.isHit(cursorX, cursorY)) {
            profile.hit(target.getDistanceToPoint(cursorX, cursorY));
        } else {
            profile.click();
        }
        target = makeTarget(target.getRadius());
    }

    public void gameStart() {
        target = makeTarget(START_RADIUS);
    }

    public Target getTarget() {
        return target;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getTime() {
        return time;
    }
}
