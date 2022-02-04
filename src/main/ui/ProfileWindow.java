package ui;

import com.googlecode.lanterna.gui2.*;
import model.Profile;

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

        loadStats(panel, user);

        panel.addComponent(new Button("Back", new Runnable() {
            @Override
            public void run() {
                ProfileWindow.this.close();
            }
        }));

        setComponent(panel);
    }

    public static void loadStats(Panel panel, Profile user) {
        panel.addComponent(new Label("Precision:"));
        panel.addComponent(new Label(Float.toString(user.getPrecision())));

        panel.addComponent(new Label("Accuracy"));
        panel.addComponent(new Label(Float.toString(user.getAccuracy())));

        panel.addComponent(new Label("Hits"));
        panel.addComponent(new Label(Integer.toString(user.getHits())));

        panel.addComponent(new Label("Clicks"));
        panel.addComponent(new Label(Integer.toString(user.getClicks())));
    }
}
