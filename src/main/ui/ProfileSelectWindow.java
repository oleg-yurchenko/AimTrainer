package ui;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import model.Profile;

import java.util.ArrayList;
import java.util.HashSet;

// The window that is shown to the user to choose a profile to perform the next "action" (go to game or view profile).
public class ProfileSelectWindow extends BasicWindow {
    private AimTrainer.Mode mode;
    private ArrayList<Profile> profiles;
    private BasicWindow nextWindow;
    private Panel panel;

    // Sets the initial variable values and shows the profile window and all its components.
    public ProfileSelectWindow(ArrayList<Profile> profiles, AimTrainer.Mode mode) {
        // Title of the window
        super("Select Profile");

        this.mode = mode;
        this.profiles = profiles;

        // Set hints that indicate the window's properties and behaviour.
        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        // Displays all the profiles, an add profile button and a quit button.
        generateScreen();

        setComponent(panel);

    }

    // Adds all the profiles in this.profiles to the panel.
    private void generateScreen() {
        // Displays all the profiles in this.profiles
        for (Profile profile : profiles) {
            writeProfile(profile);
        }

        writeAddProfile();

        // Back button that takes the user back to the menu.
        // this doesn't warrant a separate function, as the functionality is very straightforward.
        panel.addComponent(new Button("Back", new Runnable() {
            @Override
            public void run() {
                ProfileSelectWindow.this.close();
            }
        }));
    }

    // Creates a button of the given profile and attaches it to the panel.
    private void writeProfile(final Profile profile) {
        panel.addComponent(new Button(profile.getName(), new Runnable() {
            @Override
            public void run() {
                // checks which window to launch next
                if (mode == AimTrainer.Mode.GAME) {
                    // Asks the user how big they want the game (how many targets in one column/row)
                    String size = TextInputDialog.showDialog(getTextGUI(), "Size", "Enter the size of the grid", "10");
                    int gridSize = Integer.parseInt(size);
                    nextWindow = new GameWindow(profile, gridSize);
                } else {
                    nextWindow = new ProfileWindow(profile);
                }
                // renders the given next window, whether it be to view the profile or play the game.
                getTextGUI().addWindow(nextWindow);
                nextWindow.waitUntilClosed();
            }
        }));
    }

    // Creates a button to add a profile and attaches it to the panel
    private void writeAddProfile() {
        panel.addComponent(new Button("Add Profile", new Runnable() {
            @Override
            public void run() {
                // Asks the user to enter the name of the new profile
                String name = TextInputDialog.showDialog(getTextGUI(), "Name:", "Enter the name of the profile", "");
                // Makes a new profile with the given name and adds it to the list of profiles.
                profiles.add(new Profile(name));
                // "Refreshes" the panels by removing all the contents and regenerating the buttons.
                // This makes sure the new profile is added to the list of other profiles.
                panel.removeAllComponents();
                generateScreen();
            }
        }));
    }
}
