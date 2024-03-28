package ui;

import model.Mood;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDoneButton extends Button {
    private AddMoodDialogUI addMoodDialogUI;

    public AddDoneButton(MoodTrackerUI moodTrackerUI, JComponent parent, AddMoodDialogUI addMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.addMoodDialogUI = addMoodDialogUI;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Done")) {
                    Mood m = addMoodDialogUI.constructNewMood();
                    if (m != null) {
                        moodTrackerUI.addNewMoodToMoodList(m);
                        addMoodDialogUI.disposeDialog();
                    }
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Done");
    }
}
