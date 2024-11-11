import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter your gender (Male/Female/Other): ");
                        String gender = scanner.nextLine();
                        System.out.print("Enter location: ");
                        String location = scanner.nextLine();
                        System.out.print("Enter interests (comma-separated): ");
                        String interests = scanner.nextLine();

                        currentUser = new User(username, age, gender, location, interests);
                        database.addUser(currentUser);
                        loggedIn = true;
                        break;

                    case 2:
                        System.out.print("Enter your username to log in: ");
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
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } else {
                System.out.println("\nWelcome, " + currentUser.getUsername());
                System.out.println("1. View All Matches");
                System.out.println("2. Filter Matches");
                System.out.println("3. Like/Dislike Profiles");
                System.out.println("4. View Mutual Likes");
                System.out.println("5. Log Out");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        List<User> users = database.getUsers();
                        System.out.println("\nAll Matches:");
                        for (User u : users) {
                            if (!u.getUsername().equalsIgnoreCase(currentUser.getUsername())) {
                                System.out.println(u);
                            }
                        }
                        break;

                    case 2:
                        // Filtering Matches
                        System.out.print("Enter minimum age: ");
                        int minAge = scanner.nextInt();
                        System.out.print("Enter maximum age: ");
                        int maxAge = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter location: ");
                        String filterLocation = scanner.nextLine();

                        List<User> filteredUsers = database.filterUsers(minAge, maxAge, filterLocation, currentUser.getGender(), currentUser);
                        System.out.println("\nFiltered Matches:");
                        if (filteredUsers.isEmpty()) {
                            System.out.println("No matches found with the given criteria.");
                        } else {
                            for (User u : filteredUsers) {
                                System.out.println(u);
                            }
                        }
                        break;

                    case 3:
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

                    case 4:
                        System.out.println("\nYour Mutual Likes:");
                        for (String mutualLike : currentUser.getMutualLikes()) {
                            System.out.println("You matched with " + mutualLike);
                        }
                        break;

                    case 5:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        currentUser = null;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
