package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoodTest {
    private Mood testMood;
    private LocalDate date1;
    private LocalDate date2;

    @BeforeEach
    void runBefore() {
        date1 = LocalDate.of (2024, 1, 15);
        testMood = new Mood (1, date1, "Happy", "Positive", "I ate cake.");
    }

    @Test
    void testConstructor() {
        assertEquals(date1, testMood.getDate());
        assertEquals("Happy", testMood.getMood());
        assertEquals("Positive", testMood.getMoodTag());
        assertEquals(new Color(0, 204, 0), testMood.getMoodColor());
        assertEquals("I ate cake.", testMood.getNote());
        assertEquals(1, testMood.getID());
    }

    @Test
    public void testSetDate() {
        date2 = LocalDate.of (2025, 2, 25);
        testMood.setDate(date2);
        assertEquals(date2, testMood.getDate());
    }

    @Test
    public void testSetMood() {
        testMood.setMood("Sad", "Negative");
        assertEquals("Sad", testMood.getMood());
    }

    @Test
    public void testSetNote() {
        testMood.setNote("I am sooooo sad.");
        assertEquals("I am sooooo sad.", testMood.getNote());
    }

    @Test
    public void testAddNote() {
        testMood.addNote("Just kidding.");
        assertEquals("I ate cake. Just kidding.", testMood.getNote());
    }

    @Test
    public void testMoodColors() {
        assertEquals(new Color(0, 204, 0), testMood.getMoodColor());
        testMood.setMood("Aight", "Positive Neutral");
        assertEquals(new Color(102, 255, 102), testMood.getMoodColor());
        testMood.setMood("OK", "Neutral");
        assertEquals(new Color(255, 255, 255), testMood.getMoodColor());
        testMood.setMood("down", "Negative Neutral");
        assertEquals(new Color(255, 102, 102), testMood.getMoodColor());
        testMood.setMood("sad", "Negative");
        assertEquals(new Color(255, 0, 0), testMood.getMoodColor());
        testMood.setMood("none", "Nothing");
        assertEquals(new Color(211, 211, 211), testMood.getMoodColor());
    }

    @Test
    public void testMoodAnsiColors() {
        assertEquals("\u001b[36m", testMood.getAnsiColor());
        testMood.setMood("Aight", "Positive Neutral");
        assertEquals("\u001b[32m", testMood.getAnsiColor());
        testMood.setMood("OK", "Neutral");
        assertEquals("\u001b[0m", testMood.getAnsiColor());
        testMood.setMood("down", "Negative Neutral");
        assertEquals("\u001b[35m", testMood.getAnsiColor());
        testMood.setMood("sad", "Negative");
        assertEquals("\u001b[31m", testMood.getAnsiColor());
        testMood.setMood("none", "Nothing");
        assertEquals("\u001b[37m", testMood.getAnsiColor());
    }
}