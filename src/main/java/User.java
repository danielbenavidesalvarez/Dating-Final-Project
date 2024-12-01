import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private int age;
    private String gender;
    private String location;
    private List<String> interests;
    private List<String> likes;
    private List<String> mutualLikes;
    private Map<String, List<Message>> conversations;
    private boolean hasNewMessages;
    private boolean hasNewMatches;
    private String pw;

    // Constructor
    public User(){

    }
    public User(int age, String gender, List<String> interests, String location, String password, String username) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.interests = interests;
        this.likes = new ArrayList<>();
        this.mutualLikes = new ArrayList<>();
        this.conversations = new HashMap<>();
        this.hasNewMessages = false;
        this.hasNewMatches = false;
        this.pw = password;
    }

    // Getters
    public String getPw() {
        return pw;
    }
    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getLocation() { return location; }
    public List<String> getInterests() { return interests; }
    public List<String> getMutualLikes() { return mutualLikes; }
    public List<String> getLikes() { return likes; }

    // Add a like
    public void likeUser(String username) { likes.add(username); }

    // Add a mutual like and initialize conversation if not present
    public void addMutualLike(String username) {
        mutualLikes.add(username);
        hasNewMatches = true;
        if (!conversations.containsKey(username)) {
            conversations.put(username, new ArrayList<>());
        }
    }

    // Send a message to another user
    public void sendMessage(String receiver, String content) {
        if (conversations.containsKey(receiver)) {
            Message message = new Message(this.username, receiver, content);
            conversations.get(receiver).add(message);
            hasNewMessages = true;
        } else {
            List<Message> conversation = new ArrayList<>();
            conversation.add(new Message(this.username, receiver, content));
            conversations.put(receiver, conversation);
        }
    }

    // Retrieve all received messages
    public List<Message> getAllReceivedMessages() {
        List<Message> receivedMessages = new ArrayList<>();
        for (List<Message> conversation : conversations.values()) {
            for (Message message : conversation) {
                if (message.getReceiver().equals(this.username)) {
                    receivedMessages.add(message);
                }
            }
        }
        return receivedMessages;
    }

    // Clear notifications
    public void clearNewMessagesNotification() { hasNewMessages = false; }
    public void clearNewMatchesNotification() { hasNewMatches = false; }

    // Check if there are new messages or matches
    public boolean hasNewMessages() { return hasNewMessages; }
    public boolean hasNewMatches() { return hasNewMatches; }
}
