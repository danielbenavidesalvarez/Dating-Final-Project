package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A concrete implementation of the User interface with added fields and methods.
 */
public class CommonUser implements User {
    private String userId;
    private String name;
    private String password;
    private int age;
    private String interests; // Comma-separated interests as a string
    private List<String> likedUsers = new ArrayList<>();
    private int likesReceivedCount;
    private String gender;
    private Map<String, List<MessageThread>> conversations;

    public CommonUser() {
        // Default constructor required for Firebase deserialization
    }

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.userId = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public void likeUser(User likedUser) {
        if (likedUser != null && likedUser.getUserId() != null) {
            likedUsers.add(likedUser.getUserId());
        } else {
            throw new IllegalArgumentException("Liked user must have a valid user ID.");
        }
    }

    @Override
    public List<String> getLikedUsers() {
        return likedUsers;
    }

    @Override
    public void setLikedUsers(List<String> likedUsers) {
        this.likedUsers = likedUsers;
    }

    @Override
    public int getLikesReceivedCount() {
        return likesReceivedCount;
    }

    @Override
    public void setLikesReceivedCount(int count) {
        this.likesReceivedCount = count;
    }

    public boolean hasLiked(String userId) {
        return likedUsers.contains(userId);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
