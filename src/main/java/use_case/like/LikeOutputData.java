package use_case.like;

import java.util.Objects;

public class LikeOutputData {
    private final boolean success;
    private final String message;
    private final boolean match;

    public LikeOutputData(boolean success, String message, boolean match) {
        this.success = success;
        this.message = message;
        this.match = match;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMatch() {return match;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeOutputData that = (LikeOutputData) o;
        return success == that.success && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message);
    }
}
