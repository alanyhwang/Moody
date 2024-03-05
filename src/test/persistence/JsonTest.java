package persistence;

import model.Mood;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMood(int id,
                             LocalDate date,
                             String mood,
                             String moodTag,
                             String note,
                             Color Color,
                             String ansiColor,
                             Mood moodObject) {
        assertEquals(id, moodObject.getID());
        assertEquals(date, moodObject.getDate());
        assertEquals(mood, moodObject.getMood());
        assertEquals(moodTag, moodObject.getMoodTag());
        assertEquals(note, moodObject.getNote());
        assertEquals(Color, moodObject.getMoodColor());
        assertEquals(ansiColor, moodObject.getAnsiColor());
    }
}
