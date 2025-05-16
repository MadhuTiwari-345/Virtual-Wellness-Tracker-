package WellnessApp.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonUtils {
    public static void createHoverEffect(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = originalColor.darker();
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }
    
    public static JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(ThemeUtils.PRIMARY_COLOR);
        button.setForeground(ThemeUtils.SECONDARY_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        createHoverEffect(button);
        return button;
    }
    
    public static JButton createRoundButton(String text, int diameter) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(ThemeUtils.PRIMARY_COLOR.darker());
                } else {
                    g.setColor(ThemeUtils.PRIMARY_COLOR);
                }
                g.fillOval(0, 0, getSize().width-1, getSize().height-1);
                super.paintComponent(g);
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(ThemeUtils.PRIMARY_COLOR);
                g.drawOval(0, 0, getSize().width-1, getSize().height-1);
            }
            
            @Override
            public boolean contains(int x, int y) {
                return (x - getWidth()/2) * (x - getWidth()/2) + (y - getHeight()/2) * (y - getHeight()/2)
                    <= (getWidth()/2) * (getHeight()/2);
            }
        };
        
        button.setPreferredSize(new Dimension(diameter, diameter));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(ThemeUtils.SECONDARY_COLOR);
        
        return button;
    }
}