package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCancelButton extends Button {

    private EditMoodDialogUI editMoodDialogUI;

    public EditCancelButton(MoodTrackerUI moodTrackerUI, JComponent parent, EditMoodDialogUI editMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.editMoodDialogUI = editMoodDialogUI;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Cancel")) {
                    editMoodDialogUI.cancelDialog();
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Cancel");
    }
}