import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstSwingExample {
    // Predefined username and password
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame

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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        JButton cancelButton = new JButton("Cancel");//creating instance of JButton
        cancelButton.setBounds(250, 200, 100, 40);//x axis, y axis, width, height

        // Adding ActionListener to the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear fields when Cancel button is clicked
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        JButton settingsButton = new JButton("Settings");//creating instance of JButton
        settingsButton.setBounds(130, 250, 100, 40);//x axis, y axis, width, height

        // Adding ActionListener to the Settings button
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show settings dialog with options
                String[] options = {"Change Background Color", "Other Settings"};
                int choice = JOptionPane.showOptionDialog(f, "Choose an option:", "Settings", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (choice == 0) {
                    // Change Background Color
                    Color newColor = JColorChooser.showDialog(f, "Choose Background Color", f.getContentPane().getBackground());
                    if (newColor != null) {
                        f.getContentPane().setBackground(newColor);
                    }
                } else if (choice == 1) {
                    // Other Settings
                    // Show additional settings dialog
                    showAdditionalSettingsDialog(f);
                }
            }
        });

        f.add(usernameLabel);
        f.add(usernameField);
        f.add(passwordLabel);
        f.add(passwordField);
        f.add(loginButton);//adding button in JFrame
        f.add(cancelButton);//adding button in JFrame
        f.add(settingsButton);//adding button in JFrame

        f.setSize(400, 350);//400 width and 350 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

    private static void showAdditionalSettingsDialog(JFrame parentFrame) {
        // Create and show additional settings dialog
        JDialog dialog = new JDialog(parentFrame, "Additional Settings", true);
        dialog.setLayout(new GridLayout(2, 1));

        // Accessibility settings
        JLabel accessibilityLabel = new JLabel("Accessibility Settings:");
        JCheckBox accessibilityCheckBox = new JCheckBox("Enable Accessibility Features");
        dialog.add(accessibilityLabel);
        dialog.add(accessibilityCheckBox);

        // Apply button
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Apply accessibility settings
                if (accessibilityCheckBox.isSelected()) {
                    // Enable accessibility features
                    JOptionPane.showMessageDialog(parentFrame, "Accessibility features enabled.");
                }

                // Close dialog after applying settings
                dialog.dispose();
            }
        });
        dialog.add(applyButton);

        // Set dialog size and visibility
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
