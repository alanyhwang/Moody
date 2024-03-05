package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: will return a JSON object
    JSONObject toJson();
}
