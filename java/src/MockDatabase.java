import java.util.ArrayList;
import java.util.List;

public class MockDatabase {
    private List<User> users;

    public MockDatabase() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) { users.add(user); }
    public List<User> getUsers() { return users; }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    // New Method: Check if two users have mutually liked each other
    public boolean isMutualLike(User user1, String username2) {
        User user2 = findUserByUsername(username2);
        if (user2 == null) return false;
        return user1.getLikes().contains(username2) && user2.getLikes().contains(user1.getUsername());
    }
}

