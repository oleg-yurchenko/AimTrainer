package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The window that is shown to the user to choose a profile to perform the next "action" (go to game or view profile).
public class ProfileSelectWindow extends JPanel {
    private AimTrainer.Mode mode;
    private AimTrainer aimTrainer;
    private JPanel nextWindow;

    // Sets the initial variable values and shows the profile window and all its components.
    public ProfileSelectWindow(AimTrainer aimTrainer, AimTrainer.Mode mode) {
        // Title of the window
        super();

        this.mode = mode;
        this.aimTrainer = aimTrainer;

        // Displays all the profiles, an add profile button and a quit button.
        generateScreen();
    }


    // Adds all the profiles in this.profiles to the panel.
    private void generateScreen() {
        // Clears the screen of current components
        this.removeAll();

        // Displays all the profiles in this.profiles
        for (Profile profile : aimTrainer.getProfiles()) {
            writeProfile(profile);
        }

        writeAddProfile();

        // Back button that takes the user back to the menu.
        // this doesn't warrant a separate function, as the functionality is very straightforward.
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aimTrainer.setPanel(new MenuWindow(aimTrainer));
            }
        });
        this.add(backButton);
        aimTrainer.setPanel(this);
    }

    // Creates a button of the given profile and attaches it to the panel.
    private void writeProfile(Profile profile) {
        JButton profileButton = new JButton(profile.getName());
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // checks which window to launch next
                if (mode == AimTrainer.Mode.GAME) {
                    Object[] gameOptions = {"Cancel", "Moving", "Shrinking", "Default"};
                    int gameChoice = JOptionPane.showOptionDialog(null, "Choose your game mode", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, gameOptions, gameOptions[3]);
                    // seconds * 1000 to convert to milliseconds
                    int time = 0;
                    if (gameChoice != 0) {
                        time = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the time for your game (in seconds)")) * 1000;
                    }
                    Game gameMode;
                    switch (gameChoice) {
                        case 3: gameMode = new DefaultGame(profile, time);
                                break;
                        case 2: gameMode = new ShrinkingGame(profile, time);
                                break;
                        case 1: gameMode = new MovingGame(profile, time);
                                break;
                        default: gameMode = null;
                    }
                    if (gameMode != null) {
                        nextWindow = new GameWindow(aimTrainer, profile, gameMode);
                    } else {
                        nextWindow = new ProfileSelectWindow(aimTrainer, mode);
                    }
                } else {
                    nextWindow = new ProfileWindow(aimTrainer, profile);
                }
                // renders the given next window, whether it be to view the profile or play the game.
                aimTrainer.setPanel(nextWindow);
            }
        });
        this.add(profileButton);
    }

    // Creates a button to add a profile and attaches it to the panel
    private void writeAddProfile() {
        JButton addProfileButton = new JButton("Add Profile");
        addProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Asks the user to enter the name of the new profile
                String name = JOptionPane.showInputDialog(null, "Enter the name of your profile");
                // Makes a new profile with the given name and adds it to the list of profiles.
                if (!(name == null)) {
                    aimTrainer.getProfiles().add(new Profile(name));
                }
                // "Refreshes" the panels by removing all the contents and regenerating the buttons.
                // This makes sure the new profile is added to the list of other profiles.
                generateScreen();
            }
        });
        this.add(addProfileButton);
    }
}
