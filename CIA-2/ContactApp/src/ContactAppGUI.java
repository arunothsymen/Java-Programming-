import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ContactAppGUI extends JFrame {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/contacts";
    private static final String USER = "root";
    private static final String PASS = "Samsymenraj@01";

    private JTextField nameField, emailField, numberField;
    private JTextArea outputArea;

    public ContactAppGUI() {
        super("Contact Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initializeUI();

        // Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Create table if not exists
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            createTable(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        numberField = new JTextField(20);

        JButton addButton = new JButton("Add Contact");
        JButton viewButton = new JButton("View Contacts");
        JButton updateButton = new JButton("Update Contact");
        JButton deleteButton = new JButton("Delete Contact");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        addButton.addActionListener(e -> addContact());
        viewButton.addActionListener(e -> viewContacts());
        updateButton.addActionListener(e -> updateContact());
        deleteButton.addActionListener(e -> deleteContact());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(nameField, gbc);
        gbc.gridy++;
        panel.add(emailField, gbc);
        gbc.gridy++;
        panel.add(numberField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        panel.add(addButton, gbc);
        gbc.gridy++;
        panel.add(viewButton, gbc);
        gbc.gridy++;
        panel.add(updateButton, gbc);
        gbc.gridy++;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        add(panel);
    }

    private void createTable(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "phone_number VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addContact() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String name = nameField.getText();
            String email = emailField.getText();
            String number = numberField.getText();

            String insertSQL = "INSERT INTO contacts (name, email, phone_number) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, number);
                pstmt.executeUpdate();
                outputArea.setText("Contact added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewContacts() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM contacts")) {

            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String number = rs.getString("phone_number");
                result.append("ID: ").append(id).append(", Name: ").append(name)
                        .append(", Email: ").append(email).append(", Number: ").append(number).append("\n");
            }
            outputArea.setText(result.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateContact() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            int contactId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the contact to update:"));

            String newName = JOptionPane.showInputDialog("Enter new name:");
            String newEmail = JOptionPane.showInputDialog("Enter new email:");
            String newNumber = JOptionPane.showInputDialog("Enter new number:");

            String updateSQL = "UPDATE contacts SET name=?, email=?, phone_number=? WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setString(1, newName);
                pstmt.setString(2, newEmail);
                pstmt.setString(3, newNumber);
                pstmt.setInt(4, contactId);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    outputArea.setText("Contact updated successfully!");
                } else {
                    outputArea.setText("No contact found with ID " + contactId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteContact() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            int contactId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the contact to delete:"));

            String deleteSQL = "DELETE FROM contacts WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, contactId);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    outputArea.setText("Contact deleted successfully!");
                } else {
                    outputArea.setText("No contact found with ID " + contactId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactAppGUI());
    }
}
