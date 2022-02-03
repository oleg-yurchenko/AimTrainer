package model;

public class Profile {
    private String name; // username
    private int hits; // number of times target was hit
    private int clicks; // number of times user clicked while in game
    private float sumOfDistances; // sum of distances to target (divided by hits to get precision)

    // Effects: creates a profile with given string name and 0 hits, clicks and sumOfDistances.
    // Modifies: this
    public Profile(String name) {
        // stub
    }

    // Effects: adds 1 to this.hits and adds the distance to sumOfDistances
    // Modifies: this
    // Requires: distance    >= 0 (must be absolute value)
    //           this.hits+1 <= this.clicks (function calling hit must also call click)
    public void hit(float distance) {
        // stub
    }

    // Effects: adds 1 to this.clicks
    // Modifies: this
    public void click() {
        // stub
    }

    // Effects: produces the accuracy (hits/clicks)
    public float getAccuracy() {
        return 0.0f; // stub
    }

    // Effects: produces the precision (sumOfDistances/hits) ; average distance to center per hit
    public float getPrecision() {
        return 0.0f; // stub
    }

    public String getName() {
        return ""; // stub
    }

    public int getHits() {
        return 0; // stub
    }

    public int getClicks() {
        return 0; // stub
    }

    public float getSumOfDistances() {
        return 0; // stub
    }
}