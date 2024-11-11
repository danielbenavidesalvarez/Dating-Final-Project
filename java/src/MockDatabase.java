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

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    // Method to filter users based on age range, location, and gender preference
    public List<User> filterUsers(int minAge, int maxAge, String location, String userGender, User currentUser) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (currentUser != null && user.getUsername().equalsIgnoreCase(currentUser.getUsername())) {
                continue;
            }

            boolean matchesLocation = user.getLocation().equalsIgnoreCase(location);
            boolean matchesAge = user.getAge() >= minAge && user.getAge() <= maxAge;

            boolean matchesGender = false;
            if (userGender.equalsIgnoreCase("Male")) {
                matchesGender = user.getGender().equalsIgnoreCase("Female");
            } else if (userGender.equalsIgnoreCase("Female")) {
                matchesGender = user.getGender().equalsIgnoreCase("Male");
            } else {
                matchesGender = user.getGender().equalsIgnoreCase("Male") ||
                        user.getGender().equalsIgnoreCase("Female");
            }

            if (matchesAge && matchesLocation && matchesGender) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    // Method to check for mutual likes
    public boolean isMutualLike(User currentUser, String likedUsername) {
        User likedUser = findUserByUsername(likedUsername);
        return likedUser != null && likedUser.getLikes().contains(currentUser.getUsername());
    }
}
