package ui.dialogui;

import ui.MoodTrackerUI;
import ui.button.DialogOkButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

// creates JDialog to show message
public class MessageDialogUI {
    private MoodTrackerUI moodTrackerUI;
    private String name;
    private String message;
    private Boolean happyCat;
    private JDialog jdialog;

    public MessageDialogUI(MoodTrackerUI moodTrackerUI, String name, String message, Boolean happyCat) {
        this.moodTrackerUI = moodTrackerUI;
        this.name = name;
        this.message = message;
        this.happyCat = happyCat;
        makeJDialog();
    }

    // MODIFIES: this
    // EFFECTS: creates JDialog to display message
    private void makeJDialog() {
        initializeDialog();
        JLabel topText = new JLabel(message);
        ImageIcon centerImage = getImageIcon();
        JLabel centerImgLabel = new JLabel(centerImage);
        JPanel botButtonPanel = new JPanel();
        new DialogOkButton(moodTrackerUI, botButtonPanel, jdialog);
        jdialog.add(topText, BorderLayout.NORTH);
        jdialog.add(centerImgLabel, BorderLayout.CENTER);
        jdialog.add(botButtonPanel, BorderLayout.SOUTH);
        jdialog.setVisible(true);
    }

    // EFFECTS: get ImageIcon from file
    private ImageIcon getImageIcon() {
        URL imageURL;
        if (happyCat) {
            imageURL = MessageDialogUI.class.getResource("/happycat.jpg");
        } else {
            imageURL = MessageDialogUI.class.getResource("/sadcat.JPG");
        }
        if (imageURL == null) {
            throw new RuntimeException("resource failed to load");
        }
        return new ImageIcon(imageURL);
    }

    // MODIFIES: this
    // EFFECTS: sets parameters for JDialog
    private void initializeDialog() {
        jdialog = new JDialog(moodTrackerUI, name, true);
        jdialog.setLayout(new BorderLayout());
        jdialog.setMinimumSize(new Dimension(225, 250));
        jdialog.setLocation(250, 0);
    }
}
