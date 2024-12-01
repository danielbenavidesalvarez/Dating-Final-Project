package interface_adapter.like;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.like.LikeInputBoundary;
import use_case.like.LikeInputData;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

class LikeControllerTest {

    private LikeInputBoundary mockInputBoundary;
    private LikeController controller;

    @BeforeEach
    void setUp() {
        mockInputBoundary = mock(LikeInputBoundary.class);
        controller = new LikeController(mockInputBoundary);
    }

    @Test
    void testLikeUser_ValidInput() {
        String likerId = "user1";
        String likedId = "user2";

        controller.likeUser(likerId, likedId);

        verify(mockInputBoundary, times(1)).execute(new LikeInputData(likerId, likedId));
    }

    @Test
    void testLikeUser_NullLikerId_ThrowsException() {
        String likedId = "user2";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> controller.likeUser(null, likedId));
        Assertions.assertEquals("Liker user ID cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testLikeUser_EmptyLikedId_ThrowsException() {
        String likerId = "user1";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> controller.likeUser(likerId, ""));
        Assertions.assertEquals("Liked user ID cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testLikeUser_LikingSelf_ThrowsException() {
        String likerId = "user1";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> controller.likeUser(likerId, likerId));
        Assertions.assertEquals("Users cannot like themselves.", exception.getMessage());
    }
}
