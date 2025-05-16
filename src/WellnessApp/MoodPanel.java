package WellnessApp;

import javax.swing.*;
import java.awt.*;

public class MoodPanel extends JPanel {
    private JLabel moodMessageLabel;
    private String currentMood = "Neutral";

    public MoodPanel(MainApp mainApp) {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 248)); // Light pastel pink

        // Top title
        JLabel title = new JLabel("\uD83C\uDF08 Select Your Mood", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        title.setForeground(new Color(186, 85, 211)); // Orchid purple
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Center container for message + mood buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        moodMessageLabel = new JLabel("<html><center>How are you feeling today?</center></html>", SwingConstants.CENTER);
        moodMessageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        moodMessageLabel.setForeground(new Color(75, 0, 130)); // Indigo
        moodMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(moodMessageLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Grid panel for mood buttons
        JPanel moodGrid = new JPanel(new GridLayout(4, 2, 20, 20));
        moodGrid.setOpaque(false);
        moodGrid.setMaximumSize(new Dimension(400, 400));

        moodGrid.add(createMoodButton("😄 Happy", new Color(255, 223, 186), "Happy"));
        moodGrid.add(createMoodButton("😞 Sad", new Color(173, 216, 230), "Sad"));
        moodGrid.add(createMoodButton("😡 Angry", new Color(255, 105, 180), "Angry"));
        moodGrid.add(createMoodButton("😌 Calm", new Color(152, 251, 152), "Calm"));
        moodGrid.add(createMoodButton("😴 Tired", new Color(224, 255, 255), "Tired"));
        moodGrid.add(createMoodButton("😎 Cool", new Color(173, 216, 230), "Cool"));
        moodGrid.add(createMoodButton("😬 Anxious", new Color(255, 182, 193), "Anxious"));
        moodGrid.add(createMoodButton("🥺 Hopeful", new Color(255, 218, 185), "Hopeful"));

        centerPanel.add(moodGrid);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom home button
        JButton homeBtn = createGradientButton("⬅ Home", new Color(186, 85, 211), new Color(255, 105, 180));
        homeBtn.setPreferredSize(new Dimension(120, 35));
        homeBtn.addActionListener(e -> mainApp.switchPanel("Home"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(homeBtn);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createMoodButton(String text, Color bgColor, String mood) {
        JButton button = createGradientButton(text, bgColor, new Color(135, 206, 250));
        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(130, 80));
        button.addActionListener(e -> handleMoodSelection(mood));
        return button;
    }

    private void handleMoodSelection(String mood) {
        this.currentMood = mood;

        switch (mood) {
            case "Happy":
                setBackground(new Color(255, 223, 186));
                moodMessageLabel.setText("<html><center>You got this! Keep spreading positivity! 🌟</center></html>");
                break;
            case "Sad":
                setBackground(new Color(173, 216, 230));
                moodMessageLabel.setText("<html><center>It's okay to feel down. You are loved and appreciated 💖</center></html>");
                break;
            case "Angry":
                setBackground(new Color(255, 105, 180));
                moodMessageLabel.setText("<html><center>Take a deep breath. Anger is temporary 🌱</center></html>");
                break;
            case "Calm":
                setBackground(new Color(152, 251, 152));
                moodMessageLabel.setText("<html><center>You are at peace. Embrace the serenity 🌿</center></html>");
                break;
            case "Tired":
                setBackground(new Color(224, 255, 255));
                moodMessageLabel.setText("<html><center>Take a break, relax, and recharge. 💆‍♂️💤</center></html>");
                break;
            case "Cool":
                setBackground(new Color(173, 216, 230));
                moodMessageLabel.setText("<html><center>You're looking fresh! Keep slaying! 😎</center></html>");
                break;
            case "Anxious":
                setBackground(new Color(255, 182, 193));
                moodMessageLabel.setText("<html><center>Take a deep breath, you're stronger than you think! 🌸</center></html>");
                break;
            case "Hopeful":
                setBackground(new Color(255, 218, 185));
                moodMessageLabel.setText("<html><center>There's always light at the end of the tunnel! Keep pushing 🌞</center></html>");
                break;
        }

        moodMessageLabel.setForeground(getMessageColorForMood(mood));
    }

    private Color getMessageColorForMood(String mood) {
        switch (mood) {
            case "Happy": return new Color(255, 105, 180);
            case "Sad": return new Color(75, 0, 130);
            case "Angry": return new Color(255, 69, 0);
            case "Calm": return new Color(34, 139, 34);
            case "Tired": return new Color(0, 191, 255);
            case "Cool": return new Color(70, 130, 180);
            case "Anxious": return new Color(255, 69, 0);
            case "Hopeful": return new Color(255, 140, 0);
            default: return new Color(75, 0, 130);
        }
    }

    private JButton createGradientButton(String text, Color color, Color color2) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, color, getWidth(), getHeight(), color2);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
