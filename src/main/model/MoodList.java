package model;

import java.util.ArrayList;

// Represents a list of moods
public class MoodList {
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

    //MODIFIES: this
    //EFFECTS: add a new mood to moodList, increment moodID by +1
    public void addMood(Mood mood) {
        moodList.add(mood);
        this.moodID += 1;
    }

    //REQUIRES: moodID exists in the list
    //EFFECTS: find the mood in moodList given moodID, else return null
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

    //REQUIRES: a non-empty moodList
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
}
