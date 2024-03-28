package ui.button;

import ui.dialogui.AddMoodDialogUI;
import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCancelButton extends Button {
    private AddMoodDialogUI addMoodDialogUI;

    public AddCancelButton(MoodTrackerUI moodTrackerUI, JComponent parent, AddMoodDialogUI addMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.addMoodDialogUI = addMoodDialogUI;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Cancel")) {
                    addMoodDialogUI.cancelDialog();
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Cancel");
    }
}
