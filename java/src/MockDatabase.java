import java.util.ArrayList;
import java.util.List;

public class MockDatabase {
    private List<User> users;

    public MockDatabase() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
