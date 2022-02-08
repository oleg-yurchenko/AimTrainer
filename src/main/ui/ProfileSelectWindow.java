package ui;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import model.Profile;

import java.util.ArrayList;
import java.util.HashSet;

public class ProfileSelectWindow extends BasicWindow {
    private AimTrainer.Mode mode;
    private ArrayList<Profile> profiles;
    private BasicWindow nextWindow;
    private Panel panel;

    public ProfileSelectWindow(ArrayList<Profile> profiles, AimTrainer.Mode mode) {
        super("Select Profile");
        this.mode = mode;
        this.profiles = profiles;

        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        writeProfiles();
        setComponent(panel);

    }

    private void writeProfiles() {
        // SHOULD I USE SUPPRESS CHECKSTYLE ANYWHERE??
        // DO I NEED ANOTHER USER STORY (I CURRENTLY HAVE 3 IMPLEMENTED, NEED 4)
        // THIS IS A PROBLEM BECAUSE THE GUI LIMITS THE AMOUNT I CAN DO
        for (final Profile profile : profiles) {
            panel.addComponent(new Button(profile.getName(), new Runnable() {
                @Override
                public void run() {
                    if (mode == AimTrainer.Mode.GAME) {
                        // need to shorten this lol
                        int gridSize = Integer.parseInt(TextInputDialog.showDialog(getTextGUI(), "Size:", "Enter the size of the grid", "10"));
                        nextWindow = new GameWindow(profile, gridSize);
                    } else {
                        nextWindow = new ProfileWindow(profile);
                    }
                    getTextGUI().addWindow(nextWindow);
                    nextWindow.waitUntilClosed();
                }
            }));
        }

        panel.addComponent(new Button("Add Profile", new Runnable() {
            @Override
            public void run() {
                String name = TextInputDialog.showDialog(getTextGUI(), "Name:", "Enter the name of the profile", "");
                profiles.add(new Profile(name));
                panel.removeAllComponents();
                writeProfiles();
            }
        }));

        panel.addComponent(new Button("Back", new Runnable() {
            @Override
            public void run() {
                ProfileSelectWindow.this.close();
            }
        }));
    }
}
