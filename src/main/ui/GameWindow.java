package ui;

import com.googlecode.lanterna.gui2.*;
import model.Profile;

import java.util.HashSet;
import java.util.Random;

// The Window that is shown when the user plays the game itself.
public class GameWindow extends BasicWindow {
    private int gridSize;
    private Profile user;
    private Random random = new Random();
    private Panel panel;
    private int target;

    // Sets the initial variable values and shows the game window and all its components.
    public GameWindow(Profile user, int gridSize) {
        // Title of the window
        super("Game");

        this.user = user;
        this.gridSize = gridSize;

        // Set hints that indicate the window's properties and behaviour.
        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        panel = new Panel();

        // Generates a new target upon being run for the first time.
        target = random.nextInt(this.gridSize * this.gridSize);
        generateScreen();

        setComponent(panel);
    }

    // Returns a grid of buttons that the user can click, only one being the target marked by an "X".
    @SuppressWarnings("methodLength")
    private Panel generateTargets() {
        // Override method size??

        // Creates a new panel with a gridlayout based on the specified size.
        Panel gamePanel = new Panel();
        gamePanel.setLayoutManager(new GridLayout(gridSize));

        // Loops through the grid to add the buttons and target to the game panel.
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (target == i * gridSize + j) {
                    // Target button.
                    gamePanel.addComponent(new Button("X", new Runnable() {
                        @Override
                        public void run() {
                            // no distance as screen coordinates cannot be implemented as desired in this phase.
                            user.hit(0.0f);
                            // generates a new target and refreshes the screen.
                            target = random.nextInt(gridSize * gridSize);
                            generateScreen();
                        }
                    }));
                } else {
                    // "Miss" button.
                    gamePanel.addComponent(new Button("0", new Runnable() {
                        @Override
                        public void run() {
                            user.click();
                            // refreshes the screen (target stays the same, updates statistics displayed below the grid)
                            generateScreen();
                        }
                    }));
                }
            }
        }

        return gamePanel;
    }

    // Returns a panel of the user's statistics
    private Panel generateQuickStats() {
        Panel statsPanel = new Panel();
        statsPanel.setLayoutManager(new GridLayout(2));

        // loadStats is a static function that generates the given user's statistics onto a given panel.
        ProfileWindow.loadStats(statsPanel, user);

        return statsPanel;
    }

    // Returns the panel with the exit button to go back to the menu.
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

    // Clears the panel and adds all the desired components to the main panel.
    private void generateScreen() {
        // removes all the components (as some may have changed: grid or stats)
        panel.removeAllComponents();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        // Adds the various components in order.
        panel.addComponent(generateTargets());
        panel.addComponent(generateQuickStats());
        panel.addComponent(generateExit());
    }
}
