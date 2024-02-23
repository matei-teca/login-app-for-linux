import javax.swing.*;
import java.awt.*;

public class FirstSwingExample {
    // Predefined username and password
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");//creating instance of JButton
        JButton cancelButton = new JButton("Cancel");//creating instance of JButton
        JButton settingsButton = new JButton("Settings");//creating instance of JButton

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

        // Adding ActionListener to the Cancel button
        cancelButton.addActionListener(e -> {
            // Clear fields when Cancel button is clicked
            usernameField.setText("");
            passwordField.setText("");
        });

        // Adding ActionListener to the Settings button
        settingsButton.addActionListener(e -> {
            // Show settings dialog with options
            String[] options = {"Other Settings", "Accessibility", "Security", "Appearance"};
            int choice = JOptionPane.showOptionDialog(f, "Choose an option:", "Settings", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 3) {
                // Change Background Color
                Color newColor = JColorChooser.showDialog(f, "Choose Background Color", panel.getBackground());
                if (newColor != null) {
                    panel.setBackground(newColor);
                }
            } else if (choice == 2) {
                // Security Settings (Biometrics)
                showBiometricsSettingsDialog(f);
            } else if (choice == 1) {
                // Resizable Text
                showResizableTextDialog(f, panel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, cancelButton, settingsButton);
            } else if (choice == 0) {
                // Other Settings
                // Show additional settings dialog
                showAdditionalSettingsDialog(f);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        gbc.gridy = 3;
        panel.add(cancelButton, gbc);

        gbc.gridy = 4;
        panel.add(settingsButton, gbc);

        f.setSize(400, 350);//400 width and 350 height
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Calculate center position relative to screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - f.getWidth()) / 2;
        int y = (screenSize.height - f.getHeight()) / 2;
        f.setLocation(x, y);

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

//        Point parentLocation = parentFrame.getLocation();
//        int x = parentLocation.x;
//        int y = parentLocation.y + 350;
//        dialog.setLocation(x, y);

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

    private static void showBiometricsSettingsDialog(JFrame parentFrame) {
        // Create biometrics settings dialog
        JDialog dialog = new JDialog(parentFrame, "Biometrics Settings", true);
        dialog.setLayout(new GridLayout(2, 1));

        // Biometrics toggle
        JCheckBox biometricsCheckbox = new JCheckBox("Can we do it Tavi? :)");
        biometricsCheckbox.setSelected(false); // Default is disabled

        // Apply button
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> {
            boolean biometricsEnabled = biometricsCheckbox.isSelected();
            if (biometricsEnabled) {
                JOptionPane.showMessageDialog(dialog, "Biometric authentication enabled.");
                // Here you can add code to enable biometric authentication
            } else {
                JOptionPane.showMessageDialog(dialog, "Biometric authentication disabled.");
                // Here you can add code to disable biometric authentication
            }
            dialog.dispose();
        });

        dialog.add(biometricsCheckbox);
        dialog.add(applyButton);

        // Set dialog size and visibility
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
