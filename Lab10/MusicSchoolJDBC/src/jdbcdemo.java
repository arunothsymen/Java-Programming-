import java.sql.*;

public class jdbcdemo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/music_school";
        String userName = "root";
        String passWord = "Samsymenraj@01";
        String query = "select * from student";

        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Id is: " + rs.getInt(1));
                System.out.println("Name is: " + rs.getString(2));
                System.out.println("Age is: " + rs.getInt(3));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }
}