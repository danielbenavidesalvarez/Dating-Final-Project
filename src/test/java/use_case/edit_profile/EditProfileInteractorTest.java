package use_case.edit_profile;

import entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EditProfileInteractorTest {

    private EditProfileInteractor createInteractorWithMocks(
            UserDataAccessInterface mockDataAccess, EditProfileOutputBoundary mockOutputBoundary) {
        return new EditProfileInteractor(mockOutputBoundary, mockDataAccess);
    }

    @Test
    void testEditProfileSuccess() {
        // Mock dependencies
        UserDataAccessInterface mockDataAccess = mock(UserDataAccessInterface.class);
        EditProfileOutputBoundary mockOutputBoundary = mock(EditProfileOutputBoundary.class);
        EditProfileInteractor interactor = createInteractorWithMocks(mockDataAccess, mockOutputBoundary);

        // Test data
        EditProfileInputData inputData = new EditProfileInputData("123", "John Doe", 25, "Reading");
        User mockUser = mock(User.class);

        // Mock behavior
        when(mockDataAccess.findById("123")).thenReturn(mockUser);

        // Execute use case
        interactor.execute(inputData);

        // Verify user updates
        verify(mockUser).setName("John Doe");
        verify(mockUser).setAge(25);
        verify(mockUser).setInterests("Reading");
        verify(mockDataAccess).save(mockUser);

        // Capture and verify output boundary
        ArgumentCaptor<EditProfileOutputData> captor = ArgumentCaptor.forClass(EditProfileOutputData.class);
        verify(mockOutputBoundary).present(captor.capture());

        EditProfileOutputData capturedOutput = captor.getValue();
        assertTrue(capturedOutput.isSuccess());
        assertEquals("Profile updated successfully", capturedOutput.getMessage());
    }

    @Test
    void testEditProfileValidationFailure_NameEmpty() {
        // Mock dependencies
        EditProfileOutputBoundary mockOutputBoundary = mock(EditProfileOutputBoundary.class);
        UserDataAccessInterface mockDataAccess = mock(UserDataAccessInterface.class);
        EditProfileInteractor interactor = createInteractorWithMocks(mockDataAccess, mockOutputBoundary);

        // Invalid input data: Empty name
        EditProfileInputData invalidInputData = new EditProfileInputData("123", "", 25, "Reading");

        // Execute use case
        interactor.execute(invalidInputData);

        // Capture and verify output boundary
        ArgumentCaptor<EditProfileOutputData> captor = ArgumentCaptor.forClass(EditProfileOutputData.class);
        verify(mockOutputBoundary).present(captor.capture());

        EditProfileOutputData capturedOutput = captor.getValue();
        assertFalse(capturedOutput.isSuccess());
        assertEquals("Invalid input: Name cannot be empty", capturedOutput.getMessage());
    }

    @Test
    void testEditProfileValidationFailure_AgeInvalid() {
        // Mock dependencies
        EditProfileOutputBoundary mockOutputBoundary = mock(EditProfileOutputBoundary.class);
        UserDataAccessInterface mockDataAccess = mock(UserDataAccessInterface.class);
        EditProfileInteractor interactor = createInteractorWithMocks(mockDataAccess, mockOutputBoundary);

        // Invalid input data: Negative age
        EditProfileInputData invalidInputData = new EditProfileInputData("123", "John Doe", -1, "Reading");

        // Execute use case
        interactor.execute(invalidInputData);

        // Capture and verify output boundary
        ArgumentCaptor<EditProfileOutputData> captor = ArgumentCaptor.forClass(EditProfileOutputData.class);
        verify(mockOutputBoundary).present(captor.capture());

        EditProfileOutputData capturedOutput = captor.getValue();
        assertFalse(capturedOutput.isSuccess());
        assertEquals("Invalid input: Age must be a positive number", capturedOutput.getMessage());
    }

    @Test
    void testEditProfileValidationFailure_InterestsEmpty() {
        // Mock dependencies
        EditProfileOutputBoundary mockOutputBoundary = mock(EditProfileOutputBoundary.class);
        UserDataAccessInterface mockDataAccess = mock(UserDataAccessInterface.class);
        EditProfileInteractor interactor = createInteractorWithMocks(mockDataAccess, mockOutputBoundary);

        // Invalid input data: Empty interests
        EditProfileInputData invalidInputData = new EditProfileInputData("123", "John Doe", 25, "");

        // Execute use case
        interactor.execute(invalidInputData);

        // Capture and verify output boundary
        ArgumentCaptor<EditProfileOutputData> captor = ArgumentCaptor.forClass(EditProfileOutputData.class);
        verify(mockOutputBoundary).present(captor.capture());

        EditProfileOutputData capturedOutput = captor.getValue();
        assertFalse(capturedOutput.isSuccess());
        assertEquals("Invalid input: Interests cannot be empty", capturedOutput.getMessage());
    }

    @Test
    void testEditProfileUserNotFound() {
        // Mock dependencies
        UserDataAccessInterface mockDataAccess = mock(UserDataAccessInterface.class);
        EditProfileOutputBoundary mockOutputBoundary = mock(EditProfileOutputBoundary.class);
        EditProfileInteractor interactor = createInteractorWithMocks(mockDataAccess, mockOutputBoundary);

        // Test data
        EditProfileInputData inputData = new EditProfileInputData("123", "John Doe", 25, "Reading");

        // Mock behavior: User not found
        when(mockDataAccess.findById("123")).thenReturn(null);

        // Execute use case
        interactor.execute(inputData);

        // Capture and verify output boundary
        ArgumentCaptor<EditProfileOutputData> captor = ArgumentCaptor.forClass(EditProfileOutputData.class);
        verify(mockOutputBoundary).present(captor.capture());

        EditProfileOutputData capturedOutput = captor.getValue();
        assertFalse(capturedOutput.isSuccess());
        assertEquals("User not found", capturedOutput.getMessage());
    }
}
