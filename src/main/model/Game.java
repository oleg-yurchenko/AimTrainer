package model;

import ui.AimTrainer;

import java.util.ArrayList;

public abstract class Game {
    protected Target target;
    protected Profile profile;

    public Game(Profile profile) {
        target = null;
        this.profile = profile;
    }

    public abstract void tick();

    public abstract void gameStart();

    public abstract void onClick(int cursorX, int cursorY);

    public Target getTarget() {
        return target;
    }

    public Profile getProfile() {
        return profile;
    }
}
