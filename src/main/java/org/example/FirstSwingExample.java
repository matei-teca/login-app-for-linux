import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstSwingExample {
    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame

        JButton loginButton = new JButton("Login");//creating instance of JButton
        loginButton.setBounds(130,100,100, 40);//x axis, y axis, width, height

        // Adding ActionListener to the Login button
        loginButton.addActionListener(e -> {
            // Action to be performed when the Login button is clicked
            JOptionPane.showMessageDialog(f, "Login button clicked!");
        });

        JButton cancelButton = new JButton("Cancel");//creating instance of JButton
        cancelButton.setBounds(130,150,100, 40);//x axis, y axis, width, height

        JButton settingsButton = new JButton("Settings");//creating instance of JButton
        settingsButton.setBounds(130,200,100, 40);//x axis, y axis, width, height

        f.add(loginButton);//adding button in JFrame
        f.add(cancelButton);//adding button in JFrame
        f.add(settingsButton);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}  