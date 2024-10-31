import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = "password"; // Update with your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();

        // Call the method to add the user to the database
        addUser(username, password, firstName, lastName);

        scanner.close();
    }

    public static void addUser(String username, String password, String firstName, String lastName) {
        String sql = "INSERT INTO users (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);

            // Execute the update
            pstmt.executeUpdate();

            System.out.println("User added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
}
