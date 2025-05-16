package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WaterTrackerPanel extends JPanel {
    private int dailyGoal = 3000; // ml
    private int currentIntake = 0;

    private JLabel progressLabel;
    private JProgressBar progressBar;
    private JPanel logPanel;
    private JTextField inputField;

    public WaterTrackerPanel(MainApp mainApp) {
        setLayout(null);
        setBackground(new Color(245, 255, 250)); // Mint cream pastel

        // Title with emoji font
        JLabel title = new JLabel("💧 Water Tracker");
        title.setFont(getEmojiFont(32)); // Increase font size
        title.setForeground(new Color(100, 149, 237)); // CornflowerBlue
        title.setBounds(100, 20, 300, 40); // Adjusted size
        add(title);

        // Daily goal label
        JLabel goalLabel = new JLabel("Daily Goal: 3000ml");
        goalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Increase font size
        goalLabel.setBounds(120, 80, 300, 30); // Adjusted position
        add(goalLabel);

        // Progress bar
        progressBar = new JProgressBar(0, dailyGoal);
        progressBar.setBounds(60, 120, 280, 30); // Increased height
        progressBar.setForeground(new Color(135, 206, 250)); // Light blue
        progressBar.setBackground(Color.WHITE);
        add(progressBar);

        // Progress label
        progressLabel = new JLabel("You've had 0ml today");
        progressLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16)); // Increased font size
        progressLabel.setBounds(110, 160, 250, 30); // Adjusted position
        add(progressLabel);

        // Input field for adding water intake
        inputField = new JTextField();
        inputField.setBounds(60, 210, 180, 40); // Adjusted size
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Increased font size
        inputField.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2)); // Set border color
        add(inputField);

        // Add button
        JButton addBtn = new JButton("Add 💦");
        styleButton(addBtn, new Color(173, 216, 230)); // Light blue button
        addBtn.setBounds(250, 210, 120, 40); // Adjusted size
        add(addBtn);

        // Log panel for displaying the drinking log
        logPanel = new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));
        logPanel.setBackground(new Color(245, 255, 250));

        JScrollPane scrollPane = new JScrollPane(logPanel);
        scrollPane.setBounds(60, 270, 310, 150); // Adjusted size
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 255)));
        add(scrollPane);

        // Reset button
        JButton resetBtn = new JButton("Reset");
        styleButton(resetBtn, new Color(255, 105, 180)); // Hot pink button
        resetBtn.setBounds(60, 440, 120, 40); // Adjusted size
        add(resetBtn);

        // Back button
        JButton backBtn = new JButton("⬅ Back");
        styleButton(backBtn, new Color(192, 192, 192)); // Gray button
        backBtn.setBounds(250, 440, 120, 40); // Adjusted size
        add(backBtn);

        // Action Listeners
        addBtn.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (input.matches("\\d+")) { // Validate input is a number
                int amount = Integer.parseInt(input);
                currentIntake += amount;
                updateProgress();

                JLabel log = new JLabel("💧 Drank " + amount + "ml");
                log.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // Increased font size
                log.setForeground(new Color(70, 130, 180)); // Steel blue
                logPanel.add(log);
                logPanel.revalidate();
                inputField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid number in ml.");
            }
        });

        resetBtn.addActionListener(e -> {
            currentIntake = 0;
            updateProgress();
            logPanel.removeAll();
            logPanel.revalidate();
            logPanel.repaint();
        });

        backBtn.addActionListener(e -> mainApp.switchPanel("Home"));
    }

    private void updateProgress() {
        progressBar.setValue(currentIntake);
        progressLabel.setText("You've had " + currentIntake + "ml today");
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Increased font size
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Function to return an appropriate font that supports emojis
    private Font getEmojiFont(int size) {
        String[] emojiFonts = {
            "Segoe UI Emoji", "Noto Color Emoji", "Apple Color Emoji", "Twemoji Mozilla", "Segoe UI"
        };
        for (String font : emojiFonts) {
            try {
                Font emojiFont = new Font(font, Font.BOLD, size);
                if (emojiFont.getName().equals(font)) {
                    return emojiFont;
                }
            } catch (Exception e) {
                // Catch the exception if the font is not available and continue
            }
        }
        return new Font("Segoe UI", Font.BOLD, size); // Fallback font
    }
}