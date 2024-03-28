package ui.dialogui;

import model.Mood;
import ui.MoodTrackerUI;
import ui.button.EditCancelButton;
import ui.button.EditConfirmButton;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;

// creates JDialog to edit mood
public class EditMoodDialogUI extends AddMoodDialogUI {
    private Mood moodObject;

    public EditMoodDialogUI(MoodTrackerUI moodTrackerUI, Mood m) {
        super(moodTrackerUI);
        moodObject = m;
        moodID = m.getID();
        date = m.getDate();
        mood = m.getMood();
        moodTag = m.getMoodTag();
        note = m.getNote();
        fillInFields();
        dialog.setVisible(true);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: creates new JDialog with empty fields
    protected void makeJDialog() {
        initializeDialog();
        JPanel centerPanel = initializeCenterPanel();
        JPanel centerTopPanel = initializeCenterPanel();
        JPanel centerBotPanel = initializeCenterPanel();
        centerPanel.add(centerTopPanel);
        centerPanel.add(centerBotPanel);
        addDatePanel(centerTopPanel);
        addMoodPanel(centerTopPanel);
        addMoodTagPanel(centerTopPanel);
        addNotePanel(centerBotPanel);
        addDialogButtons();
        dialog.add(centerPanel);
    }

    // MODIFIES: this
    // EFFECTS: add buttons to JDialog
    @Override
    protected void addDialogButtons() {
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
        new EditConfirmButton(moodTrackerUI, p5, this);
        new EditCancelButton(moodTrackerUI, p5, this);
        if (dialog != null) {
            dialog.add(p5, BorderLayout.SOUTH);
        }
    }

    // MODIFIES: this
    // EFFECTS: fill all JComponents with currently selected mood
    private void fillInFields() {
        noteTextArea.setText(note);
        moodTagComboBox.setSelectedIndex(Arrays.asList(moodTagChoices).indexOf(moodTag));
        moodTextField.setText(String.valueOf(mood));
        yearTextField.setText(String.valueOf(date.getYear()));
        monthTextField.setText(String.valueOf(date.getMonthValue()));
        dayTextField.setText(String.valueOf(date.getDayOfMonth()));
    }

    // MODIFIES: this
    // EFFECTS: update current mood with components in current JDialog
    public void updateMood() {
        moodObject.setMood(moodTextField.getText(), moodTagComboBox.getSelectedItem().toString());
        moodObject.setNote(noteTextArea.getText());
        String yearText = getYearText();
        String monthText = getMonthText();
        String dayText = getDayText();
        String dateString = yearText + "-" + monthText + "-" + dayText;
        LocalDate newDate = LocalDate.parse(dateString);
        moodObject.setDate(newDate);
        moodTrackerUI.resetTableToMoodList();
    }
}
