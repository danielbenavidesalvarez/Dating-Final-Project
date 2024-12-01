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
    public static void test(UserDataRetreived callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    System.out.println("Data exists in 'users' node. Retrieving data for test...");
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        System.out.println(childSnapshot.getKey());
//                        User testUser = childSnapshot.getValue(User.class);
//                        userList.add(testUser);
//                        System.out.println(testUser.getUsername());
//                        System.out.println(testUser.getAge());
                    }
//                    System.out.println("Data exists in 'users' node. Retrieving data for test...");
//                    User user = dataSnapshot.getValue(User.class);
//                    System.out.println("User Retrieved:");
//                    System.out.println("Username: " + user.getUsername());
                    callback.onDataRetrieved(userList);
                }
                else {
                    System.out.println("No data found in 'users' node.");
                    callback.onDataRetrieved(userList);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error reading data: " + databaseError.getMessage());
                callback.onDataRetrieved(new ArrayList<>());

            }
        });


    }

    public static void testv2(UserDataRetreived callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        try {
                            User user = childSnapshot.getValue(User.class);
                            if (user != null) {
                                userList.add(user);
                            } else {
                                System.err.println("Failed to parse user for key: " + childSnapshot.getKey());
                            }
                        } catch (Exception e) {
                            System.err.println("Error processing user: " + childSnapshot.getKey());
                            e.printStackTrace(); // Log the full error for debugging
                        }
//                        System.out.println(childSnapshot.getKey());
//                        User user = childSnapshot.getValue(User.class);
//                        userList.add(user);
                    }
                    callback.onDataRetrieved(userList);
                }
                else{
                    System.out.println("No data found in 'users' node.");
                    callback.onDataRetrieved(new ArrayList<>());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error reading data: " + databaseError.getMessage());
                callback.onDataRetrieved(new ArrayList<>());

            }
        });
    }

    public static void main(String[] args) {
        // Initialize Firebase
        FirebaseInit.initializeFirebase();
        testv2(new UserDataRetreived() {
            @Override
            public void onDataRetrieved(List<User> users) {
                if (users.isEmpty()) {
                    System.out.println("No data found in 'users' node.");
                } else {
                    System.out.println("Users retrieved successfully:");
                    for (User user : users) {
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Password: " + user.getPw());
                        System.out.println("age" + user.getAge());
                    }
                }
            }
        });
//        testv2(new UserDataRetreived() {
//            @Override
//            public void onDataRetrieved(List<User> users) {
//                if (users.isEmpty()) {
//                    System.out.println("No data found in 'users' node.");
//                }
//                else{
//                    for (User user : users) {
//                        System.out.println(user.getPw());
//                    }
//                }
//            }
//        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

}}
