import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private int age;
    private String gender;
    private String location;
    private String interests;
    private Set<String> likes; // Set of usernames the user has liked
    private Set<String> mutualLikes; // Set of mutual likes

    public User(String username, int age, String gender, String location, String interests) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.interests = interests;
        this.likes = new HashSet<>();
        this.mutualLikes = new HashSet<>();
    }

    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getLocation() { return location; }
    public String getInterests() { return interests; }

    public Set<String> getLikes() { return likes; }
    public Set<String> getMutualLikes() { return mutualLikes; }

    public void likeUser(String username) {
        likes.add(username);
    }

    public void addMutualLike(String username) {
        mutualLikes.add(username);
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Age: " + age + ", Gender: " + gender +
                ", Location: " + location + ", Interests: " + interests;
    }
}
