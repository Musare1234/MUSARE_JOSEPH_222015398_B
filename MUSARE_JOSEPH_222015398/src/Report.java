import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Report extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JButton productInButton, productOutButton, shopButton, supplierButton,updateButton,deleteButton;
    private JTable dataTable;

    public Report() {
        setTitle("Report");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Product Report");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        
        String[] columnNames = {"ID", "Barcode", "Category ID", "Name", "Cost", "Quantity", "Total Cost", "Created At"};
        fetchDataFromDatabase(columnNames);
        
        
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        productInButton = new JButton("Product In");
        productInButton.addActionListener(this);
        buttonPanel.add(productInButton);

        productOutButton = new JButton("Product Out");
        productOutButton.addActionListener(this);
        buttonPanel.add(productOutButton);

        shopButton = new JButton("Shop");
        shopButton.addActionListener(this);
        buttonPanel.add(shopButton);

        supplierButton = new JButton("Supplier");
        supplierButton.addActionListener(this);
        buttonPanel.add(supplierButton);
        
        updateButton = new JButton("UPDATE");
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);
        
        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        
       
    }
    
        private void fetchDataFromDatabase(String[] columnNames) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musare_joseph_sms",  "222015398", "222015398");

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM product");

                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                model.setRowCount(0); // Clear existing data from the table

                // Iterate through the ResultSet and add rows to the DefaultTableModel
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("id"),
                        rs.getString("barcode"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getDouble("cost"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_cost"),
                        rs.getTimestamp("created_at")
                    };
                    model.addRow(row);
                }
                
                dataTable = new JTable(model);
             // Add the JTable to a JScrollPane
                JScrollPane scrollPane = new JScrollPane(dataTable);

                // Add the JScrollPane to the JFrame
                add(scrollPane, BorderLayout.CENTER);

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                // Log the error for debugging
                e.printStackTrace();
                // Display a user-friendly error message
                JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        
        private void updateSelectedRow() {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
                return;
            }

            // Retrieve the data of the selected row
            int id = (int) dataTable.getValueAt(selectedRow, 0);
            String barcode = (String) dataTable.getValueAt(selectedRow, 1);
            
            String name = (String) dataTable.getValueAt(selectedRow, 3);
            double cost = (double) dataTable.getValueAt(selectedRow, 4);
            int quantity = (int) dataTable.getValueAt(selectedRow, 5);
            double totalCost = (double) dataTable.getValueAt(selectedRow, 6);
            

            // Display a dialog box or form to allow the user to update the data
            JTextField barcodeField = new JTextField(barcode);
            JTextField nameField = new JTextField(name);
            JTextField costField = new JTextField(String.valueOf(cost));
            JTextField quantityField = new JTextField(String.valueOf(quantity));
            JTextField totalCostField = new JTextField(String.valueOf(totalCost));

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Barcode:"));
            panel.add(barcodeField);
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Cost:"));
            panel.add(costField);
            panel.add(new JLabel("Quantity:"));
            panel.add(quantityField);
            panel.add(new JLabel("Total Cost:"));
            panel.add(totalCostField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Update Row", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Update the data in the database
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musare_joseph_sms", "222015398", "222015398");
                    PreparedStatement stmt = conn.prepareStatement("UPDATE product SET barcode=?, name=?, cost=?, quantity=?, total_cost=? WHERE id=?");
                    stmt.setString(1, barcodeField.getText());
                    stmt.setString(2, nameField.getText());
                    stmt.setDouble(3, Double.parseDouble(costField.getText()));
                    stmt.setInt(4, Integer.parseInt(quantityField.getText()));
                    stmt.setDouble(5, Double.parseDouble(totalCostField.getText()));
                    stmt.setInt(6, id);
                    int rowsAffected = stmt.executeUpdate();
                    conn.close();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Row updated successfully.");
                        fetchDataFromDatabase(null); // Update the table to reflect the changes
                        new Report().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update row.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
        
        private void deleteRowFromDatabase(int id) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musare_joseph_sms", "222015398", "222015398");
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM product WHERE id = ?");
                stmt.setInt(1, id);
                int rowsDeleted = stmt.executeUpdate();
                conn.close();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Row deleted successfully.");
                    fetchDataFromDatabase(null);
                    new Report().setVisible(true);// Refresh the table
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete row.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting row: " + ex.getMessage());
            }
        }
        

     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productInButton) {
            // Open ProductIn page
            ProductIn productIn = new ProductIn();
            productIn.setVisible(true);
            this.dispose();
        } else if (e.getSource() == productOutButton) {
            // Open ProductOut page
            ProductOut productOut = new ProductOut();
            productOut.setVisible(true);
            this.dispose();
        } else if (e.getSource() == shopButton) {
            // Open Shop page
            Shops Shops = new Shops();
            Shops.setVisible(true);
            this.dispose();
        } else if (e.getSource() == supplierButton) {
            // Open Supplier page
            Supplier supplier = new Supplier();
            supplier.setVisible(true);
            this.dispose();
        } else if (e.getSource() == updateButton) {
            // Open Supplier page
        	updateSelectedRow();
            
            this.dispose();
        } else if (e.getSource() == deleteButton) {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this row?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Retrieve the ID of the selected row
                int id = (int) dataTable.getValueAt(selectedRow, 0);
                // Delete the row from the database
                deleteRowFromDatabase(id);
            }
        }
        
        
    }

    public static void main(String[] args) {
        Report report = new Report();
        report.setVisible(true);
    }
}
