package ui.button;

import ui.dialogui.EditMoodDialogUI;
import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button cancels editing to selected mood
public class EditCancelButton extends Button {

    private EditMoodDialogUI editMoodDialogUI;

    public EditCancelButton(MoodTrackerUI moodTrackerUI, JComponent parent, EditMoodDialogUI editMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.editMoodDialogUI = editMoodDialogUI;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Cancel")) {
                editMoodDialogUI.cancelDialog();
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