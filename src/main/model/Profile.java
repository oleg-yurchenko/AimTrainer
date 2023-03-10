package model;

// Represents a profile which has a name and keeps track of the user's statistics
public class Profile {
    private String name; // username
    private int hits; // number of times target was hit
    private int clicks; // number of times user clicked while in game
    private float sumOfDistances; // sum of distances to target (divided by hits to get precision)

    // Effects: creates a profile with given string name and 0 hits, clicks and sumOfDistances.
    // Modifies: this
    public Profile(String name) {
        this.name = name;
        this.hits = 0;
        this.clicks = 0;
        this.sumOfDistances = 0.0f;
    }

    // Effects: creates a profile with the given name and statistics.
    // Modifies: this
    public Profile(String name, int hits, int clicks, float sumOfDistances) {
        this.name = name;
        this.hits = hits;
        this.clicks = clicks;
        this.sumOfDistances = sumOfDistances;
    }

    // Effects: adds 1 to this.hits, this.clicks, and adds the distance to sumOfDistances
    // Modifies: this
    // Requires: distance >= 0 (must be absolute value)
    public void hit(float distance) {
        hits++;
        clicks++;
        sumOfDistances += distance;
    }

    // Effects: adds 1 to this.clicks
    // Modifies: this
    public void click() {
        clicks++;
    }

    // Effects: produces the accuracy (hits/clicks)
    public float getAccuracy() {
        if (clicks == 0) {
            return 1.0f;
        }
        return (float) hits / (float) clicks;
    }

    // Effects: produces the precision (sumOfDistances/hits) ; average distance to center per hit
    public float getPrecision() {
        if (hits == 0) {
            return 0.0f;
        }
        return sumOfDistances / (float) hits;
    }

    // Effects: resets all the data in this profile except for the name.
    // Modifies: this
    public void resetProfile() {
        hits = 0;
        clicks = 0;
        sumOfDistances = 0;
    }

    public String getName() {
        return name;
    }

    public int getHits() {
        return hits;
    }

    public int getClicks() {
        return clicks;
    }

    public float getSumOfDistances() {
        return sumOfDistances;
    }
}