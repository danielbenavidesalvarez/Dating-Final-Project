package use_case.like;
import java.util.Objects;
/**
 * Input data for the Like use case.
 */
public class LikeInputData {
    private final String userId;
    private final String likedUserId;

    public LikeInputData(String userId, String likedUserId) {
        this.userId = userId;
        this.likedUserId = likedUserId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLikedUserId() {
        return likedUserId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeInputData that = (LikeInputData) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(likedUserId, that.likedUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, likedUserId);
    }


}
