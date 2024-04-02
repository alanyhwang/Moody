package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MoodListTest {
    private MoodList moodList;
    private Mood mood1;
    private Mood mood2;
    private Mood mood3;
    private Mood mood4;
    private Mood mood5;
    private LocalDate date1;
    private LocalDate date2;
    private LocalDate date3;
    private LocalDate date4;
    private LocalDate date5;

    @BeforeEach
    void runBefore() {
        date1 = LocalDate.of (2024, 1, 1);
        date2 = LocalDate.of (2024, 2, 2);
        date3 = LocalDate.of (2024, 3, 3);
        date4 = LocalDate.of (2024, 4, 4);
        date5 = LocalDate.of (2024, 5, 5);
        mood1 = new Mood (1, date1, "Happy", "Positive", "I ate cake.");
        mood2 = new Mood (2, date2, "Smiling", "Positive Neutral", "Cool.");
        mood3 = new Mood (3, date3, "OK", "Neutral", "Chilling.");
        mood4 = new Mood (4, date4, "Frown", "Negative Neutral", "Melting on bed.");
        mood5 = new Mood (5, date5, "Sad", "Negative", "My dog died!");
        moodList = new MoodList();
        assertEquals(1, moodList.getMoodID());
        moodList.addMood(mood1, false);
        moodList.addMood(mood2, false);
        moodList.addMood(mood3, false);
        moodList.addMood(mood4, false);
        moodList.addMood(mood5, false);
    }

    @Test
    void testConstructor() {
        assertEquals(6, moodList.getMoodID());
        assertEquals(Arrays.asList(mood1, mood2, mood3, mood4, mood5), moodList.getMoodList());
    }

    @Test
    void testAddMood() {
        moodList.addMood(mood1, false);
        assertEquals(7, moodList.getMoodID());
        assertEquals(Arrays.asList(mood1, mood2, mood3, mood4, mood5, mood1), moodList.getMoodList());
        moodList.addMood(mood2, true);
        String expectedEventLog = EventLog.getInstance().iterator().next().getDate().toString() +
                "\nMood added! \n" +
                "ID: 1 at Date: 2024-01-01\n\n";
        assertEquals(expectedEventLog, EventLog.getInstance().iterator().next().toString());
    }

    @Test
    void testFindMood() {
        assertEquals(mood1, moodList.findMood(1));
        assertEquals(mood3, moodList.findMood(3));
        assertNull(moodList.findMood(6));
    }

    @Test
    void testDeleteMood() {
        assertEquals(Arrays.asList(mood1, mood2, mood3, mood4, mood5), moodList.getMoodList());
        moodList.deleteMood(3);
        assertEquals(Arrays.asList(mood1, mood2, mood4, mood5), moodList.getMoodList());
        moodList.deleteMood(1);
        assertEquals(Arrays.asList(mood2, mood4, mood5), moodList.getMoodList());
    }

    @Test
    void testFilterMoodByTag() {
        Mood mood6 = new Mood (6, date1, "Omega Happy", "Positive", "My cat lived!");
        Mood mood7 = new Mood (7, date1, "Slight Happy", "Positive Neutral", "I ate bread!");
        Mood mood8 = new Mood (8, date1, "Aight", "Neutral", "Sleeping.");
        Mood mood9 = new Mood (9, date1, "Slight Sad", "Negative Neutral", "Tripped.");
        Mood mood10 = new Mood (10, date1, "Omega Sad", "Negative", "My cat died!");
        moodList.addMood(mood6, false);
        moodList.addMood(mood7, false);
        moodList.addMood(mood8, false);
        moodList.addMood(mood9, false);
        moodList.addMood(mood10, false);
        assertEquals(Arrays.asList(mood1, mood6), moodList.filterMoodByTag("Positive"));
        assertEquals(Arrays.asList(mood2, mood7), moodList.filterMoodByTag("Positive Neutral"));
        assertEquals(Arrays.asList(mood3, mood8), moodList.filterMoodByTag("Neutral"));
        assertEquals(Arrays.asList(mood4, mood9), moodList.filterMoodByTag("Negative Neutral"));
        assertEquals(Arrays.asList(mood5, mood10), moodList.filterMoodByTag("Negative"));
    }
}