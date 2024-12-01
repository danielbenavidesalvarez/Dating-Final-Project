import com.google.firebase.database.DataSnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
    private String id;

    // Updated Constructor
    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.content = content;
        this.receiver = receiver;
        LocalDateTime now = LocalDateTime.now();
        String formattedTimestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.timestamp = formattedTimestamp;
    }
    public Message(){

    }

    public String getReceiver(){
        return receiver;
    }
    public String getSender() { return sender; }
    public String getContent() { return content; }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Message populateMessageFromSnapshot(DataSnapshot messageSnapshot) {
        // Extract each field from the DataSnapshot
        String sender = messageSnapshot.child("sender").getValue(String.class);
        String receiver = messageSnapshot.child("receiver").getValue(String.class);
        String content = messageSnapshot.child("content").getValue(String.class);
        String timestamp = messageSnapshot.child("timestamp").getValue(String.class);
        String id = messageSnapshot.child("id").getValue(String.class);

        // Create a Message object with extracted values
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(timestamp); // Assuming timestamp is stored as a String
        message.setId(id);

        return message;
    }

    private void setSender(String sender) {
        this.sender = sender;
    }
    private void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    private void setContent(String content) {
        this.content = content;
    }
    private void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
