package test.model;

import model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile profile;

    @BeforeEach
    public void beforeEach() {
        profile = new Profile("Test");
    }

    @Test
    public void hitTest() {
        assertEquals(0, profile.getHits());
        assertEquals(0.0f, profile.getSumOfDistances());
        profile.hit(20.0f);
        assertEquals(1, profile.getHits());
        assertEquals(20.0f, profile.getSumOfDistances());
        profile.hit(100.0f);
        assertEquals(2, profile.getHits());
        assertEquals(120.0f, profile.getSumOfDistances());
    }

    @Test
    public void clickTest() {
        assertEquals(0, profile.getClicks());
        profile.click();
        assertEquals(1, profile.getClicks());
        profile.click();
        assertEquals(2, profile.getClicks());
    }

    @Test
    public void getAccuracyTest() {
        assertEquals(1.0f, profile.getAccuracy());
        profile.hit(20.0f);
        assertEquals(1.0f, profile.getAccuracy());
        profile.click();
        assertEquals(0.5f, profile.getAccuracy());
        profile.hit(0.0f);
        assertEquals(2.0f / 3.0f, profile.getAccuracy());
    }

    @Test
    public void getPrecisionTest() {
        assertEquals(0.0f, profile.getPrecision());
        profile.hit(20.0f);
        assertEquals(20.0f, profile.getPrecision());
        profile.click();
        assertEquals(20.0f, profile.getPrecision());
        profile.hit(15.0f);
        assertEquals(35.0f / 2.0f, profile.getPrecision());
    }

    @Test
    public void getNameTest() {
        assertEquals("Test", profile.getName());
    }
}
