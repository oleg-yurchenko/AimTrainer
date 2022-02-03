package model;

public class Profile {
    // Getters for hits, clicks and sumOfDistances are not being created because
    // we will never need this values (instead we have getters taht return precision and accuracy)

    private String name; // username
    private int hits; // number of times target was hit
    private int clicks // number of times user clicked while in game
    private float sumOfDistances; // sum of distances to target (divided by hits to get precision)

    // Effects: creates a profile with 0 accuracy and precision and a given name
    // Modifies: this
    public Profile(String name) {
        // stub
    }

    // Effects: adds 1 to this.hits and adds the distance to sumOfDistances
    // Modifies: this
    // Requires: distance >= 0 (must be absolute value)
    public void hit(float distance) {
        // stub
    }

    // Effects: adds 1 to this.clicks
    // Modigies: this
    public void click() {
        // stub
    }

    // Effects: produces the accuracy (hits/clicks)
    public float getAccuracy() {
        // stub
    }

    // Effects: produces the precision (sumOfDistances/hits) ; average distance to center per hit
    public float getPrecision() {
        // stub
    }

    // Effects: returns the username of the profile
    public String getName() {
        // stub
    }
}