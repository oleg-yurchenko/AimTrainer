package ui;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import model.Profile;

import com.googlecode.lanterna.gui2.BasicWindow;

import java.util.HashSet;

public class ProfileWindow extends BasicWindow {
    private Profile user;

    public ProfileWindow(Profile user) {
        super(user.getName());

        this.user = user;

        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("Precision:"));
        panel.addComponent(new Label(Float.toString(user.getPrecision())));

        panel.addComponent(new Label("Accuracy"));
        panel.addComponent(new Label(Float.toString(user.getAccuracy())));

        panel.addComponent(new Label("Hits"));
        panel.addComponent(new Label(Integer.toString(user.getHits())));

        panel.addComponent(new Label("Clicks"));
        panel.addComponent(new Label(Integer.toString(user.getClicks())));

        setComponent(panel);
    }
}
