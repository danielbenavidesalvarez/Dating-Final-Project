package use_case.like;

import entity.User;

public interface LikeUserDataAccessInterface {
    User findById(String userId);

    void save(User user);
}
