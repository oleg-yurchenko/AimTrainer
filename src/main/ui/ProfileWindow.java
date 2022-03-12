package ui;

import model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The window that shows the statistics of the selected profile and allows the user to delete it or reset it.
public class ProfileWindow extends JPanel {
    private Profile profile;
    private AimTrainer aimTrainer;

    // Shows the profile window with statistics for the given user.
    public ProfileWindow(AimTrainer aimTrainer, Profile profile) {
        // Title of the window
        super(new GridLayout(3, 2));
        this.profile = profile;
        this.aimTrainer = aimTrainer;

        generateScreen();
    }

    public void generateScreen() {
        // Clears the screen of current components
        this.removeAll();

        // Display's the user's stats onto the panel.
        loadStats(this, profile);

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

    // Displays all the statistics of the given profile onto the given panel as static text
    // This method is static as it is easier to call this method from another function than rewriting this function in
    //     the other class.
    public static void loadStats(JPanel panel, Profile profile) {
        panel.add(new JLabel("Precision:"));
        panel.add(new JLabel(Float.toString(profile.getPrecision())));

        panel.add(new JLabel("Accuracy"));
        panel.add(new JLabel(Float.toString(profile.getAccuracy())));

        panel.add(new JLabel("Hits"));
        panel.add(new JLabel(Integer.toString(profile.getHits())));

        panel.add(new JLabel("Clicks"));
        panel.add(new JLabel(Integer.toString(profile.getClicks())));
    }

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
