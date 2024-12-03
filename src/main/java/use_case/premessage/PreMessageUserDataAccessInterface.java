package use_case.premessage;

import entity.User;

public interface PreMessageUserDataAccessInterface {
    User getUserByUsername(String username);
}
