import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class WriteToFirebase {
    public static void saveUserProfile(String userId, String name, String email) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        User2 user = new User2(name, email); // Create a User object
        ref.child(userId).setValueAsync(user); // Save the user object under their userId
        System.out.println("User profile saved!");
    }
}
class User2 {
    public String name;
    public String email;

    public User2(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
