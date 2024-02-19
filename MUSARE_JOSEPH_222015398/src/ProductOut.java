import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProductOut extends JFrame implements ActionListener {

    private JLabel barcodeLabel, categoryLabel, nameLabel, costLabel, quantityLabel;
    private JTextField barcodeField, categoryField, nameField, costField, quantityField;
    private JButton addButton,backButton;

    public ProductOut() {
        setTitle("OUT Product");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        barcodeLabel = new JLabel("Barcode:");
        barcodeLabel.setBounds(40, 30, 80, 25);
        add(barcodeLabel);

        barcodeField = new JTextField();
        barcodeField.setBounds(130, 30, 200, 25);
        add(barcodeField);

        categoryLabel = new JLabel("shop id:");
        categoryLabel.setBounds(40, 70, 80, 25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(130, 70, 200, 25);
        add(categoryField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 110, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 110, 200, 25);
        add(nameField);

        costLabel = new JLabel("Cost:");
        costLabel.setBounds(40, 150, 80, 25);
        add(costLabel);

        costField = new JTextField();
        costField.setBounds(130, 150, 200, 25);
        add(costField);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(40, 190, 80, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(130, 190, 200, 25);
        add(quantityField);

        addButton = new JButton("out Product");
        addButton.setBounds(130, 230, 120, 30);
        addButton.addActionListener(this);
        add(addButton);
        
        backButton = new JButton("BACK");
        backButton.setBounds(260, 230, 120, 30);
        backButton.addActionListener(this);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addProductToDatabase();
        }else if (e.getSource() == backButton) {
        	new Report().setVisible(true);
        	dispose();
        }
        
    }

    private void addProductToDatabase() {
        String barcode = barcodeField.getText();
        String category = categoryField.getText();
        
        double cost = Double.parseDouble(costField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        double totalCost = cost * quantity;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musare_joseph_sms",  "222015398", "222015398");

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO product_out (barcode, shop_id,  cost, quantity,total) VALUES ( ?, ?, ?, ?,?)");
            stmt.setString(1, barcode);
            stmt.setString(2, category);
            
            stmt.setDouble(3, cost);
            stmt.setInt(4, quantity);
            stmt.setDouble(5, totalCost);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Product added successfully.");
                // Clear input fields after successful addition
                barcodeField.setText("");
                categoryField.setText("");
                nameField.setText("");
                costField.setText("");
                quantityField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product.");
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ProductOut productIn = new ProductOut();
        productIn.setVisible(true);
    }
}
