import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class DatingAppGUI extends JFrame {
    private MockDatabase database;
    private User currentUser;
    private String[] availableGenders = {"Male", "Female", "Other"};
    private String[] availableInterests = {"Technology", "Sports", "Music", "Art", "Travel", "Cooking"};
    private List<Integer> ageList = new ArrayList<>();
    private Object[] ageArray;
    public DatingAppGUI() {
        database = new MockDatabase();
        initialize();
        for (int i = 18; i <= 100; i++){
            ageList.add(i);
        }
        ageArray = ageList.toArray();
    }

    private void initialize() {
        setTitle("Vamos Dating App");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton createProfileButton = new JButton("Create Profile");
        JButton loginButton = new JButton("Log In");
        JButton exitButton = new JButton("Exit");

        createProfileButton.addActionListener(e -> createProfile());
        loginButton.addActionListener(e -> logIn());
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(createProfileButton);
        panel.add(loginButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
    }

    private void createProfile() {
        String username = JOptionPane.showInputDialog(this, "Enter username:");
        int age = 0;
        while (true){
            int ageSelected = (int) JOptionPane.showInputDialog(
                    this,
                    "Enter age",
                    "Select age",
                    JOptionPane.QUESTION_MESSAGE ,
                    null,
                    ageArray,
                    ageList.get(0));

            if (ageSelected != 0){
                age = ageSelected;
            }
            int changeAge = JOptionPane.showConfirmDialog(this,
                    "Change Age",
                    "Age",
                    JOptionPane.YES_NO_OPTION);
            if (changeAge == JOptionPane.NO_OPTION) {
                break;
            }
        }

        String gender;
        while (true) {
            String genderSelected = (String) JOptionPane.showInputDialog(this,
                    "Enter gender:",
                    "Select Gender",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    availableGenders,
                    availableGenders[0]);
            if (genderSelected != null) {
                gender = genderSelected;
                break;
            }
        }
        String location = JOptionPane.showInputDialog(this, "Enter location:");
        // Dropdown-based interest selection

        List<String> interestList = new ArrayList<>();

        while (true) {
            String selectedInterest = (String) JOptionPane.showInputDialog(
                    this,
                    "Select an interest:",
                    "Choose Interest",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    availableInterests,
                    availableInterests[0]
            );

            if (selectedInterest != null && !interestList.contains(selectedInterest)) {
                interestList.add(selectedInterest);
            }

            int addMore = JOptionPane.showConfirmDialog(this, "Add another interest?", "Interests", JOptionPane.YES_NO_OPTION);
            if (addMore == JOptionPane.NO_OPTION) {
                break;
            }
        }


        currentUser = new User(username, age, gender, location, interestList);
        database.addUser(currentUser);
        JOptionPane.showMessageDialog(this, "Profile created successfully!");
    }

    private void logIn() {
        String username = JOptionPane.showInputDialog(this, "Enter your username:");
        currentUser = database.findUserByUsername(username);

        if (currentUser != null) {
            JOptionPane.showMessageDialog(this, "Logged in successfully!");
            showUserMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Profile not found.");
        }
    }

    private void showUserMenu() {
        String[] options = {"View Mutual Likes", "Like/Dislike Profiles", "Messages", "Log Out"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose an option",
                "main.java.java.User Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0 -> viewMutualLikes();
            case 1 -> likeDislikeProfiles();
            case 2 -> messageMenu();
            case 3 -> currentUser = null;
        }
    }

    private void viewMutualLikes() {
        if (currentUser.getMutualLikes().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No mutual likes found.");
        } else {
            StringBuilder message = new StringBuilder("Mutual Likes:\n");
            for (String user : currentUser.getMutualLikes()) {
                message.append(user).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private void likeDislikeProfiles() {
        List<User> users = database.getUsers();
        for (User user : users) {
            if (!user.getUsername().equals(currentUser.getUsername())) {
                int response = JOptionPane.showConfirmDialog(this, "Do you like " + user.getUsername() + "?", "Like/Dislike", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    currentUser.likeUser(user.getUsername());
                    if (database.isMutualLike(currentUser, user.getUsername())) {
                        currentUser.addMutualLike(user.getUsername());
                        user.addMutualLike(currentUser.getUsername());
                        JOptionPane.showMessageDialog(this, "It's a match with " + user.getUsername() + "!");
                    }
                }
            }
        }
    }

    private void messageMenu() {
        String[] options = {"Send main.java.Message", "View Messages"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose an option",
                "Messages",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            sendMessage();
        } else if (choice == 1) {
            viewMessages();
        }
    }

    private void sendMessage() {
        List<String> mutualLikes = new ArrayList<>(currentUser.getMutualLikes());
        if (mutualLikes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No users available to message.");
        } else {
            String receiver = (String) JOptionPane.showInputDialog(this, "Select a user to message:", "Send main.java.Message", JOptionPane.QUESTION_MESSAGE, null, mutualLikes.toArray(), mutualLikes.get(0));
            if (receiver != null) {
                String content = JOptionPane.showInputDialog(this, "Enter your message:");
                currentUser.sendMessage(receiver, content);
                JOptionPane.showMessageDialog(this, "main.java.Message sent to " + receiver);
            }
        }
    }

    private void viewMessages() {
        List<Message> receivedMessages = currentUser.getAllReceivedMessages();
        if (receivedMessages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No messages at this time.");
        } else {
            StringBuilder messageLog = new StringBuilder("Messages:\n");
            for (Message message : receivedMessages) {
                messageLog.append(message).append("\n");
            }
            JOptionPane.showMessageDialog(this, messageLog.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatingAppGUI app = new DatingAppGUI();
            app.setVisible(true);
        });
    }
}

