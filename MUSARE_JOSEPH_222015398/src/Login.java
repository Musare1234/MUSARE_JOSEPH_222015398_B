import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        setTitle("STOCK MANAGEMENT SYSTEM - Login");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Login to STOCK MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(40, 20, 320, 30);
        add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 70, 80, 25);
        add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 110, 80, 25);
        add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 70, 200, 25);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 110, 200, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 170, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            loginUser();
        }
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musare_joseph_sms", "222015398", "222015398");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                Report report = new Report();
                report.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}
