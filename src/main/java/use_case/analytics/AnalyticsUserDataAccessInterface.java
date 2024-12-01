package use_case.analytics;

import entity.User;

/**
 * Interface for data access methods required by the Analytics use case.
 */
public interface AnalyticsUserDataAccessInterface {
    /**
     * Retrieves a user by their unique user ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return the User object, or null if not found.
     */
    User getUserById(String userId);

    /**
     * Counts the number of matches for a given user ID.
     *
     * @param userId the ID of the user to check matches for.
     * @return the number of matches.
     */
    int countMatches(String userId);

    /**
     * Counts the number of users who share interests with the given user ID.
     *
     * @param userId the ID of the user to check shared interests for.
     * @return the number of users with shared interests.
     */
    int countSharedInterests(String userId);

    int countLikesReceived(String userId);
    int countLikesGiven(String userId);
}
