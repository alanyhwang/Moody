package ui;

import model.Mood;

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
                    } catch (Exception er) {
                        System.out.println("Date exception");
                    }
                    editMoodDialogUI.cancelDialog();
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Confirm");
    }
}
