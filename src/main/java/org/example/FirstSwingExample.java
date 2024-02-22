import javax.swing.*;
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

        f.add(usernameLabel);
        f.add(usernameField);
        f.add(passwordLabel);
        f.add(passwordField);
        f.add(loginButton);//adding button in JFrame
        f.add(cancelButton);//adding button in JFrame

        f.setSize(400, 300);//400 width and 300 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
