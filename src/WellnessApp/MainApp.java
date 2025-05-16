package WellnessApp;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public MainApp() {
        setTitle("Virtual Wellness Tracker 🌿");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Open in full screen
        setUndecorated(true);  // Remove window borders and title bar for full screen effect
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen
        setResizable(true);  // Allow resizing after full screen if desired
        
        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(255, 241, 242));  // Light background color
        
        // Initialize all panels with proper names for CardLayout
        mainPanel.add("Login", new LoginPanel(this));
        mainPanel.add("SignUp", new SignUpPanel(this));
        mainPanel.add("Home", new HomePanel(this));
        mainPanel.add("Habit", new HabitPanel(this));
        mainPanel.add("Mood", new MoodPanel(this));
        mainPanel.add("Water", new WaterTrackerPanel(this));
        mainPanel.add("Sleep", new SleepPanel(this));
        mainPanel.add("Meditation", new MeditationPanel(this));
        mainPanel.add("Quote", new QuotePanel(this));
        
        add(mainPanel); 
        switchPanel("Login");  // Set the default starting panel to "Login"
    }
    
    // Method to switch between different panels
    public void switchPanel(String panelName) {
        System.out.println("Switching to: " + panelName);  // Debugging line
        cardLayout.show(mainPanel, panelName);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  // Set system look and feel
                new MainApp().setVisible(true);  // Create and display the MainApp
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	public Object showHomePanel() {
		// TODO Auto-generated method stub
		return null;
	}
}
