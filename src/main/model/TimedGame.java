package model;

import exceptions.GameOverException;
import ui.AimTrainer;

import java.awt.*;
import java.util.Random;

public class TimedGame extends Game {
    private int time;
    public static final float TARGET_RADIUS = 20;
    public static final String COLOR = "black";
    private Random random;

    public TimedGame(Profile profile, int time) {
        super(profile);
        this.time = time;
        this.random = new Random();
        gameStart();
    }

    public void tick() {
        if (time-- <= 0) {
            throw new GameOverException();
        }
    }

    public void gameStart() {
        target = makeTarget();
    }

    public void onClick(int cursorX, int cursorY) {
        if (target.isHit(cursorX, cursorY)) {
            profile.hit(target.getDistanceToPoint(cursorX, cursorY));
        } else {
            profile.click();
        }
        target = makeTarget();
    }

    private Target makeTarget() {
        int positionX = random.nextInt(AimTrainer.getFrameWidth() - 2 * (int)TARGET_RADIUS) + (int)TARGET_RADIUS;
        int positionY = random.nextInt(AimTrainer.getFrameHeight() - 2 * (int)TARGET_RADIUS) + (int)TARGET_RADIUS;
        return new Target(positionX, positionY, COLOR, TARGET_RADIUS);
    }
}
