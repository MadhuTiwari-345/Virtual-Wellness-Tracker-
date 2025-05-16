package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class MeditationPanel extends JPanel {
    private JComboBox<String> typeSelector;
    private JLabel timerLabel;
    private Timer timer;
    private int remainingSeconds;
    private boolean isRunning = false;

    public MeditationPanel(MainApp mainApp) {
        // Set layout to GridBagLayout for precise control over component positioning
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 255, 250)); // Honeydew green

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Space between components
        gbc.anchor = GridBagConstraints.CENTER;  // Align components to the center

        // Title
        JLabel title = new JLabel("🌸 Mindful Meditation");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32)); // Larger font size for better visibility
        title.setForeground(new Color(186, 85, 211)); // Orchid color
        title.setHorizontalAlignment(SwingConstants.CENTER); // Center title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Spans across 2 columns
        add(title, gbc);

        // Type selection
        JLabel selectLabel = new JLabel("Choose Type:");
        selectLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20)); // Font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(selectLabel, gbc);

        String[] types = {"🧘‍♂️ Calm", "🌿 Nature", "💫 Mindfulness", "🔥 Focus", "🌙 Sleep"};
        typeSelector = new JComboBox<>(types);
        typeSelector.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // Font size
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(typeSelector, gbc);

        // Duration selection
        JLabel timeLabel = new JLabel("Select Duration:");
        timeLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20)); // Font size
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(timeLabel, gbc);

        String[] durations = {"5 min", "10 min", "15 min"};
        JComboBox<String> timeCombo = new JComboBox<>(durations);
        timeCombo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // Font size
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(timeCombo, gbc);

        // Timer label
        timerLabel = new JLabel("00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 48)); // Timer font
        timerLabel.setForeground(new Color(72, 61, 139)); // Dark slate blue
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(timerLabel, gbc);

        // Buttons with custom creation for better consistency
        JButton startBtn = createStyledButton("▶ Start", new Color(144, 238, 144));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(startBtn, gbc);

        JButton pauseBtn = createStyledButton("⏸ Pause", new Color(255, 165, 0));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(pauseBtn, gbc);

        JButton resetBtn = createStyledButton("🔁 Reset", new Color(255, 105, 180));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(resetBtn, gbc);

        JButton backBtn = createStyledButton("⬅ Home", new Color(192, 192, 192));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(backBtn, gbc);

        // Action Listeners for buttons
        startBtn.addActionListener(e -> {
            if (!isRunning) {
                int minutes = Integer.parseInt(timeCombo.getSelectedItem().toString().split(" ")[0]);
                remainingSeconds = minutes * 60;
                startTimer();
                isRunning = true;
            }
        });

        pauseBtn.addActionListener(e -> {
            if (isRunning) {
                stopTimer();
                isRunning = false;
            }
        });

        resetBtn.addActionListener(e -> {
            stopTimer();
            timerLabel.setText("00:00");
            isRunning = false;
        });

        backBtn.addActionListener(e -> {
            stopTimer();
            mainApp.switchPanel("Home");
        });
    }

    // Method to start the timer
    private void startTimer() {
        stopTimer(); // Ensure no duplicate
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (remainingSeconds > 0) {
                    int mins = remainingSeconds / 60;
                    int secs = remainingSeconds % 60;
                    timerLabel.setText(String.format("%02d:%02d", mins, secs));
                    remainingSeconds--;
                } else {
                    timerLabel.setText("Done ✨");
                    stopTimer();
                    isRunning = false;

                    // Show popup after timer ends
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(
                            MeditationPanel.this,
                            "Your meditation session is complete! 🌟\nHope you feel relaxed and recharged.",
                            "Meditation Complete",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    });
                }
            }
        }, 0, 1000);
    }

    // Method to stop the timer
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // Custom button creation with rounded corners and gradient background
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, bgColor, getWidth(), getHeight(), new Color(135, 206, 250)); // Light gradient
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20)); // Larger font size
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(220, 50)); // Make buttons larger
        return button;
    }
}
