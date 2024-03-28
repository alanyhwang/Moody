package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterConfirmButton extends Button {
    private FilterMoodDialogUI filterMoodDialogUI;

    public FilterConfirmButton(MoodTrackerUI mtUI, JComponent parent, FilterMoodDialogUI filterMoodDialogUI) {
        super(mtUI, parent);
        this.filterMoodDialogUI = filterMoodDialogUI;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Confirm")) {
                    String moodTag = filterMoodDialogUI.getSelectedMoodTag();
                    moodTrackerUI.updateFilteredMoodTable(moodTag);
                    filterMoodDialogUI.disposeDialog();
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Confirm");
    }
}
