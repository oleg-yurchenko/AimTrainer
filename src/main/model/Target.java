package model;

public class Target {
    private float x;
    private float y;
    private String colour;
    private float radius;

    // Effects: makes a new target with given x and y positions, given colour (hex code) and radius.
    // Modifies: this
    public Target(float x, float y, String colour, float radius) {
        // stub
    }

    // Effects: moves the x value by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    public void moveX(float amount) {
        // stub
    }

    // Effects: moves the y value by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    public void moveY(float amount) {
        // stub
    }

    // Effects: changes the colour of the target to the given colour (hex code)
    // Modifies: this
    // Requires: colour is in hex (Integer.toHexString() was used, or custom hex value)
    public void setColour(String colour) {
        // stub
    }

    // Effects: modifies the radius of the circle by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    //           this.radius - amount >= 0
    public void changeRadius(float amount) {
        // stub
    }

    // Effects: gets the distance from the center of the target to the given point
    public float getDistanceToPoint(float x, float y) {
        return 0.0f; // stub
    }

    // Effects: returns true if the given coordinates are inside the target
    public boolean isHit(float x, float y) {
        return false; // stub
    }

    public float getX() {
        return 0.0f; // stub
    }

    public float getY() {
        return 0.0f; // stub
    }

    public String getColour() {
        return ""; // stub
    }

    public float getRadius() {
        return 0.0f; // stub
    }
}