package ui;

import model.Target;

import javax.swing.*;
import java.awt.*;

public class TargetDrawing extends JComponent {
    private Target target;

    TargetDrawing(Target target) {
        this.target = target;
        setPreferredSize(new Dimension(AimTrainer.getFrameWidth(), AimTrainer.getFrameHeight()));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int radius = (int)target.getRadius();
        graphics.setColor(Color.getColor(target.getColour()));
        graphics.fillOval((int)target.getPosX() - radius, (int)target.getPosY() - radius, radius, radius);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }
}
