package WellnessApp.Utils;

import javax.swing.*;
import java.awt.*;

public class ThemeUtils {
    // Color palette
    public static final Color BACKGROUND_COLOR = new Color(250, 245, 255);
    public static final Color PRIMARY_COLOR = new Color(255, 105, 180);
    public static final Color SECONDARY_COLOR = new Color(100, 255, 218);
    public static final Color ACCENT_COLOR = new Color(255, 215, 0);
    public static final Color TEXT_COLOR = new Color(50, 50, 50);
    
    // Fonts
    public static final Font TITLE_FONT = new Font("Arial Rounded MT Bold", Font.BOLD, 28);
    public static final Font SUBTITLE_FONT = new Font("Arial Rounded MT Bold", Font.PLAIN, 18);
    public static final Font BODY_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Arial Rounded MT Bold", Font.BOLD, 16);
    
    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}