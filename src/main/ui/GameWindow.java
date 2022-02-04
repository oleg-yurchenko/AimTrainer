package ui;

import model.*;

import com.googlecode.lanterna.gui2.BasicWindow;

import java.util.HashSet;

public class GameWindow extends BasicWindow {
    private Profile user;

    public GameWindow(Profile user) {
        super("Game");

        this.user = user;

        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);
    }
}
