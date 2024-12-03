package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import data_access.FirebaseInit;
import data_access.UserConversationsRetriever;
import entity.Message;
import entity.MessageCallback;

public class MessageView{
    public final String viewName = "Message view";
    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;
    String username1;
    String username2;
    UserConversationsRetriever retriever = new UserConversationsRetriever();

    public MessageView(String username1, String username2) {
        FirebaseInit.initializeFirebase();
        UserConversationsRetriever.printMessagesBetweenUsers(username1, username2, new MessageCallback() {

            @Override
            public void update(List<Message> messages) {
                updateMessages(messages);
            }
        });
        frame = new JFrame("Messages" + "between " + username1 + " and " + username2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                    retriever.sendMessage(username1, username2, content);
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




    public static void main(String[] args) {
        String username1 = "daniel";
        String username2 = "pedro";
        MessageView a = new MessageView(username1, username2);
        MessageView b = new MessageView(username2, username1);
        FirebaseInit.initializeFirebase();
        UserConversationsRetriever.printMessagesBetweenUsers(username1, username2, new MessageCallback() {

            @Override
            public void update(List<Message> messages) {
                a.updateMessages(messages);
            }
        });
        UserConversationsRetriever.printMessagesBetweenUsers(username2, username1, new MessageCallback() {

            @Override
            public void update(List<Message> messages) {
                b.updateMessages(messages);
            }
        });
    }
}

