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
    public moveX(float amount) {
        // stub
    }

    // Effects: moves the y value by the given amount
    // Modifies: this
    public moveY(float amount) {
        // stub
    }

    // Effects: changes the colour of the target to the given colour (hex code)
    // Modifies: this
    public void setColour(String colour) {
        // stub
    }

    // Effects: modifies the radius of the circle by the given amount
    // Modifies: this
    public void changeRadius(float amount) {
        // stub
    }

    // Effects: gets the distance from the center of the target to the given point
    public float getDistanceToPoint(float x, float y) {
        // stub
    }

    // Effects: returns the x position
    public float getX() {
        // stub
    }

    //Effects: returns the y position
    public float getY() {
        // stub
    }

    //Effects: returns the colour
    public String getColour() {
        // stub
    }

    // Effects: return the radius of the target
    public float getRadius() {
        // stub
    }
}