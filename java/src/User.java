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
    private String interests;
    private Set<String> likes;
    private Set<String> mutualLikes;
    private Map<String, List<Message>> conversations;
    private boolean hasNewMessages;
    private boolean hasNewMatches;

    public User(String username, int age, String gender, String location, String interests) {
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

    public String getUsername() { return username; }
    public Set<String> getMutualLikes() { return mutualLikes; }

    // New Method to get the list of likes
    public Set<String> getLikes() { return likes; }

    public void likeUser(String username) { likes.add(username); }

    public void addMutualLike(String username) {
        mutualLikes.add(username);
        hasNewMatches = true;
        if (!conversations.containsKey(username)) {
            conversations.put(username, new ArrayList<>());
        }
    }

    public void sendMessage(String receiver, String content) {
        if (conversations.containsKey(receiver)) {
            Message message = new Message(this.username, receiver, content);
            conversations.get(receiver).add(message);
            hasNewMessages = true;
        }
    }

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

    public void clearNewMessagesNotification() { hasNewMessages = false; }
    public void clearNewMatchesNotification() { hasNewMatches = false; }
}
