package persistence;

import model.Mood;
import model.MoodList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

// Modelled from JsonSerializationDemo
// Represents a reader that reads MoodList from JSON file
public class JsonReader {
    private String source;

    // EFFECTS: initializes source from JSON file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads moodList from JSON file and returns it;
    // if reading data fails, throws IOException
    public MoodList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMoodList(jsonObject);
    }

    // EFFECTS: read source file as string and returns it
    // if reading fails, throws IOException
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MoodList fromJSON object and returns MoodList
    private MoodList parseMoodList(JSONObject jsonObject) {
        MoodList ml = new MoodList();
        addMoods(ml, jsonObject);
        ml.setMoodID(jsonObject.getInt("moodID"));
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses Moods from JSON object and adds them to MoodList
    private void addMoods(MoodList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("mood list");
        for (Object json : jsonArray) {
            JSONObject mood = (JSONObject) json;
            addMood(ml, mood);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses Mood from JSON object and adds it to MoodList
    private void addMood(MoodList ml, JSONObject jsonObject) {
        int entryID = jsonObject.getInt("entryID");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        String mood = jsonObject.getString("mood");
        String moodTag = jsonObject.getString("moodTag");
        String note = jsonObject.getString("note");
        Mood moodObject = new Mood(entryID, date, mood, moodTag, note);
        ml.addMood(moodObject);
    }
}
