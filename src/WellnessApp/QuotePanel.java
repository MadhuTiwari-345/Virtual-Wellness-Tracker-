package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class QuotePanel extends JPanel {
    private JLabel quoteLabel;
    private final String[] quotes = {
        "✨ The only way to do great work is to love what you do. ― Steve Jobs",
        "🌱 Life is what happens when you're busy making other plans. ― John Lennon",
        "🌈 In the middle of every difficulty lies opportunity. ― Albert Einstein",
        "💪 Do one thing every day that scares you. ― Eleanor Roosevelt",
        "🔥 Well done is better than well said. ― Benjamin Franklin",
        "🌟 Believe you can and you're halfway there. ― Theodore Roosevelt",
        "🌸 The future belongs to those who believe in the beauty of their dreams. ― Eleanor Roosevelt",
        "💖 Believe in yourself and all that you are.",
        "🚀 Success is the sum of small efforts, repeated day in and day out.",
        "🎯 Do what you can, with what you have, where you are.",
        "🧠 Your limitation—it’s only your imagination.",
        "🌠 Dream it. Wish it. Do it."
    };

    public QuotePanel(MainApp mainApp) {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 245, 248)); // Light pastel pink

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Increased padding between components

        JLabel title = new JLabel("💬 Daily Dose of Motivation");
        title.setFont(getEmojiFont(Font.BOLD, 35)); // Increased font size for title
        title.setForeground(new Color(186, 85, 211)); // Orchid purple
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        quoteLabel = new JLabel("<html><center>Click below to feel inspired!</center></html>", SwingConstants.CENTER);
        quoteLabel.setFont(getEmojiFont(Font.ITALIC, 26)); // Increased font size for quote label
        quoteLabel.setForeground(new Color(75, 0, 130)); // Indigo
        gbc.gridy = 1;
        add(quoteLabel, gbc);

        JButton inspireBtn = createGradientButton("✨ Inspire Me!");
        gbc.gridy = 2;
        add(inspireBtn, gbc);

        JButton homeBtn = createStyledButton("⬅ Home", Color.LIGHT_GRAY);
        gbc.gridy = 3;
        add(homeBtn, gbc);

        // Event Listeners
        inspireBtn.addActionListener(e -> showRandomQuote());
        homeBtn.addActionListener(e -> mainApp.switchPanel("Home"));
    }

    private void showRandomQuote() {
        int idx = new Random().nextInt(quotes.length);
        String quote = quotes[idx];
        quoteLabel.setText("<html><div style='text-align: center;'>" + quote + "</div></html>");
    }

    private JButton createGradientButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(255, 105, 180), getWidth(), getHeight(),
                        new Color(135, 206, 250)); // Pink to Light Blue
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Larger button corners
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(getEmojiFont(Font.BOLD, 20)); // Increased font size for button
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createStyledButton(String text, Color unused) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(186, 85, 211), getWidth(), getHeight(),
                        new Color(255, 192, 203)); // Orchid to Pink
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Larger button corners
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(getEmojiFont(Font.BOLD, 18)); // Increased font size for home button
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Utility method for emoji-compatible font
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

        return new Font("Dialog", style, size); // Default fallback
    }
}
