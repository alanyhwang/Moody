package ui.button;

import ui.dialogui.EditMoodDialogUI;
import ui.MoodTrackerUI;
import ui.dialogui.MessageDialogUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditConfirmButton extends Button {

    private EditMoodDialogUI editMoodDialogUI;

    public EditConfirmButton(MoodTrackerUI moodTrackerUI, JComponent parent, EditMoodDialogUI editMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.editMoodDialogUI = editMoodDialogUI;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Confirm");
    }
}
