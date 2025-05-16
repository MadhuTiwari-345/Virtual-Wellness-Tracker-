package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton, loginRedirectButton;
    private MainApp mainApp;

    public SignUpPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(null);
        setBackground(new Color(230, 245, 255)); // Soft blue background

        // Decorative Emojis
        JLabel deco1 = new JLabel("🌈");
        deco1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        deco1.setBounds(40, 60, 60, 60);
        add(deco1);

        JLabel deco2 = new JLabel("🌟");
        deco2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        deco2.setBounds(390, 100, 60, 60);
        add(deco2);

        // Here Card Panel
        JPanel card = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 220));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
            }
        };
        card.setLayout(null);
        card.setBounds(100, 100, 300, 400);
        card.setOpaque(false);
        add(card);

        // Here Title
        JLabel title = new JLabel("create account");
        title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 22));
        title.setForeground(new Color(100, 149, 237)); // Cornflower Blue
        title.setBounds(70, 30, 200, 30);
        card.add(title);

        // Here Username
        usernameField = new RoundedTextField(15);
        usernameField.setBounds(50, 80, 200, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        usernameField.setBackground(new Color(245, 245, 245));
        card.add(usernameField);

        // Here Password
        passwordField = new RoundedPasswordField(15);
        passwordField.setBounds(50, 140, 200, 40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        passwordField.setBackground(new Color(245, 245, 245));
        card.add(passwordField);

        // Here Sign Up Button
        signUpButton = new GradientButton("sign up");
        signUpButton.setBounds(50, 200, 200, 45);
        signUpButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
        signUpButton.setForeground(Color.WHITE);
        card.add(signUpButton);

        // Divider
        JLabel divider = new JLabel("───────── or ─────────");
        divider.setForeground(new Color(180, 180, 180));
        divider.setFont(new Font("Arial", Font.PLAIN, 12));
        divider.setBounds(50, 260, 200, 20);
        card.add(divider);

        // Here  Login Redirect
        loginRedirectButton = new JButton("already have an account?");
        loginRedirectButton.setBounds(50, 300, 200, 30);
        loginRedirectButton.setForeground(new Color(255, 105, 180)); // Hot pink
        loginRedirectButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginRedirectButton.setContentAreaFilled(false);
        loginRedirectButton.setBorderPainted(false);
        loginRedirectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(loginRedirectButton);

        // Here Placeholder Setup
        addPlaceholder(usernameField, "choose a username");
        addPlaceholder(passwordField, "choose a password");

        // Here Action Listeners
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.equals("choose a username") && !password.equals("choose a password")) {
                JOptionPane.showMessageDialog(this,
                        "yay! account created 🎉", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainApp.switchPanel("Login");
            } else {
                JOptionPane.showMessageDialog(this,
                        "please enter valid credentials", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginRedirectButton.addActionListener(e -> {
            mainApp.switchPanel("Login");
        });
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

    // Here Custom rounded text field
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

    // Here Custom rounded password field
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

    // HERE Gradient button
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
