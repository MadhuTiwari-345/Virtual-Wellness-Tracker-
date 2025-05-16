package WellnessApp;

import javax.swing.*;

import WellnessApp.Utils.ThemeUtils;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SocialShareDialog extends JDialog {
    private JTextArea postArea;
    
    public SocialShareDialog(MainApp parent) {
        super(parent, "Share Your Vibe", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        
        initUI();
    }
    
    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 255, 255, 230));
        
        // Header
        JLabel titleLabel = new JLabel("📱 Post Your Progress", SwingConstants.CENTER);
        titleLabel.setFont(ThemeUtils.TITLE_FONT);
        titleLabel.setForeground(ThemeUtils.PRIMARY_COLOR);
        
        // Here Content area
        postArea = new JTextArea();
        postArea.setText("Just completed my daily wellness check! ✨ #SelfCare #VibeTrack");
        postArea.setFont(ThemeUtils.BODY_FONT);
        postArea.setLineWrap(true);
        postArea.setWrapStyleWord(true);
        postArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ThemeUtils.SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        //  Here Platform buttons
        JPanel platformPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        platformPanel.setOpaque(false);
        
        String[] platforms = {"Instagram", "TikTok", "Twitter", "Snapchat"};
        Color[] colors = {
            new Color(193, 53, 132), // Instagram
            new Color(0, 0, 0),       // TikTok
            new Color(29, 161, 242),  // Twitter
            new Color(255, 252, 0)    // Snapchat
        };
        
        for (int i = 0; i < platforms.length; i++) {
            JButton platformButton = new JButton(platforms[i]);
            platformButton.setBackground(colors[i]);
            platformButton.setForeground(Color.WHITE);
            platformButton.setFont(ThemeUtils.BODY_FONT);
            platformButton.addActionListener(this::shareToPlatform);
            platformPanel.add(platformButton);
        }
        
        // Here Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        ThemeUtils.styleButton(closeButton);
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(postArea), BorderLayout.CENTER);
        mainPanel.add(platformPanel, BorderLayout.SOUTH);
        mainPanel.add(closeButton, BorderLayout.PAGE_END);
        
        add(mainPanel);
    }
    
    private void shareToPlatform(ActionEvent e) {
        String platform = ((JButton) e.getSource()).getText();
       
        JOptionPane.showMessageDialog(this, 
            "Shared to " + platform + "! 🚀", 
            "Vibe Shared", 
            JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}