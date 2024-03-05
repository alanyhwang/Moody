package persistence;

import model.Mood;
import model.MoodList;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileDontExist() {
        JsonReader reader = new JsonReader("./data/fileDoesntExist.json");
        try {
            MoodList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyMoodList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMoodList.json");
        try {
            MoodList ml = reader.read();
            assertEquals(1, ml.getMoodID());
            assertEquals(Arrays.asList(), ml.getMoodList());
        } catch (IOException e) {
            fail("Unexpected unable to read from file");
        }
    }

    @Test
    void testReaderMoodListAllTags() {
        JsonReader reader = new JsonReader("./data/testReaderMoodListAllTags.json");
        try {
            MoodList ml = reader.read();
            assertEquals(8, ml.getMoodID());
            List<Mood> moods = ml.getMoodList();
            assertEquals(6, moods.size());
            checkMood(1, LocalDate.parse("1991-01-01"), "Happy", "Positive", "VICTORY ROYALE YAY", new Color(0, 204, 0), "\u001b[38;5;34m", moods.get(0));
            checkMood(3, LocalDate.parse("1992-02-02"), "Not Bad", "Positive Neutral", "playing games", new Color(102, 255, 102), "\u001b[38;5;10m", moods.get(1));
            checkMood(4, LocalDate.parse("1993-03-03"), "OK", "Neutral", "sleeping in bed zzz", new Color(255, 255, 255), "\u001b[0m", moods.get(2));
            checkMood(5, LocalDate.parse("1994-04-04"), "Sad", "Negative Neutral", "Imma go cry now", new Color(255, 102, 102), "\u001b[38;5;9m", moods.get(3));
            checkMood(6, LocalDate.parse("1995-05-05"), "Angry", "Negative", "IM SUPER ANGRY.", new Color(255, 0, 0), "\u001b[38;5;160m", moods.get(4));
            checkMood(7, LocalDate.parse("1996-06-06"), "huh", "default", "fake note", new Color(211, 211, 211), "\u001b[37m", moods.get(5));
        } catch (IOException e) {
            fail("Unexpected unable to read from file");
        }
    }
}
