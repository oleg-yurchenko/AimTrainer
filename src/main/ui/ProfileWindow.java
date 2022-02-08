package ui;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import model.Profile;

import java.util.ArrayList;
import java.util.HashSet;

// The window that shows the statistics of the selected profile and allows the user to delete it or reset it.
public class ProfileWindow extends BasicWindow {
    private Profile profile;
    private Panel panel;
    private ArrayList<Profile> profiles;

    // Shows the profile window with statistics for the given user.
    public ProfileWindow(ArrayList<Profile> profiles, Profile profile) {
        // Title of the window
        super(profile.getName());

        this.profile = profile;
        this.profiles = profiles;

        // Set hints that indicate the window's properties and behaviour.
        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        generateScreen();

        setComponent(panel);
    }

    public void generateScreen() {
        // Clears the screen of current components
        panel.removeAllComponents();

        // Display's the user's stats onto the panel.
        loadStats(panel, profile);

        // Adds the reset profile button
        writeResetProfile();

        // Adds the delete profile button
        writeDeleteProfile();

        // Adds a simple back button to the component that takes the user back to the previous window (profile select).
        panel.addComponent(new Button("Back", new Runnable() {
            @Override
            public void run() {
                ProfileWindow.this.close();
            }
        }));
    }

    // Displays all the statistics of the given profile onto the given panel as static text
    // This method is static as it is easier to call this method from another function than rewriting this function in
    //     the other class.
    public static void loadStats(Panel panel, Profile profile) {
        panel.addComponent(new Label("Precision:"));
        panel.addComponent(new Label(Float.toString(profile.getPrecision())));

        panel.addComponent(new Label("Accuracy"));
        panel.addComponent(new Label(Float.toString(profile.getAccuracy())));

        panel.addComponent(new Label("Hits"));
        panel.addComponent(new Label(Integer.toString(profile.getHits())));

        panel.addComponent(new Label("Clicks"));
        panel.addComponent(new Label(Integer.toString(profile.getClicks())));
    }

    public void writeResetProfile() {
        panel.addComponent(new Button("Reset Profile", new Runnable() {
            @Override
            public void run() {
                profile.resetProfile();
                generateScreen();
            }
        }));
    }

    public void writeDeleteProfile() {
        panel.addComponent(new Button("Delete Profile", new Runnable() {
            @Override
            public void run() {
                String confirmation = TextInputDialog.showDialog(getTextGUI(), "Confirm", "Enter: \"delete\"", "");
                if (confirmation.equals("delete")) {
                    profiles.remove(profile);
                    ProfileWindow.this.close();
                }
            }
        }));
    }
}
