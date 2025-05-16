package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpRedirectButton, exitButton;  // Added exitButton
    private MainApp mainApp;

    public LoginPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(null);
        setBackground(new Color(245, 250, 255)); // Light pastel blue

        // Emoji Labels
        JLabel emoji1 = new JLabel("✨");
        emoji1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 35));
        emoji1.setBounds(50, 60, 60, 60);
        add(emoji1);

        JLabel emoji2 = new JLabel("🌻");
        emoji2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        emoji2.setBounds(390, 120, 60, 60);
        add(emoji2);

        // Card Panel
        JPanel card = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                g2.setColor(new Color(255, 255, 255, 220));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
            }
        };
        card.setLayout(null);
        card.setBounds(100, 100, 300, 400);
        card.setOpaque(false);
        add(card);
     // Title
        JLabel title = new JLabel("Welcome !");
        title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28)); // Increased font size
        title.setForeground(new Color(100, 149, 237)); // Cornflower blue
        title.setBounds(50, 30, 200, 40);  // Adjusted the position and width
        title.setHorizontalAlignment(SwingConstants.CENTER);  // Center the text
        card.add(title);

        // Username Field
        usernameField = new RoundedTextField(15);
        usernameField.setBounds(50, 80, 200, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        usernameField.setBackground(new Color(245, 245, 245));
        card.add(usernameField);

        // Password Field
        passwordField = new RoundedPasswordField(15);
        passwordField.setBounds(50, 140, 200, 40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        passwordField.setBackground(new Color(245, 245, 245));
        card.add(passwordField);

        // Login Button
        loginButton = new GradientButton("log in");
        loginButton.setBounds(50, 200, 200, 45);
        loginButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        card.add(loginButton);

     // Exit Button
        exitButton = new GradientButton("Exit");
        exitButton.setBounds(50, 350, 200, 45);  // Adjusted for consistency with other buttons
        exitButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(255, 85, 85));  // Soft red color for harmony
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(e -> System.exit(0));  // Exits the application
        card.add(exitButton);


        // Divider
        JLabel divider = new JLabel("───────── or ─────────");
        divider.setForeground(new Color(180, 180, 180));
        divider.setFont(new Font("Arial", Font.PLAIN, 12));
        divider.setBounds(50, 260, 200, 20);
        card.add(divider);

        // Sign Up Redirect Button
        signUpRedirectButton = new JButton("new here? create account");
        signUpRedirectButton.setBounds(50, 300, 200, 30);
        signUpRedirectButton.setForeground(new Color(255, 105, 180)); // Hot pink
        signUpRedirectButton.setFont(new Font("Arial", Font.BOLD, 12));
        signUpRedirectButton.setContentAreaFilled(false);
        signUpRedirectButton.setBorderPainted(false);
        signUpRedirectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(signUpRedirectButton);

        // Placeholder Setup
        addPlaceholder(usernameField, "enter username");
        addPlaceholder(passwordField, "enter password");

        // Action Listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.equals("enter username") && !password.equals("enter password")) {
                JOptionPane.showMessageDialog(this, "login success! 🎉", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                mainApp.switchPanel("Home"); // Switch to HomePanel
            } else {
                JOptionPane.showMessageDialog(this, "invalid login info 😥", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        signUpRedirectButton.addActionListener(e -> mainApp.switchPanel("SignUp"));
    }

    private void addPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(new Color(150, 150, 150));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(new Color(150, 150, 150));
                }
            }
        });
    }

    // Custom rounded text field
    class RoundedTextField extends JTextField {
        private int radius;

        public RoundedTextField(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    // Custom rounded password field
    class RoundedPasswordField extends JPasswordField {
        private int radius;

        public RoundedPasswordField(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    // Gradient button
    class GradientButton extends JButton {
        public GradientButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(100, 200, 225),
                    getWidth(), 0, new Color(255, 105, 180));
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
