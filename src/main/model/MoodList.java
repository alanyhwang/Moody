package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of moods
public class MoodList implements Writable {
    private ArrayList<Mood> moodList;   // list of added moods
    private int moodID;                 // ID for current entry

    // EFFECTS: initialize moodList and moodID
    public MoodList() {
        moodList = new ArrayList<>();
        moodID = 1;
    }

    public ArrayList<Mood> getMoodList() {
        return moodList;
    }

    public int getMoodID() {
        return moodID;
    }

    public void setMoodID(int moodID) {
        this.moodID = moodID;
    }

    //MODIFIES: this
    //EFFECTS: add a new mood to moodList, increment moodID by +1
    public void addMood(Mood mood) {
        moodList.add(mood);
        this.moodID += 1;
    }

    //EFFECTS: return the mood in moodList given moodID, else return null
    public Mood findMood(int n) {
        for (Mood m : moodList) {
            if (m.getID() == n) {
                return m;
            }
        }
        return null;
    }

    //REQUIRES: moodID exists in the list
    //MODIFIES: this
    //EFFECTS: delete the mood from moodList given moodID, else return null
    public void deleteMood(int moodID) {
        moodList.remove(findMood(moodID));
    }

    //EFFECTS: produce a list of entries with given moodTag
    public ArrayList<Mood> filterMoodByTag(String moodTag) {
        ArrayList<Mood> moodListWithTag = new ArrayList<>();
        for (Mood m : moodList) {
            if (m.getMoodTag().equals(moodTag)) {
                moodListWithTag.add(m);
            }
        }
        return moodListWithTag;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mood list", moodListToJson());
        json.put("moodID", moodID);
        return json;
    }

    //EFFECTS: return moods in moodList as JSON array
    private JSONArray moodListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Mood m : moodList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
