import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteToFirebase {
    public static void saveUserProfile(String username, int age, String gender, String location, List<String> interests, String pw) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        User user = new User(age, gender, interests, location, pw, username); // Create a User object
        ref.child(username).setValueAsync(user); // Save the user object under their userId
        editUserProfile(username, "pw", pw);
        try {
            Thread.sleep(5000); // Adjust time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("User profile saved!");
    }

    public static void editUserProfile(String username, String fieldChange, Object newValue) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(username);
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put(fieldChange, newValue);

        ref.updateChildrenAsync(updateValues);




    }


    public void sendMessage(String sender, String receiver, String content) {
        // Firebase reference to add a new message
        String threadId = UserConversationsRetriever.generateThreadId(sender, receiver);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("threads").child(threadId).child("messages");
        String messageId = ref.push().getKey();
        Message message = new Message(sender, receiver, content);
        message.setId(messageId);
        if (messageId != null) {
            ref.child(messageId).setValueAsync(message);
        }
    }

    public static void main(String[] args) {
        FirebaseInit.initializeFirebase();
        List<String> interests = new ArrayList<>();
        interests.add("abc");
        WriteToFirebase.saveUserProfile("tammy", 20, "male", "toronto", interests, "food");
        try {
            Thread.sleep(5000); // Adjust time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 5;
//        WriteToFirebase.editUserProfile("tammy", "pw", i);
        try {
            Thread.sleep(5000); // Adjust time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
