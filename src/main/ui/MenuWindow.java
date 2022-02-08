package ui;

import com.googlecode.lanterna.gui2.*;
import model.Profile;

import java.util.ArrayList;
import java.util.HashSet;

// This class represents the window where the user can choose to play a game, view a profile or quit.
public class MenuWindow extends BasicWindow {
    private ProfileSelectWindow profileSelectWindow;
    private ArrayList<Profile> profiles;

    // Sets the initial variable values and shows the menu window and all its components.
    public MenuWindow(ArrayList<Profile> profileList) {
        // Title of the window
        super("Menu");
        this.profiles = profileList;

        // Set hints that indicate the window's properties and behaviour.
        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        // The buttons of all the user actions.
        panel.addComponent(new Button("Play", new Runnable() {
            @Override
            public void run() {
                // Opens the profile selector window and sets the mode to GAME
                profileSelectWindow = new ProfileSelectWindow(profiles, AimTrainer.Mode.GAME);
                getTextGUI().addWindow(profileSelectWindow);
                profileSelectWindow.waitUntilClosed();
            }
        }));

        panel.addComponent(new Button("Profiles", new Runnable() {
            @Override
            public void run() {
                // Opens the profile selector window and sets the mode to PROFILE
                profileSelectWindow = new ProfileSelectWindow(profiles, AimTrainer.Mode.PROFILE);
                getTextGUI().addWindow(profileSelectWindow);
                profileSelectWindow.waitUntilClosed();
            }
        }));

        panel.addComponent(new Button("Quit", new Runnable() {
            @Override
            public void run() {
                MenuWindow.this.close();
            }
        }));

        setComponent(panel);
    }

}
