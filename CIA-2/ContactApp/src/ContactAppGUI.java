import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ContactAppGUI extends JFrame {
    private JTextField nameField, emailField, phoneField;
    private DefaultTableModel tableModel;
    private JTable contactTable;

    public ContactAppGUI() {
        setTitle("Contact Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");

        // Create table model and table
        tableModel = new DefaultTableModel();
        contactTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactTable);

        // Set layout
        setLayout(new BorderLayout());

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);

        // Add components to the main frame
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addContact());
        updateButton.addActionListener(e -> updateContact());
        deleteButton.addActionListener(e -> deleteContact());
        searchButton.addActionListener(e -> searchContacts());

        // Load contacts from the database
        loadContacts();
    }

    private void addContact() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "Samsymenraj@01");
            String query = "INSERT INTO contacts (name, email, phone_number) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nameField.getText());
                statement.setString(2, emailField.getText());
                statement.setString(3, phoneField.getText());
                statement.executeUpdate();
            }
            connection.close();
            loadContacts(); // Refresh the table
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}

    private void updateContact() {
        try {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a contact to update.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "Samsymenraj@01");
            String query = "UPDATE contacts SET name=?, email=?, phone_number=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nameField.getText());
                statement.setString(2, emailField.getText());
                statement.setString(3, phoneField.getText());
                statement.setInt(4, Integer.parseInt(contactTable.getValueAt(selectedRow, 0).toString())); // Assuming ID is in the first column
                statement.executeUpdate();
            }
            connection.close();
            loadContacts(); // Refresh the table
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteContact() {
        try {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmDialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmDialogResult != JOptionPane.YES_OPTION) {
                return;
            }

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "Samsymenraj@01");
            String query = "DELETE FROM contacts WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(contactTable.getValueAt(selectedRow, 0).toString())); // Assuming ID is in the first column
                statement.executeUpdate();
            }
            connection.close();
            loadContacts(); // Refresh the table
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadContacts() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "Samsymenraj@01");
            String query = "SELECT * FROM contacts";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                // Clear existing data
                tableModel.setRowCount(0);

                // Populate the table
                while (resultSet.next()) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(resultSet.getString("name"));
                    rowData.add(resultSet.getString("email"));
                    rowData.add(resultSet.getString("phone_number"));
                    tableModel.addRow(rowData);
                }}
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchContacts() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "Samsymenraj@01");
            String query = "SELECT * FROM contacts WHERE name LIKE ? OR email LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + nameField.getText() + "%");
                statement.setString(2, "%" + emailField.getText() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Clear existing data
                    tableModel.setRowCount(0);

                    // Populate the table
                    while (resultSet.next()) {
                        Vector<Object> rowData = new Vector<>();
                        rowData.add(resultSet.getString("id"));
                        rowData.add(resultSet.getString("name"));
                        rowData.add(resultSet.getString("email"));
                        rowData.add(resultSet.getString("phone_number"));
                        tableModel.addRow(rowData);
                    }
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	ContactAppGUI app = new ContactAppGUI();
            app.setVisible(true);
        });
    }}