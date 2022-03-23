package ui;

import model.Target;

import javax.swing.*;
import java.awt.*;

public class TargetDrawing extends JComponent {
    private Target target;
    private float originalX;
    private float originalY;
    private float originalRadius;

    TargetDrawing(Target target) {
        this.target = target;
        this.originalX = -1.0f;
        this.originalY = -1.0f;
        this.originalRadius = 0.0f;
        setPreferredSize(new Dimension(AimTrainer.getFrameWidth(), AimTrainer.getFrameHeight()));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        float radius = target.getRadius();
        graphics.setColor(Color.getColor(target.getColour()));
        int newX = (int)(originalX + originalRadius - radius);
        int newY = (int)(originalY + originalRadius - radius);
        graphics.fillOval(newX, newY, (int)(radius * 2), (int)(radius * 2));
    }

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
