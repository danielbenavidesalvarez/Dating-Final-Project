import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class MessageView {
    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;
    WriteToFirebase a = new WriteToFirebase();
    String username1;
    String username2;

    public MessageView(String title, String username1, String username2) {
        frame = new JFrame(title + "" + "between" + username1 + "and " + username2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = inputField.getText();
                if (!content.isEmpty()) {
                    a.sendMessage(username1, username2, content);
                    inputField.setText("");
                }
            }
        });

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    public void updateMessages(List<Message> messages) {
        StringBuilder messagesBuilder = new StringBuilder();
        for (Message message : messages) {
            messagesBuilder.append(message.getSender()).append(": ").append(message.getContent()).append("\n");
        }
        messageArea.setText(messagesBuilder.toString());
    }

//    public void updateMessages(List<Message> messages) {
//        StringBuilder messagesBuilder = new StringBuilder();
//        for (Message message : messages) {
//            messagesBuilder.append(message.getContent()).append("\n");
//        }
//        messageArea.setText(messagesBuilder.toString());
//    }

    public static void main(String[] args) {
        String username1 = "aryan";
        String username2 = "me";
        MessageView a = new MessageView("messages", username1, username2);
        MessageView b = new MessageView("messages", username2, username1);
        FirebaseInit.initializeFirebase();
        UserConversationsRetriever.printMessagesBetweenUsers("aryan", "me", new MessageCallback() {

            @Override
            public void update(List<Message> messages) {
                a.updateMessages(messages);
            }
        });
        UserConversationsRetriever.printMessagesBetweenUsers("aryan", "me", new MessageCallback() {

            @Override
            public void update(List<Message> messages) {
                b.updateMessages(messages);
            }
        });
    }
}

interface MessageCallback {
    void update(List<Message> messages);
}
