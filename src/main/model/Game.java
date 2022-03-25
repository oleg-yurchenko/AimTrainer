package model;

import exceptions.GameOverException;
import ui.AimTrainer;

import java.util.Random;

// An abstract Game class used to represent various game modes
public abstract class Game {
    public static final float START_RADIUS = 20.0f;
    protected Random random;
    protected Target target;
    protected Profile profile;
    protected String colour;
    protected int time;

    // Effects: A constructor that sets the current target to null, and sets the profile, time, and colour to the given
    //          variables.
    // Modifies: this
    public Game(Profile profile, int time, String colour) {
        this.target = null;
        this.profile = profile;
        this.time = time;
        this.colour = colour;
        random = new Random();
    }

    // Effects: Reduces the timer by one.
    //          Throws GameOverException if the time limit is reached.
    // Modifies: this
    public void tick() {
        if (--time <= 0) {
            throw new GameOverException();
        }
    }

    // Effects: Makes a target with the given radius.
    // Modifies: this
    // Requires: targetRadius >= 0.0f
    protected Target makeTarget(float targetRadius) {
        int positionX = random.nextInt(AimTrainer.getFrameWidth() - 3 * (int)targetRadius) + (int)targetRadius;
        int positionY = random.nextInt(AimTrainer.getFrameHeight() - 3 * (int)targetRadius) + (int)targetRadius;
        if (this.target == null) {
            return new Target(positionX, positionY, colour, targetRadius);
        }
        return new Target(positionX, positionY, target.getColour(), targetRadius);
    }

    // Effects: processes a mouse click to either hit the target or miss the target, then generates a new target
    //          -1, -1 special input is used to testing misses, and should never occur when actually playing
    // Modifies: this, profile
    public void onClick(int cursorX, int cursorY) {
        if (target.isHit(cursorX, cursorY)) {
            profile.hit(target.getDistanceToPoint(cursorX, cursorY));
        } else {
            profile.click();
        }
        target = makeTarget(target.getRadius());
    }

    // Effects: creates a new target with the start radius
    // Modifies: this
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
