package ui;

import com.googlecode.lanterna.gui2.*;
import model.Profile;

import java.util.HashSet;
import java.util.Random;

public class GameWindow extends BasicWindow {
    private int gridSize;
    private Profile user;
    private Random random = new Random();
    private Panel panel;
    private int target;

    public GameWindow(Profile user, int gridSize) {
        super("Game");

        this.user = user;
        this.gridSize = gridSize;

        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        panel = new Panel();

        target = random.nextInt(this.gridSize * this.gridSize);
        generateScreen();

        setComponent(panel);
    }

    private Panel generateTargets() {
        Panel gamePanel = new Panel();
        gamePanel.setLayoutManager(new GridLayout(gridSize));

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (target == i * gridSize + j) {
                    gamePanel.addComponent(new Button("X", new Runnable() {
                        @Override
                        public void run() {
                            user.hit(0.0f);
                            target = random.nextInt(gridSize * gridSize);
                            generateScreen();
                        }
                    }));
                } else {
                    gamePanel.addComponent(new Button("0", new Runnable() {
                        @Override
                        public void run() {
                            user.click();
                            generateScreen();
                        }
                    }));
                }
            }
        }

        return gamePanel;
    }

    private Panel generateQuickStats() {
        Panel statsPanel = new Panel();
        statsPanel.setLayoutManager(new GridLayout(2));

        ProfileWindow.loadStats(statsPanel, user);

        return statsPanel;
    }

    private Panel generateExit() {
        Panel exitPanel = new Panel();

        exitPanel.addComponent(new Button("Exit", new Runnable() {
            @Override
            public void run() {
                GameWindow.this.close();
            }
        }));

        return exitPanel;
    }

    private void generateScreen() {
        panel.removeAllComponents();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        panel.addComponent(generateTargets());
        panel.addComponent(generateQuickStats());
        panel.addComponent(generateExit());
    }
}
