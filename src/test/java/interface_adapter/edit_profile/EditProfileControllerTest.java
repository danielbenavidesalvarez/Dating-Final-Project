package interface_adapter.edit_profile;

import interface_adapter.edit_profile.EditProfileController;
import org.junit.jupiter.api.Test;
import use_case.edit_profile.EditProfileInputBoundary;

import static org.mockito.Mockito.*;

class EditProfileControllerTest {

    @Test
    void testHandleEditProfile() {
        EditProfileInputBoundary mockInteractor = mock(EditProfileInputBoundary.class);

        EditProfileController controller = new EditProfileController(mockInteractor);

        String userId = "123";
        String name = "John Doe";
        int age = 30;
        String interests = "Reading, Hiking";

        controller.handleEditProfile(userId, name, age, interests);

        verify(mockInteractor).execute(argThat(inputData ->
                inputData.getUserId().equals(userId) &&
                        inputData.getName().equals(name) &&
                        inputData.getAge() == age &&
                        inputData.getInterests().equals(interests)
        ));
    }
}
