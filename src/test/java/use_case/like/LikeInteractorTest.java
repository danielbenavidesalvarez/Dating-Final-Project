package use_case.like;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LikeInteractorTest {
    @Test
    void testLikeUserSuccess() {
        LikeUserDataAccessInterface dataAccess = mock(LikeUserDataAccessInterface.class);
        LikeOutputBoundary outputBoundary = mock(LikeOutputBoundary.class);

        User user = new CommonUser("user123", "password123");
        user.setUserId("user123");
        User likedUser = new CommonUser("user456", "password456");
        likedUser.setUserId("user456");

        when(dataAccess.findById("user123")).thenReturn(user);
        when(dataAccess.findById("user456")).thenReturn(likedUser);

        LikeInputData inputData = new LikeInputData("user123", "user456");
        LikeInteractor interactor = new LikeInteractor(outputBoundary, dataAccess);
        interactor.execute(inputData);

        verify(dataAccess).save(user);
        verify(outputBoundary).present(new LikeOutputData(true, "User liked successfully"));
    }

    @Test
    void testLikeUserNotFound() {
        LikeUserDataAccessInterface dataAccess = mock(LikeUserDataAccessInterface.class);
        LikeOutputBoundary outputBoundary = mock(LikeOutputBoundary.class);

        when(dataAccess.findById("user123")).thenReturn(null);

        LikeInputData inputData = new LikeInputData("user123", "user456");
        LikeInteractor interactor = new LikeInteractor(outputBoundary, dataAccess);
        interactor.execute(inputData);

        verify(outputBoundary).present(new LikeOutputData(false, "User not found"));
        verify(dataAccess, never()).save(any());
    }

    @Test
    void testLikedUserNotFound() {
        LikeUserDataAccessInterface dataAccess = mock(LikeUserDataAccessInterface.class);
        LikeOutputBoundary outputBoundary = mock(LikeOutputBoundary.class);

        User user = new CommonUser("user123", "password123");

        when(dataAccess.findById("user123")).thenReturn(user);
        when(dataAccess.findById("user456")).thenReturn(null);

        LikeInputData inputData = new LikeInputData("user123", "user456");
        LikeInteractor interactor = new LikeInteractor(outputBoundary, dataAccess);
        interactor.execute(inputData);

        verify(outputBoundary).present(new LikeOutputData(false, "Liked user not found"));
        verify(dataAccess, never()).save(any());
    }

    @Test
    void testDuplicateLike() {
        LikeUserDataAccessInterface dataAccess = mock(LikeUserDataAccessInterface.class);
        LikeOutputBoundary outputBoundary = mock(LikeOutputBoundary.class);

        User user = new CommonUser("user123", "password123");
        user.setUserId("user123");
        User likedUser = new CommonUser("user456", "password456");
        likedUser.setUserId("user456");

        // Simulate the user already liked the likedUser
        user.likeUser(likedUser);

        when(dataAccess.findById("user123")).thenReturn(user);
        when(dataAccess.findById("user456")).thenReturn(likedUser);

        LikeInputData inputData = new LikeInputData("user123", "user456");
        LikeInteractor interactor = new LikeInteractor(outputBoundary, dataAccess);
        interactor.execute(inputData);

        verify(outputBoundary).present(new LikeOutputData(false, "User already liked this person"));
        verify(dataAccess, never()).save(any());
    }

    @Test
    void testSelfLike() {
        LikeUserDataAccessInterface dataAccess = mock(LikeUserDataAccessInterface.class);
        LikeOutputBoundary outputBoundary = mock(LikeOutputBoundary.class);

        User user = new CommonUser("user123", "password123");
        user.setUserId("user123");

        when(dataAccess.findById("user123")).thenReturn(user);

        LikeInputData inputData = new LikeInputData("user123", "user123");
        LikeInteractor interactor = new LikeInteractor(outputBoundary, dataAccess);
        interactor.execute(inputData);

        verify(outputBoundary).present(new LikeOutputData(false, "Users cannot like themselves"));
        verify(dataAccess, never()).save(any());
    }
}
