package ui.dialogui;

import model.Event;
import model.EventLog;
import ui.MoodTrackerUI;
import ui.button.EventDialogOkButton;

import javax.swing.*;
import java.awt.*;

// creates JDialog to list all the events
public class EventLogUI {
    private MoodTrackerUI moodTrackerUI;
    private JDialog jdialog;
    private JTextArea eventLogArea;

    public EventLogUI(MoodTrackerUI moodTrackerUI) {
        this.moodTrackerUI = moodTrackerUI;
        makeJDialog();
    }

    // MODIFIES: this
    // EFFECTS: create and initialize new JDialog window
    private void makeJDialog() {
        initializeDialog();
        initializeTextBoxForEventLogs();
        JScrollPane eventLogAreaWithScroll = new JScrollPane(eventLogArea);
        JPanel botButtonPanel = new JPanel();
        new EventDialogOkButton(moodTrackerUI, botButtonPanel, jdialog);
        jdialog.add(eventLogAreaWithScroll, BorderLayout.CENTER);
        jdialog.add(botButtonPanel, BorderLayout.SOUTH);
        jdialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create and initialize new text area to display event logs
    private void initializeTextBoxForEventLogs() {
        eventLogArea = new JTextArea();
        for (Event event : EventLog.getInstance()) {
            eventLogArea.append(event.toString());
        }
        eventLogArea.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: reprint event logs to text area
    public void updateEventLogTextBox() {
        eventLogArea.setText("");
        for (Event event : EventLog.getInstance()) {
            eventLogArea.append(event.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: create and initialize new JDialog
    private void initializeDialog() {
        jdialog = new JDialog(moodTrackerUI, "Event Log", false);
        jdialog.setLayout(new BorderLayout());
        jdialog.setMinimumSize(new Dimension(350, 300));
        jdialog.setLocation(525, 0);
    }
}
