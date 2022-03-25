package ui;

import model.Profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The window that shows the statistics of the selected profile and allows the user to delete it or reset it.
public class ProfileWindow extends JPanel {
    private Profile profile;
    private AimTrainer aimTrainer;

    // Effects: Shows the profile window with statistics for the given user.
    // Modifies: this
    public ProfileWindow(AimTrainer aimTrainer, Profile profile) {
        super(new GridLayout(3, 2));
        this.profile = profile;
        this.aimTrainer = aimTrainer;

        generateScreen();
    }

    public void generateScreen() {
        // Clears the screen of current components
        this.removeAll();
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Display's the user's stats onto the panel.
        loadStats(profile);

        // Adds the user's name
        this.add(new JLabel(profile.getName(), SwingConstants.CENTER));

        // Adds the reset profile button
        writeResetProfile();

        // Adds the delete profile button
        writeDeleteProfile();

        // Adds a simple back button to the component that takes the user back to the previous window (profile select).
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aimTrainer.setPanel(new ProfileSelectWindow(aimTrainer, AimTrainer.Mode.PROFILE));
            }
        });

        this.add(backButton);
        aimTrainer.setPanel(this);
    }

    // Effects: Displays all the statistics of the given profile onto this panel as static text.
    // Modifies: this
    public void loadStats(Profile profile) {
        this.add(new JLabel("Precision: ", SwingConstants.RIGHT));
        this.add(new JLabel(Float.toString(profile.getPrecision()), SwingConstants.LEFT));

        this.add(new JLabel("Accuracy: ", SwingConstants.RIGHT));
        this.add(new JLabel(Float.toString(profile.getAccuracy()), SwingConstants.LEFT));

        this.add(new JLabel("Hits: ", SwingConstants.RIGHT));
        this.add(new JLabel(Integer.toString(profile.getHits()), SwingConstants.LEFT));

        this.add(new JLabel("Clicks: ", SwingConstants.RIGHT));
        this.add(new JLabel(Integer.toString(profile.getClicks()), SwingConstants.LEFT));
    }

    // Effects: Creates and adds a button that resets the profile's stats.
    // Modifies: profile
    public void writeResetProfile() {
        JButton resetButton = new JButton("Reset Profile");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.resetProfile();
                generateScreen();
            }
        });
        this.add(resetButton);
    }

    // Effects: Creates and adds a button that deletes the profile.
    //          Only deletes the profile if the user enters "delete" into the prompt to not accidentally delete.
    // Modifies: aimTrainer
    public void writeDeleteProfile() {
        JButton deleteButton = new JButton("Delete Profile");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String confirmation = JOptionPane.showInputDialog("Enter: \"delete\" to confirm");
                if (confirmation.equals("delete")) {
                    aimTrainer.getProfiles().remove(profile);
                    aimTrainer.setPanel(new ProfileSelectWindow(aimTrainer, AimTrainer.Mode.PROFILE));
                }
            }
        });
        this.add(deleteButton);
    }
}
