package data_access;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import entity.Message;
import entity.MessageCallback;


public class UserConversationsRetriever {

    public static void printMessagesBetweenUsers(String user1, String user2, MessageCallback callback) {
        String threadId = generateThreadId(user1, user2);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("threads").child(threadId).child("messages");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> messages = new ArrayList<>();
                System.out.println("Messages between " + user1 + " and " + user2 + ":");
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = Message.populateMessageFromSnapshot(messageSnapshot);
                    if (message != null) {
                        messages.add(message);
                        System.out.println(message.getContent());
                    }
                }
                callback.update(messages);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error fetching messages: " + databaseError.getMessage());
                callback.update(new ArrayList<>());
            }
        });
    }

    public static String generateThreadId(String user1, String user2) {
        // Generate a unique thread ID based on participant usernames
        if (user1.compareTo(user2) < 0) {
            return user1 + "_" + user2;
        } else {
            return user2 + "_" + user1;
        }
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
        UserConversationsRetriever retriever = new UserConversationsRetriever();

        // Retrieve messages between johnDoe and janeDoe
        printMessagesBetweenUsers("johnDoe", "janeDoe", new MessageCallback() {

            @Override
            public void update(List<Message> messages) {
                for (Message message : messages) {
                    System.out.println(message.getId());
                }
            }
        });
        retriever.sendMessage("johnDoe","janeDoe", "hello world" );
        retriever.sendMessage("aryan","me", "luis gay" );
        try {
            Thread.sleep(10000); // Wait to see updates in Firebase
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}