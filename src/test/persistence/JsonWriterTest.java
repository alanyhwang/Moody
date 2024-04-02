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

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MoodList ml = new MoodList();
            JsonWriter writer = new JsonWriter("./data/illegal*FileName.json");
            writer.open();
            fail("IOException was expected, illegal file name");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testWriterMoodListNoEntry() {
        try {
            MoodList ml = new MoodList();
            JsonWriter writer = new JsonWriter("./data/testWriterMoodListNoEntry.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMoodListNoEntry.json");
            ml = reader.read();
            assertEquals(1, ml.getMoodID());
            assertEquals(Arrays.asList(), ml.getMoodList());

        } catch (IOException e) {
            fail("IOException thrown unexpectedly");
        }
    }

    @Test
    void testWriterMoodListAllTag() {
        try {
            MoodList ml = new MoodList();
            ml.addMood(new Mood(1, LocalDate.parse("1991-01-01"), "Happy", "Positive", "VICTORY ROYALE YAY"), false);
            ml.addMood(new Mood(2, LocalDate.parse("1992-02-02"), "Not Bad", "Positive Neutral", "playing games"), false);
            ml.addMood(new Mood(3, LocalDate.parse("1993-03-03"), "OK", "Neutral", "sleeping in bed zzz"), false);
            ml.addMood(new Mood(4, LocalDate.parse("1994-04-04"), "Sad", "Negative Neutral", "Imma go cry now"), false);
            ml.addMood(new Mood(5, LocalDate.parse("1995-05-05"), "Angry", "Negative", "IM SUPER ANGRY."), false);
            ml.addMood(new Mood(6, LocalDate.parse("1996-06-06"), "huh", "default", "fake note"), false);

            JsonWriter writer = new JsonWriter("./data/testWriterMoodListAllTag.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMoodListAllTag.json");
            ml = reader.read();
            assertEquals(7, ml.getMoodID());
            List<Mood> moods = ml.getMoodList();
            assertEquals(6, moods.size());
            checkMood(1, LocalDate.parse("1991-01-01"), "Happy", "Positive", "VICTORY ROYALE YAY", new Color(82, 228, 117), "\u001b[38;5;34m", moods.get(0));
            checkMood(2, LocalDate.parse("1992-02-02"), "Not Bad", "Positive Neutral", "playing games", new Color(169, 236, 185), "\u001b[38;5;10m", moods.get(1));
            checkMood(3, LocalDate.parse("1993-03-03"), "OK", "Neutral", "sleeping in bed zzz", new Color(222, 218, 218), "\u001b[0m", moods.get(2));
            checkMood(4, LocalDate.parse("1994-04-04"), "Sad", "Negative Neutral", "Imma go cry now", new Color(245, 185, 185), "\u001b[38;5;9m", moods.get(3));
            checkMood(5, LocalDate.parse("1995-05-05"), "Angry", "Negative", "IM SUPER ANGRY.", new Color(239, 144, 148), "\u001b[38;5;160m", moods.get(4));
            checkMood(6, LocalDate.parse("1996-06-06"), "huh", "default", "fake note", new Color(211, 211, 211), "\u001b[37m", moods.get(5));

        } catch (IOException e) {
            fail("IOException thrown unexpectedly");
        }
    }
}
