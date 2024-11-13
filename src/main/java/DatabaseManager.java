import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:datingapp.db";
    private Connection conn;

    // Establish connection to the database
    public DatabaseManager() {
        connect();
        createTables();
    }

    // Method to connect to the database
    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    // Create tables if they don't exist
    private void createTables() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT UNIQUE," +
                "age INTEGER," +
                "gender TEXT," +
                "location TEXT" +
                ");";

        String createMessagesTable = "CREATE TABLE IF NOT EXISTS messages (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sender TEXT," +
                "receiver TEXT," +
                "content TEXT," +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createMessagesTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to create tables: " + e.getMessage());
        }
    }

    // Method to insert a user into the database
    public void addUser(String username, int age, String gender, String location) {
        String sql = "INSERT INTO users (username, age, gender, location) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, location);
            pstmt.executeUpdate();
            System.out.println("User added: " + username);
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    // Method to retrieve all users
    public void getAllUsers() {
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username") +
                        ", Age: " + rs.getInt("age") +
                        ", Gender: " + rs.getString("gender") +
                        ", Location: " + rs.getString("location"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }
    }

    // Close the connection when done
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.addUser("luiscal", 21, "Male", "Toronto");
        dbManager.addUser("danny", 24, "Male", "Vancouver");

        System.out.println("\nUsers in the database:");
        dbManager.getAllUsers();
        dbManager.close();
    }
}
