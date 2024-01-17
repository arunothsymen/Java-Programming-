import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MusicSchoolApp extends JFrame {

    private JTextField idField, nameField, ageField;
    private JButton addButton, displayButton, updateButton, deleteButton;

    private static final String url = "jdbc:mysql://localhost:3306/music_school";
    private static final String userName = "root";
    private static final String passWord = "Samsymenraj@01";

    public MusicSchoolApp() {
        super("Music School Application");

        idField = new JTextField(10);
        nameField = new JTextField(20);
        ageField = new JTextField(5);

        addButton = new JButton("Add Student");
        displayButton = new JButton("Display Students");
        updateButton = new JButton("Update Student");
        deleteButton = new JButton("Delete Student");

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("       ID:"));
        add(idField);

        add(new JLabel("       Name:"));
        add(nameField);

        add(new JLabel("       Age:"));
        add(ageField);

        add(addButton);
        add(displayButton);
        add(updateButton);
        add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent() {
        try (Connection con = DriverManager.getConnection(url, userName, passWord)) {
            String query = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, Integer.parseInt(idField.getText()));
                pst.setString(2, nameField.getText());
                pst.setInt(3, Integer.parseInt(ageField.getText()));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStudents() {
        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM student")) {

            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                result.append("Id: ").append(rs.getInt(1)).append(", ")
                        .append("Name: ").append(rs.getString(2)).append(", ")
                        .append("Age: ").append(rs.getInt(3)).append("\n");
            }

            JOptionPane.showMessageDialog(this, result.toString(), "Students", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        try (Connection con = DriverManager.getConnection(url, userName, passWord)) {
            String query = "UPDATE student SET name = ?, age = ? WHERE id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, nameField.getText());
                pst.setInt(2, Integer.parseInt(ageField.getText()));
                pst.setInt(3, Integer.parseInt(idField.getText()));
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        try (Connection con = DriverManager.getConnection(url, userName, passWord)) {
            String query = "DELETE FROM student WHERE id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, Integer.parseInt(idField.getText()));
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusicSchoolApp();
            }
        });
    }
}
