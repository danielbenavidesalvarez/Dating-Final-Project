import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;

    // Updated Constructor
    public Message(String sender, String receiver, String content, String timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getReceiver() { return receiver; }
    public String getSender() { return sender; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[" + timestamp.format(formatter) + "] " + sender + ": " + content;
    }
}
