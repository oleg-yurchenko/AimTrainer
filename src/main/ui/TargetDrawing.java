package ui;

import model.Target;

import javax.swing.*;
import java.awt.*;

// The drawing of a Target (circle)
public class TargetDrawing extends JComponent {
    private Target target;
    private float originalX;
    private float originalY;
    private float originalRadius;

    // Effects: Sets initial values and gets the given target.
    // Modifies: this
    TargetDrawing(Target target) {
        this.target = target;
        this.originalX = -1.0f;
        this.originalY = -1.0f;
        this.originalRadius = 0.0f;
        setPreferredSize(new Dimension(AimTrainer.getFrameWidth(), AimTrainer.getFrameHeight()));
    }

    // Effects: Draws the target onto the screen.
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        float radius = target.getRadius();
        graphics.setColor(Color.getColor(target.getColour()));
        int newX = (int)(originalX + originalRadius - radius);
        int newY = (int)(originalY + originalRadius - radius);
        graphics.fillOval(newX, newY, (int)(radius * 2), (int)(radius * 2));
    }

    // Effects: Sets a new target and changes values related to the new target.
    // Modifies: this
    public void setTarget(Target target) {
        this.target = target;
        this.originalX = target.getPosX() - target.getRadius();
        this.originalY = target.getPosY() - target.getRadius();
        this.originalRadius = target.getRadius();
    }

    public Target getTarget() {
        return target;
    }
}
