package persistence;

import model.Event;
import model.EventLog;
import model.MoodList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Modelled from JsonSerializationDemo
// Represents a writer that writes MoodList JSON to file
public class JsonWriter {
    private static final int INDENT = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: initialize file destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; if no destination file, throws FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes MoodList in JSON to file
    public void write(MoodList ml) {
        JSONObject json = ml.toJson();
        saveToFile(json.toString(INDENT));
        EventLog.getInstance().logEvent(new Event("Entries saved to moodList.json.\n\n"));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes json in string form to json file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
