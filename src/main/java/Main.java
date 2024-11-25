import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FirebaseInit.initializeFirebase();
        WriteToFirebase.editUserProfile("aryan", "age", 60);
        MockDatabase database = new MockDatabase();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;
        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                System.out.println("\nWelcome to Vamos Dating App");
                System.out.println("1. Create Profile");
                System.out.println("2. Log in to Profile");
                System.out.println("3. Exit");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter gender (Male/Female/Other): ");
                        String gender = scanner.nextLine();
                        System.out.print("Enter location: ");
                        String location = scanner.nextLine();
                        System.out.print("Enter interests ");
                        String interests = scanner.nextLine();
                        System.out.print("Enter password ");
                        String password = scanner.nextLine();
                        List<String> interestList = new ArrayList<String>();
                        interestList.add(interests);
                        currentUser = new User(username, age, gender, location, interestList, password);
                        database.addUser(currentUser);
                        loggedIn = true;
                        break;

                    case 2:
                        System.out.print("Enter username to log in: ");
                        String loginUsername = scanner.nextLine();
                        currentUser = database.findUserByUsername(loginUsername);

                        if (currentUser != null) {
                            System.out.println("Logged in successfully!");
                            loggedIn = true;
                        } else {
                            System.out.println("Profile not found. Please create a profile first.");
                        }
                        break;

                    case 3:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } else {
                System.out.println("\nWelcome, " + currentUser.getUsername());
                System.out.println("1. View Mutual Likes");
                System.out.println("2. Like/Dislike Profiles");
                System.out.println("3. Messages");
                System.out.println("4. Log Out");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1: // View Mutual Likes
                        System.out.println("\nYour Mutual Likes:");
                        for (String mutualLike : currentUser.getMutualLikes()) {
                            System.out.println("You matched with " + mutualLike);
                        }
                        currentUser.clearNewMatchesNotification();
                        break;

                    case 2: // Like/Dislike Profiles
                        List<User> allUsers = database.getUsers();
                        System.out.println("\nLike/Dislike Profiles:");
                        for (User user : allUsers) {
                            if (!user.getUsername().equalsIgnoreCase(currentUser.getUsername())) {
                                System.out.print("Do you like " + user.getUsername() + "? (yes/no): ");
                                String response = scanner.nextLine();
                                if (response.equalsIgnoreCase("yes")) {
                                    currentUser.likeUser(user.getUsername());

                                    if (database.isMutualLike(currentUser, user.getUsername())) {
                                        currentUser.addMutualLike(user.getUsername());
                                        user.addMutualLike(currentUser.getUsername());
                                        System.out.println("It's a match with " + user.getUsername() + "!");
                                    }
                                }
                            }
                        }
                        break;

                    case 3: // Messages Menu
                        System.out.println("\nMessages:");
                        System.out.println("1. Send main.java.Message");
                        System.out.println("2. View Messages");
                        System.out.print("Choose an option: ");
                        int messageOption = scanner.nextInt();
                        scanner.nextLine();

                        if (messageOption == 1) { // Send main.java.Message
                            List<String> mutualLikes = new ArrayList<>(currentUser.getMutualLikes());
                            if (mutualLikes.isEmpty()) {
                                System.out.println("No users available to message.");
                            } else {
                                System.out.println("\nSelect a user to message:");
                                for (int i = 0; i < mutualLikes.size(); i++) {
                                    System.out.println((i + 1) + ". " + mutualLikes.get(i));
                                }
                                System.out.print("Choose a number: ");
                                int userChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (userChoice > 0 && userChoice <= mutualLikes.size()) {
                                    String receiver = mutualLikes.get(userChoice - 1);
                                    System.out.print("Enter your message: ");
                                    String content = scanner.nextLine();
                                    currentUser.sendMessage(receiver, content);
                                    System.out.println("main.java.Message sent to " + receiver);
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            }
                        } else if (messageOption == 2) { // View All Received Messages
                            List<Message> receivedMessages = currentUser.getAllReceivedMessages();
                            if (receivedMessages.isEmpty()) {
                                System.out.println("No messages at this time.");
                            } else {
                                System.out.println("\nAll Received Messages:");
                                for (Message message : receivedMessages) {
                                    System.out.println(message);
                                }
                            }
                            currentUser.clearNewMessagesNotification();
                        } else {
                            System.out.println("Invalid option.");
                        }
                        break;

                    case 4: // Log Out
                        loggedIn = false;
                        currentUser = null;
                        System.out.println("Logged out successfully.");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
