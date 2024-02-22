package org.example;

import javax.swing.*;
import java.awt.*;

public class FirstSwingExample {
    // Predefined username and password
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame

        JPanel panel = new JPanel(null);
        f.add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 100, 80, 25);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(130, 100, 200, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 80, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(130, 150, 200, 25);

        JButton loginButton = new JButton("Login");//creating instance of JButton
        loginButton.setBounds(130, 200, 100, 40);//x axis, y axis, width, height

        // Adding ActionListener to the Login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Check if username and password match predefined values
            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                JOptionPane.showMessageDialog(f, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(f, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear fields after login attempt
            usernameField.setText("");
            passwordField.setText("");
        });

        JButton cancelButton = new JButton("Cancel");//creating instance of JButton
        cancelButton.setBounds(250, 200, 100, 40);//x axis, y axis, width, height

        // Adding ActionListener to the Cancel button
        cancelButton.addActionListener(e -> {
            // Clear fields when Cancel button is clicked
            usernameField.setText("");
            passwordField.setText("");
        });

        JButton settingsButton = new JButton("Settings");//creating instance of JButton
        settingsButton.setBounds(130, 250, 100, 40);//x axis, y axis, width, height

        // Adding ActionListener to the Settings button
        settingsButton.addActionListener(e -> {
            // Show settings dialog with options
            String[] options = {"Change Background Color", "Resizable Text", "Other Settings"};
            int choice = JOptionPane.showOptionDialog(f, "Choose an option:", "Settings", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                // Change Background Color
                Color newColor = JColorChooser.showDialog(f, "Choose Background Color", f.getContentPane().getBackground());
                if (newColor != null) {
                    f.getContentPane().setBackground(newColor);
                }
            } else if (choice == 1) {
                // Resizable Text
                showResizableTextDialog(f, panel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, cancelButton, settingsButton);
            } else if (choice == 2) {
                // Other Settings
                // Show additional settings dialog
                showAdditionalSettingsDialog(f);
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);//adding button in JFrame
        panel.add(cancelButton);//adding button in JFrame
        panel.add(settingsButton);//adding button in JFrame

        f.setSize(400, 350);//400 width and 350 height
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);//making the frame visible
    }

    private static void showResizableTextDialog(JFrame parentFrame, JPanel panel, JLabel usernameLabel, JTextField usernameField,
                                                JLabel passwordLabel, JPasswordField passwordField,
                                                JButton loginButton, JButton cancelButton, JButton settingsButton) {

        // Create and show resizable text dialog
        JDialog dialog = new JDialog(parentFrame, "Resizable Text", true);
        dialog.setLayout(new GridLayout(1, 2));

        // Text size slider
        JSlider textSizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 30, 12);
        textSizeSlider.setMajorTickSpacing(5);
        textSizeSlider.setMinorTickSpacing(1);
        textSizeSlider.setPaintTicks(true);
        textSizeSlider.setPaintLabels(true);
        textSizeSlider.addChangeListener(e -> {
            // Adjust text size and component size
            int newSize = textSizeSlider.getValue();
            Font newFont = new Font("SansSerif", Font.PLAIN, newSize);
            usernameLabel.setFont(newFont);
            usernameField.setFont(newFont);
            passwordLabel.setFont(newFont);
            passwordField.setFont(newFont);
            loginButton.setFont(newFont);
            cancelButton.setFont(newFont);
            settingsButton.setFont(newFont);

            // Update component size
            updateComponentSize(panel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, cancelButton, settingsButton);
        });

        // Apply button
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> {
            // Close dialog after applying settings
            dialog.dispose();
        });

        dialog.add(textSizeSlider);
        dialog.add(new JLabel()); // Add an empty label for spacing
        dialog.add(applyButton);

        // Set dialog location relative to parent frame
        dialog.setSize(300, 150);

        Point parentLocation = parentFrame.getLocation();
        int x = parentLocation.x;
        int y = parentLocation.y + 350;
        dialog.setLocation(x, y);

        dialog.setVisible(true);

    }

    private static void updateComponentSize(JPanel panel, JLabel usernameLabel, JTextField usernameField,
                                            JLabel passwordLabel, JPasswordField passwordField,
                                            JButton loginButton, JButton cancelButton, JButton settingsButton) {
        // Calculate maximum text width
        int maxTextWidth = Math.max(usernameLabel.getPreferredSize().width, passwordLabel.getPreferredSize().width);
        maxTextWidth = Math.max(maxTextWidth, loginButton.getPreferredSize().width);
        maxTextWidth = Math.max(maxTextWidth, cancelButton.getPreferredSize().width);
        maxTextWidth = Math.max(maxTextWidth, settingsButton.getPreferredSize().width);

        // Set new component bounds
        usernameLabel.setBounds(50, 100, maxTextWidth, 25);
        usernameField.setBounds(50 + maxTextWidth + 10, 100, 200, 25);
        passwordLabel.setBounds(50, 150, maxTextWidth, 25);
        passwordField.setBounds(50 + maxTextWidth + 10, 150, 200, 25);
        loginButton.setBounds(130, 200, 100, 40);
        cancelButton.setBounds(250, 200, 100, 40);
        settingsButton.setBounds(130, 250, 100, 40);

        // Update panel size
        panel.setPreferredSize(new Dimension(400, 350));
        panel.revalidate();
        panel.repaint();
    }

    private static void showAdditionalSettingsDialog(JFrame parentFrame) {
        // Create and show additional settings dialog
        JOptionPane.showMessageDialog(parentFrame, "Additional settings dialog will be shown here.");
    }
}
