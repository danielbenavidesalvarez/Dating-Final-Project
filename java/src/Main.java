import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MockDatabase database = new MockDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Vamos Dating App");
            System.out.println("1. Create Profile");
            System.out.println("2. View Matches");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter gender (Male/Female/Other): ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter interests (comma-separated): ");
                    String interests = scanner.nextLine();

                    User user = new User(username, age, gender, location, interests);
                    database.addUser(user);
                    System.out.println("Profile created successfully!");
                    break;
                case 2:
                    List<User> users = database.getUsers();
                    System.out.println("\nPotential Matches:");
                    for (User u : users) {
                        System.out.println(u);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
