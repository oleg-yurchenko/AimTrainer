package ui;

import model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// This class represents the window where the user can choose to play a game, view a profile or quit.
public class MenuWindow extends JPanel {
    private ProfileSelectWindow profileSelectWindow;
    private ArrayList<Profile> profiles;
    private AimTrainer aimTrainer;

    // Sets the initial variable values and shows the menu window and all its components.
    public MenuWindow(AimTrainer aimTrainer) {
        // Title of the window
        super(new GridLayout(3, 1));
        this.profiles = aimTrainer.getProfiles();
        this.aimTrainer = aimTrainer;

        generateScreen();
    }

    private void generateScreen() {
        // The buttons of all the user actions.
        this.add(makePlayButton());
        this.add(makeProfilesButton());
        this.add(makeQuitButton());
    }

    // Effects: Creates the play button for the MenuWindow
    private JButton makePlayButton() {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Opens the profile selector window and sets the mode to GAME
                profileSelectWindow = new ProfileSelectWindow(aimTrainer, AimTrainer.Mode.GAME);
                aimTrainer.setPanel(profileSelectWindow);
            }
        });
        return playButton;
    }

    // Effects: Creates the profiles button for the MenuWindow
    private JButton makeProfilesButton() {
        JButton profilesButton = new JButton("Profiles");
        profilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Opens the profile selector window and sets the mode to PROFILE
                profileSelectWindow = new ProfileSelectWindow(aimTrainer, AimTrainer.Mode.PROFILE);
                aimTrainer.setPanel(profileSelectWindow);
            }
        });
        return profilesButton;
    }

    // Effects: Creates the Quit button for the MenuWindow
    private JButton makeQuitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aimTrainer.exit();
            }
        });
        return quitButton;
    }
}
