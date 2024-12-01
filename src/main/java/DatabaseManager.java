import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:datingapp.db";
    private Connection conn;

    public DatabaseManager() {
        connect();
        createTables();
    }

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

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

    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, age, gender, location) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getGender());
            pstmt.setString(4, user.getLocation());
            pstmt.executeUpdate();
            System.out.println("User added: " + user.getUsername());
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
//                return new User(
//                        rs.getString("username"),
//                        rs.getInt("age"),
//                        rs.getString("gender"),
//                        rs.getString("location"),
//                        new ArrayList<>(),
//                        rs.getString("password")
//                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding user: " + e.getMessage());
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
//                users.add(new User(
//                        rs.getString("username"),
//                        rs.getInt("age"),
//                        rs.getString("gender"),
//                        rs.getString("location"),
//                        new ArrayList<>(),
//                        rs.getString("password")
//                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }
        return users;
    }

    public boolean isMutualLike(String currentUser, String otherUsername) {
        return findUserByUsername(currentUser).getMutualLikes().contains(otherUsername);
    }

    public void sendMessage(String sender, String receiver, String content) {
        String sql = "INSERT INTO messages (sender, receiver, content) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sender);
            pstmt.setString(2, receiver);
            pstmt.setString(3, content);
            pstmt.executeUpdate();
            System.out.println("Message sent from " + sender + " to " + receiver);
        } catch (SQLException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    public List<Message> getMessagesForUser(String username) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE receiver = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(
                        rs.getString("sender"),
                        rs.getString("receiver"),
                        rs.getString("content"),
                        rs.getString("timestamp")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving messages: " + e.getMessage());
        }
        return messages;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
