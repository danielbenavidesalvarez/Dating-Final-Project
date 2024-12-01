package use_case.edit_profile;

import entity.User;

/**
 * Interface for accessing user data.
 */
public interface UserDataAccessInterface {
    User findById(String userId);
    void save(User user);
}
