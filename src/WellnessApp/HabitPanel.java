package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HabitPanel extends JPanel {
    private JLabel titleLabel;
    private ArrayList<JCheckBox> habitCheckboxes;
    private JButton submitButton;
    private JButton resetButton;
    private JProgressBar progressBar;

    public HabitPanel(MainApp mainApp) {
        setLayout(null);
        setBackground(new Color(255, 245, 248)); // Light pastel pink

        // Title Label with emoji-supported font
        titleLabel = new JLabel("🌟 Daily Habits Tracker");
        titleLabel.setFont(getEmojiFont(Font.BOLD, 24));
        titleLabel.setForeground(new Color(186, 85, 211)); // Orchid purple
        titleLabel.setBounds(90, 30, 400, 30);
        add(titleLabel);

        // List of daily habits
        String[] habits = {
            "🧘‍♀️ Meditate for 10 minutes",
            "🍎 Eat healthy",
            "🚶‍♂️ Walk for 30 minutes",
            "📚 Read a book for 20 minutes",
            "💧 Drink 2 liters of water",
            "😴 Sleep 8 hours"
        };

        habitCheckboxes = new ArrayList<>();
        int yPosition = 100;

        for (String habit : habits) {
            JCheckBox habitCheckbox = new JCheckBox(habit);
            habitCheckbox.setFont(getEmojiFont(Font.PLAIN, 16));
            habitCheckbox.setForeground(new Color(75, 0, 130)); // Indigo
            habitCheckbox.setBackground(new Color(255, 245, 248));
            habitCheckbox.setBounds(60, yPosition, 400, 30);
            habitCheckbox.setFocusPainted(false);
            habitCheckbox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(habitCheckbox);
            habitCheckboxes.add(habitCheckbox);
            yPosition += 40;
        }

        // Progress Bar
        progressBar = new JProgressBar(0, habitCheckboxes.size());
        progressBar.setBounds(60, yPosition, 350, 30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(186, 85, 211));
        add(progressBar);

        // Submit Button
        submitButton = createStyledButton("✅ Submit", new Color(186, 85, 211));
        submitButton.setBounds(60, yPosition + 50, 150, 35);
        submitButton.addActionListener(e -> submitHabits());
        add(submitButton);

        // Reset Button
        resetButton = createStyledButton("🔄 Reset", new Color(144, 238, 144));
        resetButton.setBounds(220, yPosition + 50, 150, 35);
        resetButton.addActionListener(e -> resetHabits());
        add(resetButton);

        // Home Button
        JButton homeBtn = createStyledButton("⬅ Home", Color.LIGHT_GRAY);
        homeBtn.setBounds(125, yPosition + 100, 130, 35);
        homeBtn.addActionListener(e -> mainApp.switchPanel("Home"));
        add(homeBtn);
    }

    private void submitHabits() {
        int completedHabits = 0;
        for (JCheckBox habitCheckbox : habitCheckboxes) {
            if (habitCheckbox.isSelected()) {
                completedHabits++;
            }
        }

        progressBar.setValue(completedHabits);

        String message = "You completed " + completedHabits + " habits today!";
        if (completedHabits == habitCheckboxes.size()) {
            message = "Awesome! You completed all your habits today! 🌟";
        }

        JOptionPane.showMessageDialog(this, message, "Habit Tracker", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetHabits() {
        for (JCheckBox habitCheckbox : habitCheckboxes) {
            habitCheckbox.setSelected(false);
        }
        progressBar.setValue(0);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, bgColor, getWidth(), getHeight(), new Color(255, 182, 193)); // Light pink gradient
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(getEmojiFont(Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }


    // Utility to get emoji-compatible font
    private Font getEmojiFont(int style, int size) {
        String[] emojiFonts = {
            "Segoe UI Emoji", // Windows
            "Apple Color Emoji", // macOS
            "Noto Color Emoji", // Linux
            "Segoe UI" // fallback
        };

        for (String fontName : emojiFonts) {
            Font font = new Font(fontName, style, size);
            if (font.canDisplayUpTo("🌟") == -1)
                return font;
            }
        

        // Default if none support emoji
        return new Font("Dialog", style, size);
    }
}
