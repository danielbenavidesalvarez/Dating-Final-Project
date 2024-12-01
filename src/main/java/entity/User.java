package entity;

import java.util.List;

/**
 * A User interface that defines the structure of user entities.
 */
public interface User {
    String getName();

    void setUserId(String userId);

    String getPassword();

    // Methods for additional fields
    int getAge();
    void setAge(int age);

    String getInterests();
    void setInterests(String interests);

    void setName(String name);

    String getUserId();

    void likeUser(User likedUser);

    // New methods for analytics
    List<String> getLikedUsers();
    int getLikesReceivedCount();

    // New methods for Firebase compatibility
    void setLikesReceivedCount(int count); // Setter for analytics data
    void setLikedUsers(List<String> likedUsers); // Setter for liked users
}

