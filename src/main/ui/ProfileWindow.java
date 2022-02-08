package ui;

import com.googlecode.lanterna.gui2.*;
import model.Profile;

import java.util.HashSet;

// The window that shows the statistics of the selected profile.
public class ProfileWindow extends BasicWindow {
    private Profile user;
    private Panel panel;

    // Shows the profile window with statistics for the given user.
    public ProfileWindow(Profile user) {
        // Title of the window
        super(user.getName());

        this.user = user;

        // Set hints that indicate the window's properties and behaviour.
        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        // Display's the user's stats onto the panel.
        loadStats(panel, user);

        // Adds a simple back button to the component that takes the user back to the previous window (profile select).
        panel.addComponent(new Button("Back", new Runnable() {
            @Override
            public void run() {
                ProfileWindow.this.close();
            }
        }));

        setComponent(panel);
    }

    // Displays all the statistics of the given user onto the given panel as static text
    // This method is static as it is easier to call this method from another function than rewriting this function in
    //     the other class.
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
