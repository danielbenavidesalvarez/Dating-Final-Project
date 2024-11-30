import com.google.api.core.ApiFuture;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.Api;

import java.util.ArrayList;
import java.util.List;

public class ReadFromFirebase {

    // Method to retrieve all user objects and print their data
    public static void fetchAndPrintUsers() {
        // Reference to the "users" node in your Firebase database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        // Adding a listener to fetch data
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    System.out.println("Data exists in 'users' node. Retrieving data...");
                    List<User> userList = new ArrayList<>();
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (user != null) {
                            userList.add(user);

                            // Print user details
                            System.out.println("User Retrieved:");
                            System.out.println("Username: " + user.getUsername());
                            System.out.println("Age: " + user.getAge());
                            System.out.println("Gender: " + user.getGender());
                            System.out.println("Location: " + user.getLocation());
                            System.out.println("Password: " + user.getPw());
                            System.out.println("Interests: " + user.getInterests());
                            System.out.println("------------------");
                        } else {
                            System.out.println("Error: Could not retrieve user data.");
                        }
                    }

                    // Print total users retrieved
                    System.out.println("Total Users Retrieved: " + userList.size());
                } else {
                    System.out.println("No data found in 'users' node.");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error reading data: " + error.getMessage());
            }
        });
    }
    public static boolean returner(boolean flag) {
        System.out.println(flag);
        return flag;

    }
    public static String userName(User testUser){
        return testUser.getUsername();

    }

    public static void checkUser(String username, String password, UserExistenceCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        Query checkDatabase = ref.child(username);

        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("Data exists in 'users' node. Retrieving data...");
                    String dbUsername = dataSnapshot.child("username").getValue(String.class);
                    String dbPassword = dataSnapshot.child("pw").getValue(String.class);
                    System.out.println("details: " + dbPassword + "  " + dbUsername);
                    if (dbUsername != null && dbPassword != null && dbPassword.equals(password)) {
                        callback.onResult(true, "authentication successful");
                    }
                    else{
                        callback.onResult(false, "username or password incorrect");
                    }
                }
                else {
                    callback.onResult(false, "user not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error reading data: " + databaseError.getMessage());
                callback.onResult(false, "Error reading data");

            }


        });



    }
    public static void test(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("Data exists in 'users' node. Retrieving data for test...");
                    User user = dataSnapshot.getValue(User.class);
                    System.out.println("User Retrieved:");
                    System.out.println("Username: " + user.getUsername());
                }
                else {
                    System.out.println("No data found in 'users' node.");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error reading data: " + databaseError.getMessage());

            }
        });


    }

    public static void main(String[] args) {
        // Initialize Firebase
        FirebaseInit.initializeFirebase();
//        checkUser("ahana");
        try {
            Thread.sleep(5000); // Adjust time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();}
//        test();
//        try {
//            Thread.sleep(1000000); // Adjust time as needed
//        } catch (InterruptedException e) {
//            e.printStackTrace();}

        // Fetch and print users
}}
