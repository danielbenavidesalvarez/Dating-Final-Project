import java.util.ArrayList;
import java.util.List;

public class MessageThread {
    private String user1;
    private String user2;
    private List<Message> messages;

    // Default constructor (required for Firebase)
    public MessageThread() {
        this.messages = new ArrayList<>();
    }

    // Constructor for a new thread
    public MessageThread (String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<>();
    }

    // Add a message to the thread
    public void addMessage(Message message) {
        this.messages.add(message);
    }


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public String getUser1() {
        return user1;
    }
    public void setUser1(String user1) {
        this.user1 = user1;
    }
    public String getUser2() {
        return user2;
    }
    public void setUser2(String user2) {
        this.user2 = user2;
    }
}
