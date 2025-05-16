package WellnessApp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class SleepPanel extends JPanel {
    private JComboBox<String> ageGroupBox;
    private JTextField sleepField;
    private JLabel sleepAdviceLabel;
    private JTextArea resultArea;

    public SleepPanel(MainApp mainApp) {
        setLayout(null);
        setBackground(new Color(255, 245, 252)); // Light pastel pink background

        JLabel title = new JLabel("🌙 Sleep Tracker");
        title.setFont(new Font("Poppins", Font.BOLD, 30));
        title.setForeground(new Color(102, 51, 153)); // Purple
        title.setBounds(100, 20, 300, 35);
        add(title);

        JLabel ageLabel = new JLabel("Your Age Group:");
        ageLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        ageLabel.setBounds(50, 80, 150, 25);
        add(ageLabel);

        String[] ageGroups = {"< 13", "13–18", "18–25", "26–64", "65+"};
        ageGroupBox = new JComboBox<>(ageGroups);
        ageGroupBox.setFont(new Font("Poppins", Font.PLAIN, 14));
        ageGroupBox.setBounds(180, 80, 120, 30);
        ageGroupBox.setBackground(new Color(255, 250, 240)); // Floral White
        add(ageGroupBox);

        sleepAdviceLabel = new JLabel();
        sleepAdviceLabel.setFont(new Font("Poppins", Font.ITALIC, 14));
        sleepAdviceLabel.setForeground(new Color(120, 120, 120));
        sleepAdviceLabel.setBounds(50, 115, 350, 25);
        add(sleepAdviceLabel);

        JLabel sleepLabel = new JLabel("Hours you slept:");
        sleepLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        sleepLabel.setBounds(50, 160, 200, 25);
        add(sleepLabel);

        sleepField = new JTextField();
        sleepField.setFont(new Font("Poppins", Font.PLAIN, 16));
        sleepField.setBounds(50, 190, 250, 45);
        sleepField.setBackground(new Color(255, 255, 255));
        sleepField.setBorder(new RoundedBorder(12));
        sleepField.setMargin(new Insets(5, 10, 5, 10));
        add(sleepField);

        // Buttons
        JButton calcBtn = createButton("🔢 Calculate", new Color(173, 216, 230));  // Baby Blue
        JButton resetBtn = createButton("🔄 Reset", new Color(255, 182, 193));     // Light Pink
        JButton homeBtn = createButton("🏠 Home", new Color(144, 238, 144));       // Light Green

        calcBtn.setBounds(50, 240, 120, 45);
        resetBtn.setBounds(180, 240, 120, 45);
        homeBtn.setBounds(310, 240, 120, 45);

        add(calcBtn);
        add(resetBtn);
        add(homeBtn);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Poppins", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(new Color(255, 255, 240)); // Ivory
        resultArea.setBorder(new CompoundBorder(
                new LineBorder(new Color(180, 180, 255), 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        resultArea.setBounds(50, 300, 380, 120);
        add(resultArea);

        // Event Listeners
        calcBtn.addActionListener(e -> calculateSleepDuration());
        resetBtn.addActionListener(e -> resetFields());
        homeBtn.addActionListener(e -> mainApp.switchPanel("Home"));
        ageGroupBox.addActionListener(e -> updateSleepAdvice());

        updateSleepAdvice();
    }

    private void updateSleepAdvice() {
        String group = (String) ageGroupBox.getSelectedItem();
        int[] range = getRecommendedSleepRange(group);
        sleepAdviceLabel.setText(String.format("🛏 Recommended: %d–%d hrs", range[0], range[1]));
    }

    private void calculateSleepDuration() {
        try {
            double hours = Double.parseDouble(sleepField.getText().trim());
            int[] range = getRecommendedSleepRange((String) ageGroupBox.getSelectedItem());

            StringBuilder feedback = new StringBuilder();

            if (hours <= 0) {
                feedback.append("⚠️ No sleep detected!\n\n");
                feedback.append("💤 Try to get some rest today.");
            } else if (hours < range[0]) {
                feedback.append("😴 Too little sleep!\n\n");
                feedback.append("📌 Your body needs more rest.");
            } else if (hours > range[1]) {
                feedback.append("🛑 Oversleep alert!\n\n");
                feedback.append("📌 Consider cutting back slightly.");
            } else {
                feedback.append("✅ Perfect Sleep!\n\n");
                feedback.append("🌟 You're well-rested. Keep it up!");
            }

            resultArea.setText(feedback.toString());
        } catch (NumberFormatException e) {
            resultArea.setText("❗ Please enter a valid number like 6 or 8.5");
        }
    }

    private void resetFields() {
        sleepField.setText("");
        resultArea.setText("");
        ageGroupBox.setSelectedIndex(0);
        updateSleepAdvice();
    }

    private int[] getRecommendedSleepRange(String ageGroup) {
        return switch (ageGroup) {
            case "< 13" -> new int[]{9, 11};
            case "13–18" -> new int[]{8, 10};
            case "18–25", "26–64" -> new int[]{7, 9};
            case "65+" -> new int[]{7, 8};
            default -> new int[]{7, 9};
        };
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setForeground(Color.BLACK);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(20));
        btn.setOpaque(true);
        btn.setContentAreaFilled(false); // Allow background color painting
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Fix: Override paint to ensure background fills
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        // Add hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bgColor.darker());
                btn.repaint();
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
                btn.repaint();
            }
        });

        return btn;
    }


    // Rounded border class
    class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(186, 85, 211)); // Medium Orchid
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
