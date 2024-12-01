import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import java.util.ArrayList;

public class UserConversationsRetriever {

    public static void printMessagesBetweenUsers(String user1, String user2) {
        String threadId = generateThreadId(user1, user2);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("threads").child(threadId).child("messages");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Messages between " + user1 + " and " + user2 + ":");
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    if (message != null) {
                        System.out.println(message);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error fetching messages: " + databaseError.getMessage());
            }
        });
    }

    private static String generateThreadId(String user1, String user2) {
        // Generate a unique thread ID based on participant usernames
        if (user1.compareTo(user2) < 0) {
            return user1 + "_" + user2;
        } else {
            return user2 + "_" + user1;
        }
    }

    public static void main(String[] args) {
        FirebaseInit.initializeFirebase();

        // Retrieve messages between johnDoe and janeDoe
        printMessagesBetweenUsers("johnDoe", "janeDoe");

        try {
            Thread.sleep(10000); // Wait to see updates in Firebase
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
