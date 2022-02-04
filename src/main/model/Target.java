package model;

public class Target {
    private float posX;
    private float posY;
    private String colour;
    private float radius;

    // Effects: makes a new target with given x and y positions, given colour (hex code) and radius.
    // Modifies: this
    public Target(float x, float y, String colour, float radius) {
        this.posX = x;
        this.posY = y;
        this.colour = colour;
        this.radius = radius;
    }

    // Effects: moves the x value by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    public void moveX(float amount) {
        this.posX += amount;
    }

    // Effects: moves the y value by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    public void moveY(float amount) {
        this.posY += amount;
    }

    // Effects: changes the colour of the target to the given colour (hex code)
    // Modifies: this
    // Requires: colour is in hex (Integer.toHexString() was used, or custom hex value)
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Effects: modifies the radius of the circle by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    //           this.radius - amount >= 0
    public void changeRadius(float amount) {
        this.radius += amount;
    }

    // Effects: gets the distance from the center of the target to the given point
    public float getDistanceToPoint(float x, float y) {
        return 0.0f; // stub
    }

    // Effects: returns true if the given coordinates are inside the target
    public boolean isHit(float x, float y) {
        return false; // stub
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public String getColour() {
        return colour;
    }

    public float getRadius() {
        return radius;
    }
}