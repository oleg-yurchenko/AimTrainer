package ui;

import exceptions.GameOverException;
import model.Game;
import model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

// The Window that is shown when the user plays the game itself.
public class GameWindow extends JPanel {
    private Profile user;
    private Random random = new Random();
    private Game gameMode;
    private TargetDrawing targetDrawing;
    private AimTrainer aimTrainer;
    private MouseListener mouseListener;

    // Sets the initial variable values and shows the game window and all its components.
    // Starts the game and screen threads to start the game cycle.
    public GameWindow(AimTrainer aimTrainer, Profile user, Game gameMode) {
        super();

        this.aimTrainer = aimTrainer;
        this.user = user;
        this.gameMode = gameMode;
        this.targetDrawing = new TargetDrawing(null);

        this.add(targetDrawing);

        Thread gameThread = makeGameThread();
        Thread screenThread = makeScreenThread(gameThread);

        startMouseListening();
        gameThread.start();
        screenThread.start();
    }

    // Effects: Returns a new thread that is used to start the game, and then tick() the Game until GameOver is thrown.
    // Modifies: gameMode
    private Thread makeGameThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                gameMode.gameStart();
                while (true) {
                    try {
                        gameMode.tick();
                        Thread.sleep(1);
                    } catch (GameOverException | InterruptedException e) {
                        break;
                    }
                }
            }
        }, "gameThread");
    }

    // Effects: Returns a new thread that is used to draw onto the screen. The gameThread is passed in so that this
    //          thread stops when gameThread dies.
    private Thread makeScreenThread(Thread gameThread) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (gameThread.isAlive()) {
                    if (gameMode.getTarget() != null) {
                        gameLoop();
                    }
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                stopMouseListening();
                endGameScreen();
            }
        }, "screenThread");
    }

    // Effects: Adds a MouseListener to the window which runs the game's onClick() method whenever the mouse is pressed.
    // Modifies: this
    private void startMouseListening() {
        this.mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameMode.onClick(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        this.addMouseListener(this.mouseListener);
    }

    // Effects: Removes the MouseListener to stop polling for the game's onClick() function.
    private void stopMouseListening() {
        this.removeMouseListener(this.mouseListener);
    }

    // Effects: Sets the TargetDrawing to the game's target and repaints the screen (refreshes it).
    // Modifies: targetDrawing
    private void gameLoop() {
        // draw target
        targetDrawing.setTarget(gameMode.getTarget());
        this.repaint();
    }

    // Effects: Displays a small timer in the top left of the screen to show the time remaining.
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawString(Float.toString(Math.round((float)gameMode.getTime() / 10) / 100.0f), 20, 20);
    }

    // Effects: Clears the screen and displays the player's stats and "retry" and "exit" buttons
    // Modifies: this
    private void endGameScreen() {
        this.removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridheight = 1;
        constraint.gridwidth = 1;
        constraint.gridx = 0;
        constraint.gridy = 0;
        // Add restart/back buttons
        this.add(makeRetryButton(), constraint);
        constraint.gridx = 1;
        this.add(makeExitButton(), constraint);
        constraint.gridwidth = 2;
        constraint.gridy = 1;
        constraint.gridx = GridBagConstraints.REMAINDER;
        this.add(makeStatsPanel(), constraint);
        aimTrainer.setPanel(this);
    }

    // Effects: Creates and returns a retry button that instantiates a new GameWindow to start the process again
    // Modifies: aimTrainer
    private JButton makeRetryButton() {
        JButton retryButton = new JButton("Retry");
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aimTrainer.setPanel(new GameWindow(aimTrainer, user, gameMode));
            }
        });
        return retryButton;
    }

    // Effects: Creates and returns an exit button that swaps the panel to the menu.
    // Modifies: aimTrainer
    private JButton makeExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aimTrainer.setPanel(new MenuWindow(aimTrainer));
            }
        });
        return exitButton;
    }

    // Effects: Creates and returns a panel with the player's statistics
    private JPanel makeStatsPanel() {
        JPanel stats = new JPanel(new GridLayout(2, 2));
        stats.add(new Label("Precision:"));
        stats.add(new Label(Float.toString(gameMode.getProfile().getPrecision())));
        stats.add(new Label("Accuracy:"));
        stats.add(new Label(Float.toString(gameMode.getProfile().getAccuracy())));

        return stats;
    }
}
