package use_case.analytics;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalyticsInteractorTest {

    private AnalyticsUserDataAccessInterface userDataAccess;
    private AnalyticsOutputBoundary presenter;
    private AnalyticsInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(AnalyticsUserDataAccessInterface.class);
        presenter = mock(AnalyticsOutputBoundary.class);
        interactor = new AnalyticsInteractor(presenter, userDataAccess);
    }

    @Test
    void testExecute_WithValidData() {
        // Mock user object
        User user = mock(User.class);
        when(user.getUserId()).thenReturn("user123");
        when(user.getLikedUsers()).thenReturn(Set.of("user456", "user789"));
        when(user.getLikesReceivedCount()).thenReturn(5);

        // Mock userDataAccess methods
        when(userDataAccess.getUserById("user123")).thenReturn(user);
        when(userDataAccess.countMatches("user123")).thenReturn(3);
        when(userDataAccess.countSharedInterests("user123")).thenReturn(2);

        // Create input data
        AnalyticsInputData inputData = new AnalyticsInputData("user123");

        // Execute the interactor
        interactor.execute(inputData);

        // Verify presenter interactions
        verify(presenter, times(1)).present(argThat(outputData ->
                outputData.getLikesGiven() == 2 &&
                        outputData.getLikesReceived() == 5 &&
                        outputData.getMatches() == 3 &&
                        outputData.getSharedInterests() == 2
        ));
    }

    @Test
    void testExecute_UserNotFound() {
        // Mock userDataAccess to return null
        when(userDataAccess.getUserById("invalidUser")).thenReturn(null);

        // Create input data
        AnalyticsInputData inputData = new AnalyticsInputData("invalidUser");

        // Assert exception is thrown
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            interactor.execute(inputData);
        });

        assertEquals("User not found.", exception.getMessage());
    }
}
