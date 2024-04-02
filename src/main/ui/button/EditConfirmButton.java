package ui.button;

import ui.dialogui.EditMoodDialogUI;
import ui.MoodTrackerUI;
import ui.dialogui.MessageDialogUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button confirms editing to selected mood
public class EditConfirmButton extends Button {

    private EditMoodDialogUI editMoodDialogUI;

    public EditConfirmButton(MoodTrackerUI moodTrackerUI, JComponent parent, EditMoodDialogUI editMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.editMoodDialogUI = editMoodDialogUI;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Confirm")) {
                try {
                    editMoodDialogUI.updateMood();
                    editMoodDialogUI.cancelDialog();
                } catch (Exception er) {
                    System.out.println("Date exception");
                    String name = "Invalid Date Error";
                    String message = "Date is invalid. Choose another date.";
                    new MessageDialogUI(moodTrackerUI, name, message, false);
                }
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Confirm");
    }
}
