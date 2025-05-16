package WellnessApp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginSignupPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public LoginSignupPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new CardLayout());

        // Create Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(loginTitle, gbc);

        // Username Field
        JLabel userLabel = new JLabel("Username: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(userLabel, gbc);
        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        // Password Field
        JLabel passwordLabel = new JLabel("Password: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(loginButton, gbc);

        // Signup Link
        JButton signupLink = new JButton("Don't have an account? Sign up here");
        gbc.gridx = 1;
        gbc.gridy = 4;
        loginPanel.add(signupLink, gbc);

        signupLink.addActionListener(e -> cardLayout.show(mainPanel, "Signup")); // Switch to Signup Panel

        // Action for Login Button
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());
            // Implement your login logic here
            System.out.println("Login Attempt: " + username + " " + password);
        });

        // Create Signup Panel
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new GridBagLayout());

        JLabel signupTitle = new JLabel("Sign Up");
        signupTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        signupPanel.add(signupTitle, gbc);

        // Username Field
        JLabel signupUserLabel = new JLabel("Username: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        signupPanel.add(signupUserLabel, gbc);
        JTextField signupUserField = new JTextField(20);
        gbc.gridx = 1;
        signupPanel.add(signupUserField, gbc);

        // Password Field
        JLabel signupPasswordLabel = new JLabel("Password: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        signupPanel.add(signupPasswordLabel, gbc);
        JPasswordField signupPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        signupPanel.add(signupPasswordField, gbc);

        // Confirm Password Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        signupPanel.add(confirmPasswordLabel, gbc);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        signupPanel.add(confirmPasswordField, gbc);

        // Signup Button
        JButton signupButton = new JButton("Sign Up");
        gbc.gridx = 1;
        gbc.gridy = 4;
        signupPanel.add(signupButton, gbc);

        // Back to Login Link
        JButton loginLink = new JButton("Already have an account? Login here");
        gbc.gridx = 1;
        gbc.gridy = 5;
        signupPanel.add(loginLink, gbc);

        loginLink.addActionListener(e -> cardLayout.show(mainPanel, "Login")); // Switch to Login Panel

        // Action for Signup Button
        signupButton.addActionListener(e -> {
            String signupUsername = signupUserField.getText();
            String signupPassword = new String(signupPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            // Implement your signup logic here
            if (signupPassword.equals(confirmPassword)) {
                System.out.println("Signup Attempt: " + signupUsername + " " + signupPassword);
            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
            }
        });

        // Add both panels to mainPanel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signupPanel, "Signup");
    }
}
