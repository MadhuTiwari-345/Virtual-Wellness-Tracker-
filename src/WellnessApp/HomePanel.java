package WellnessApp;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class HomePanel extends JPanel {
    private MainApp mainApp;
    private JLabel title;
    private GradientTileButton[] tileButtons = new GradientTileButton[6];
    private GradientTileButton exitButton;

    public HomePanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(null);
        setBackground(new Color(255, 248, 251)); // Light blush

        title = new JLabel("\u2728  Welcome To Wellness Tracker\uD83C\uDF3F  \u2728", SwingConstants.CENTER);
        title.setFont(getEmojiFont(28));
        title.setForeground(new Color(255, 105, 180));
        title.setBounds(0, 30, 1000, 40); // Wide initially
        add(title);

        // Create main feature tiles
        tileButtons[0] = createTile("💧 Water", () -> mainApp.switchPanel("Water"));
        tileButtons[1] = createTile("😄 Mood", () -> mainApp.switchPanel("Mood"));
        tileButtons[2] = createTile("🧘 Meditation", () -> mainApp.switchPanel("Meditation"));
        tileButtons[3] = createTile("💤 Sleep", () -> mainApp.switchPanel("Sleep"));
        tileButtons[4] = createTile("📅 Habits", () -> mainApp.switchPanel("Habit"));
        tileButtons[5] = createTile("💬 Quotes", () -> mainApp.switchPanel("Quote"));

        for (GradientTileButton btn : tileButtons) {
            add(btn);
        }

        // Exit button (same style as tiles)
        exitButton = createTile("🚪 Exit", () -> {
            int choice = JOptionPane.showConfirmDialog(
                this, "Are you sure you want to exit?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(exitButton);
    }

    // Auto-realign tiles and spacing based on size
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        revalidateLayout();
    }

    private void revalidateLayout() {
        int tileWidth = 150;
        int tileHeight = 100;
        int hGap = 30;
        int vGap = 30;
        int panelWidth = getWidth();
        int xStart = (panelWidth - (tileWidth * 2 + hGap)) / 2;

        int yStart = 100;
        for (int i = 0; i < tileButtons.length; i++) {
            int col = i % 2;
            int row = i / 2;
            int x = xStart + col * (tileWidth + hGap);
            int y = yStart + row * (tileHeight + vGap);
            tileButtons[i].setBounds(x, y, tileWidth, tileHeight);
        }

        // Exit button centered under the last row
        int totalRows = 3;
        int exitY = yStart + totalRows * (tileHeight + vGap) + 10;
        exitButton.setBounds((panelWidth - tileWidth) / 2, exitY, tileWidth, tileHeight);

        // Title center align
        title.setBounds((getWidth() - 600) / 2, 30, 600, 40);
    }

    private GradientTileButton createTile(String text, Runnable action) {
        GradientTileButton tile = new GradientTileButton(text);
        tile.setFont(getEmojiFont(16));
        tile.setForeground(Color.WHITE);
        tile.setFocusPainted(false);
        tile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tile.setContentAreaFilled(false);
        tile.setBorderPainted(false);
        tile.setOpaque(false);

        tile.addActionListener(e -> action.run());
        tile.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                tile.setForeground(Color.YELLOW);
            }

            public void mouseExited(MouseEvent e) {
                tile.setForeground(Color.WHITE);
            }
        });

        return tile;
    }

    class GradientTileButton extends JButton {
        public GradientTileButton(String text) {
            super(text);
            setFont(new Font("Segoe UI", Font.BOLD, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0,
                    new Color(255, 105, 180), getWidth(), getHeight(), new Color(135, 206, 250));
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g2);
            g2.dispose();
        }
    }

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
            } catch (Exception ignored) {}
        }
        return new Font("Segoe UI", Font.BOLD, size); // fallback
    }
}  