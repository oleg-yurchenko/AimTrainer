package test.model;

import model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TargetTest {
    private Target target1;
    private Target target2;

    @BeforeEach
    public void beforeEach() {
        target1 = new Target(0.0f, 0.0f, Integer.toHexString(0), 5.0f);
        target2 = new Target(100.0f, 30.0f, Integer.toHexString(255), 2.0f);
    }

    @Test
    public void moveXTest() {
        target1.moveX(20.0f);
        assertEquals(20.0f, target1.getX());
        target1.moveX(-5.0f);
        assertEquals(15.0f, target1.getX());

        target2.moveX(-50.0f);
        assertEquals(150.0f, target2.getX());
        target2.moveX(25.0f);
        assertEquals(175.0f, target2.getX());
    }

    @Test
    public void moveYTest() {
        target1.moveY(10.0f);
        assertEquals(10.0f, target1.getY());
        target1.moveY(-0.1f);
        assertEquals(9.9f, target1.getY());

        target2.moveY(-15.5f);
        assertEquals(14.5f, target2.getY());
        target2.moveY(20.2f);
        assertEquals(34.7f, target2.getY());
    }

    @Test
    public void setColourTest() {
        target1.setColour("FF0000");
        assertEquals("FF0000", target1.getColour());
        target1.setColour("00FFFF");
        assertEquals("00FFFF", target1.getColour());

        target2.setColour("FFFFFF");
        assertEquals("FFFFFF", target2.getColour());
        target2.setColour("123456");
        assertEquals("123456", target2.getColour());
    }

    @Test
    public void changeRadiusTest() {
        target1.changeRadius(-1.0f);
        assertEquals(4.0f, target1.getRadius());
        target1.changeRadius(2.0f);
        assertEquals(6.0f, target1.getRadius());

        target2.changeRadius(-0.5f);
        assertEquals(1.5f, target2.getRadius());
        target2.changeRadius(0.1f);
        assertEquals(1.6f, target2.getRadius());
        target2.changeRadius(-2.0f);
        assertEquals(0.0f, target2.getRadius());
    }

    @Test
    public void getDistanceToPointTest() {
        assertEquals(0.0f, target1.getDistanceToPoint(0.0f, 0.0f));
        assertEquals(1.0f, target1.getDistanceToPoint(1.0f, 0.0f));
        assertEquals(Math.sqrt(2), target1.getDistanceToPoint(1.0f, 1.0f));

        assertEquals(0.0f, target2.getDistanceToPoint(100.0f, 30.0f));
        assertEquals(Math.sqrt(Math.pow(80, 2) + Math.pow(10, 2)), target2.getDistanceToPoint(20.0f, 20.0f));
    }

    @Test
    public void isHitTest() {
        assertTrue(target1.isHit(0.0f, 0.0f));
        assertTrue(target1.isHit(1.0f, 1.0f));
        assertTrue(target1.isHit(4.9f, 0.0f));
        assertTrue(target1.isHit(5.0f, 0.0f));
        assertFalse(target1.isHit(5.1f, 0.0f));

        assertTrue(target2.isHit(100.0f, 30.0f));
        assertTrue(target2.isHit(100.0f,31.9f));
        assertTrue(target2.isHit(100.0f,32.0f));
        assertFalse(target2.isHit(100.0f,32.1f));

        assertTrue(target1.isHit(1.0f, 1.0f));
        assertFalse(target1.isHit(10.0f, 10.0f));
    }
}
