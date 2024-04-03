package ui.button;

import ui.dialogui.AddMoodDialogUI;
import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button cancels effects of add button
public class AddCancelButton extends Button {

    private AddMoodDialogUI addMoodDialogUI;

    public AddCancelButton(MoodTrackerUI moodTrackerUI, JComponent parent, AddMoodDialogUI addMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.addMoodDialogUI = addMoodDialogUI;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Cancel")) {
                addMoodDialogUI.cancelDialog();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Cancel");
    }
}
