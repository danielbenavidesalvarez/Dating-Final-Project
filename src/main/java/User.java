import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private int age;
    private String gender;
    private String location;
    private List<String> interests;
    private Set<String> likes;
    private Set<String> mutualLikes;
    private Map<String, List<Message>> conversations;
    private boolean hasNewMessages;
    private boolean hasNewMatches;

    // Constructor
    public User(String username, int age, String gender, String location, List<String> interests) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.interests = interests;
        this.likes = new HashSet<>();
        this.mutualLikes = new HashSet<>();
        this.conversations = new HashMap<>();
        this.hasNewMessages = false;
        this.hasNewMatches = false;
    }

    // Getters
    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getLocation() { return location; }
    public List<String> getInterests() { return interests; }
    public Set<String> getMutualLikes() { return mutualLikes; }
    public Set<String> getLikes() { return likes; }

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
