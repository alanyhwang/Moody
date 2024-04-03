package ui.button;

import model.Mood;
import ui.dialogui.AddMoodDialogUI;
import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button finishes effects of add button
public class AddDoneButton extends Button {

    private AddMoodDialogUI addMoodDialogUI;

    public AddDoneButton(MoodTrackerUI moodTrackerUI, JComponent parent, AddMoodDialogUI addMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.addMoodDialogUI = addMoodDialogUI;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Done")) {
                Mood m = addMoodDialogUI.constructNewMood();
                if (m != null) {
                    moodTrackerUI.addNewMoodToMoodList(m);
                    addMoodDialogUI.disposeDialog();
                }
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Done");
    }
}
