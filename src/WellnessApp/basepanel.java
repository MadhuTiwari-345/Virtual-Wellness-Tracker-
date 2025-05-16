package WellnessApp;

import javax.swing.*;
import java.awt.*;

public class basepanel extends JPanel {
    protected MainApp mainApp;
    protected Color bgColor = new Color(255, 241, 242);
    protected Color cardColor = new Color(255, 255, 255, 220);
    protected Color accentColor = new Color(255, 105, 180);
    protected Color secondaryColor = new Color(100, 200, 225);
    
    public basepanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(null);
        setBackground(bgColor);
    }
    
    protected JPanel createCard(int x, int y, int width, int height) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(cardColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
            }
        };
        card.setLayout(null);
        card.setBounds(x, y, width, height);
        card.setOpaque(false);
        return card;
    }
    
    protected JButton createGradientButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gp = new GradientPaint(
                    0, 0, accentColor, 
                    getWidth(), 0, secondaryColor);
                
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        button.setBounds(x, y, width, height);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    protected void addEmojiDecoration(String emoji, int x, int y, int size) {
        JLabel deco = new JLabel(emoji);
        deco.setFont(new Font("Segoe UI Emoji", Font.PLAIN, size));
        deco.setBounds(x, y, size, size);
        add(deco);
    }
}