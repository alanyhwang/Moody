package ui.dialogui;

import model.Mood;
import ui.MoodTrackerUI;
import ui.button.AddCancelButton;
import ui.button.AddDoneButton;

import javax.swing.*;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

// Extract constructor information to create new mood from DialogUI
public class AddMoodDialogUI {

    protected static final String[] moodTagChoices =
            { "Positive","Positive Neutral", "Neutral","Negative Neutral","Negative"};

    protected JDialog dialog;
    protected MoodTrackerUI moodTrackerUI;
    protected int moodID;
    protected LocalDate date;
    protected String mood;
    protected String moodTag;
    protected String note;
    protected JTextArea noteTextArea;
    protected JComboBox<String> moodTagComboBox;
    protected JTextField moodTextField;
    protected JTextField yearTextField;
    protected JTextField monthTextField;
    protected JTextField dayTextField;


    public AddMoodDialogUI(MoodTrackerUI moodTrackerUI) {
        this.moodTrackerUI = moodTrackerUI;
        resetMoodParameters();
        makeJDialog();
    }

    private void resetMoodParameters() {
        moodID = 0;
        date = null;
        mood = null;
        moodTag = null;
        note = null;
    }

    protected void makeJDialog() {
        JDialog d = initializeDialog();
        JPanel centerPanel = initializeCenterPanel();
        JPanel centerTopPanel = initializeCenterPanel();
        JPanel centerBotPanel = initializeCenterPanel();
        centerPanel.add(centerTopPanel);
        centerPanel.add(centerBotPanel);
        addDatePanel(centerTopPanel);
        addMoodPanel(centerTopPanel);
        addMoodTagPanel(centerTopPanel);
        addNotePanel(centerBotPanel);
        addDialogButtons(d);
        d.add(centerPanel);
        d.setVisible(true);
    }

    protected JPanel initializeCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
        return centerPanel;
    }

    protected void addDialogButtons(JDialog d) {
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
        new AddDoneButton(moodTrackerUI, p5, this);
        new AddCancelButton(moodTrackerUI, p5, this);
        if (d != null) {
            d.add(p5, BorderLayout.SOUTH);
        }
    }

    protected void addNotePanel(JPanel p) {
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(0, 1));
        JLabel noteLabel = new JLabel("Note: ");
        noteLabel.setVisible(true);
        p4.add(noteLabel);
        noteTextArea = new JTextArea(50, 10);
        noteTextArea.setLineWrap(true);
        noteTextArea.setVisible(true);
        p4.add(noteTextArea);
        p.add(p4);
    }

    protected void addMoodTagPanel(JPanel p) {
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel moodTagLabel = new JLabel("MoodTag: ");
        moodTagLabel.setVisible(true);
        p3.add(moodTagLabel);
        moodTagComboBox = new JComboBox<>(moodTagChoices);
        p3.add(moodTagComboBox);
        p3.setVisible(true);
        p.add(p3);
    }

    protected void addMoodPanel(JPanel p) {
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel moodLabel = new JLabel("Mood: ");
        moodLabel.setVisible(true);
        p2.add(moodLabel);
        moodTextField = new JTextField(10);
        p2.add(moodTextField);
        p2.setVisible(true);
        p.add(p2);
    }

    protected void addDatePanel(JPanel p) {
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel dateLabel = new JLabel("Date(YYYY/MM/DD): ", JLabel.LEFT);
        dateLabel.setVisible(true);
        p1.add(dateLabel);
        yearTextField = new JTextField(3);
        monthTextField = new JTextField(2);
        dayTextField = new JTextField(2);
        p1.add(yearTextField);
        p1.add(monthTextField);
        p1.add(dayTextField);
        p1.setVisible(true);
        p.add(p1);
    }

    protected JDialog initializeDialog() {
        this.dialog = new JDialog(moodTrackerUI, "New Mood Entry", true);
        dialog.setLayout(new BorderLayout());
        dialog.setMinimumSize(new Dimension(250, 300));
        dialog.setLocation(500, 0);
        return dialog;
    }

    public void cancelDialog() {
        resetMoodParameters();
        disposeDialog();
    }

    public Mood constructNewMood() {
        Mood m = null;
        convertJComponentsToMoodConstructors();
        if (moodID != 0 && date != null && moodTag != null) {
            m = new Mood(moodID, date, mood, moodTag, note);
        }
        return m;
    }

    private void convertJComponentsToMoodConstructors() {
        try {
            String yearText = getYearText();
            String monthText = getMonthText();
            String dayText = getDayText();
            String dateString = yearText + "-" + monthText + "-" + dayText;
            date = LocalDate.parse(dateString);
            note = noteTextArea.getText();
            moodTag = Objects.requireNonNull(moodTagComboBox.getSelectedItem()).toString();
            mood = moodTextField.getText();
            moodID = moodTrackerUI.getMoodList().getMoodID();
        } catch (DateTimeException e) {
            System.out.print("Date Exception");
            String name = "Invalid Date Error";
            String message = "Date is invalid. Choose another date.";
            new MessageDialogUI(moodTrackerUI, name, message, false);
        }
    }

    public void disposeDialog() {
        dialog.dispose();
    }

    protected String getYearText() {
        String yearFromText = yearTextField.getText();
        if (yearFromText.length() == 1) {
            String yearText = "000" + yearFromText;
            return yearText;
        }
        if (yearFromText.length() == 2) {
            String yearText = "00" + yearFromText;
            return yearText;
        }
        if (yearFromText.length() == 3) {
            String yearText = "0" + yearFromText;
            return yearText;
        }
        return yearFromText;
    }

    protected String getDayText() {
        if (dayTextField.getText().length() == 1) {
            String dayText = "0" + dayTextField.getText();
            return dayText;
        }
        return dayTextField.getText();
    }

    protected String getMonthText() {
        if (monthTextField.getText().length() == 1) {
            String monthText = "0" + monthTextField.getText();
            return monthText;
        }
        return monthTextField.getText();
    }
}
