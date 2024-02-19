import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

    private JLabel titleLabel, firstNameLabel, lastNameLabel, usernameLabel, phoneLabel, emailLabel, passwordLabel, addressLabel;
    private JTextField firstNameField, lastNameField, usernameField, phoneField, emailField, addressField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;

    public Register() {
        setTitle("Register");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(250, 20, 100, 30);
        add(titleLabel);

        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(40, 70, 80, 25);
        add(firstNameLabel);

        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(40, 110, 80, 25);
        add(lastNameLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 150, 80, 25);
        add(usernameLabel);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(40, 190, 80, 25);
        add(phoneLabel);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 230, 80, 25);
        add(emailLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 270, 80, 25);
        add(passwordLabel);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(40, 310, 80, 25);
        add(addressLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(130, 70, 200, 25);
        add(firstNameField);

        lastNameField = new JTextField();
        lastNameField.setBounds(130, 110, 200, 25);
        add(lastNameField);

        usernameField = new JTextField();
        usernameField.setBounds(130, 150, 200, 25);
        add(usernameField);

        phoneField = new JTextField();
        phoneField.setBounds(130, 190, 200, 25);
        add(phoneField);

        emailField = new JTextField();
        emailField.setBounds(130, 230, 200, 25);
        add(emailField);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 270, 200, 25);
        add(passwordField);

        addressField = new JTextField();
        addressField.setBounds(130, 310, 200, 25);
        add(addressField);

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 370, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(300, 370, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            registerUser();
        } else if (e.getSource() == loginButton) {
            // Open login form
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }

    private void registerUser() {
        // Retrieve input values
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String address = addressField.getText();

        // Insert into database (Replace with your database logic)
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musare_joseph_sms", "222015398", "222015398");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO admin (firstname, lastname, username, phone, email, password, address) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.setString(7, address);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Register register = new Register();
        register.setVisible(true);
    }
}
