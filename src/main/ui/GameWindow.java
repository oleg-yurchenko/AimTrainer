package ui;

import exceptions.GameOverException;
import model.Game;
import model.Profile;

import javax.swing.*;
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
    public GameWindow(AimTrainer aimTrainer, Profile user, Game gameMode) {
        super();

        this.aimTrainer = aimTrainer;
        this.user = user;
        this.gameMode = gameMode;
        this.targetDrawing = new TargetDrawing(null);

        this.add(targetDrawing);

        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        gameMode.tick();
                        Thread.sleep(1);
                    } catch (GameOverException | InterruptedException e) {
                        System.out.println("Game Over");
                        break;
                    }
                }
                System.out.println("game thread over");
            }
        }, "gameThread");
        Thread screenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (gameThread.isAlive()) {
                    gameLoop();
                    System.out.println("screen thread");
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                System.out.println("screen thread over");
                stopMouseListening();
                endGameScreen();
            }
        }, "screenThread");

        startMouseListening();
        gameThread.start();
        screenThread.start();
    }

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

    private void stopMouseListening() {
        this.removeMouseListener(this.mouseListener);
    }

    private void gameLoop() {

        // draw target
        targetDrawing.setTarget(gameMode.getTarget());
        this.repaint();
    }

    private void endGameScreen() {
        this.removeAll();
        // Add restart/back buttons
        // maybe quick stats?
    }

    /*
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
    */
}
