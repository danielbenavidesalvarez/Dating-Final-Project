import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteToFirebase {
    public static void saveUserProfile(String username, int age, String gender, String location, List<String> interests) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        User user = new User(username, age, gender, location, interests); // Create a User object
        ref.child(username).setValueAsync(user); // Save the user object under their userId
        System.out.println("User profile saved!");
    }

    public static void editUserProfile(String username, String fieldChange, Object newValue) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(username);
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put(fieldChange, newValue);

        ref.updateChildrenAsync(updateValues);




    }
}
