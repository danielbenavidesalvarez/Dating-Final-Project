package data_access;

import com.google.firebase.database.*;
import entity.CommonUser;
import entity.User;
import use_case.analytics.AnalyticsUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.edit_profile.UserDataAccessInterface;
import use_case.like.LikeUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.people.PeopleUserDataAccessInterface;
import use_case.premessage.PreMessageUserDataAccessInterface;
import use_case.report_account.ReportAccountUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Firebase implementation of the DAO for user data.
 */
public class FirebaseUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        UserDataAccessInterface,
        LikeUserDataAccessInterface,
        AnalyticsUserDataAccessInterface,
        ReportAccountUserDataAccessInterface,
        PeopleUserDataAccessInterface, PreMessageUserDataAccessInterface {

    private final DatabaseReference userRef;

    public FirebaseUserDataAccessObject() {
        this.userRef = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public boolean existsByName(String identifier) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        userRef.child(identifier).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                future.complete(snapshot.exists());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error checking existence: " + error.getMessage());
                future.completeExceptionally(error.toException());
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void save(User user) {
        if (!(user instanceof CommonUser)) {
            System.err.println("Error: Only CommonUser objects can be saved to Firebase.");
            return;
        }

        CommonUser commonUser = (CommonUser) user;

        // Validate and set userId if it's null or empty
        if (commonUser.getUserId() == null || commonUser.getUserId().isEmpty()) {
            String fallbackId = commonUser.getName() + "_" + System.currentTimeMillis();
            commonUser.setUserId(fallbackId);
            System.out.println("Warning: User ID was null or empty. Setting fallback ID: " + fallbackId);
        }

        userRef.child(commonUser.getUserId()).setValue(commonUser, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error saving user: " + databaseError.getMessage());
            }
            else {
                System.out.println("User saved successfully.");
            }
        });
    }




    @Override
    public User get(String username) {
        CompletableFuture<User> future = new CompletableFuture<>();
        userRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    CommonUser user = snapshot.getValue(CommonUser.class);
                    if (user != null) {
                        user.setUserId(snapshot.getKey());
                    }
                    future.complete(user);
                } else {
                    future.complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error fetching user: " + error.getMessage());
                future.completeExceptionally(error.toException());
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void changePassword(User user) {
        save(user);
    }

    @Override
    public void setCurrentUsername(String name) {
        // This can be implemented based on app-specific requirements.
    }

    @Override
    public String getCurrentUsername() {
        // This can be implemented based on app-specific requirements.
        return null;
    }

    @Override
    public User findById(String userId) {
        return get(userId);
    }

    @Override
    public User getUserById(String userId) {
        return get(userId);
    }

    @Override
    public int countMatches(String userId) {
        System.out.println("Firebase: Counting matches for user ID: " + userId);
        // Firebase logic for counting matches can be added here
        return 0;
    }

    @Override
    public int countSharedInterests(String userId) {
        System.out.println("Firebase: Counting shared interests for user ID: " + userId);
        // Firebase logic for counting shared interests can be added here
        return 0;
    }

    @Override
    public int countLikesGiven(String userId) {
        System.out.println("Firebase: Counting likes given for user ID: " + userId);
        // Firebase logic for counting likes given can be added here
        return 0;
    }

    @Override
    public int countLikesReceived(String userId) {
        System.out.println("Firebase: Counting likes received for user ID: " + userId);
        // Firebase logic for counting likes received can be added here
        return 0;
    }

    @Override
    public boolean doesUserExist(String userId) {
        return existsByName(userId);
    }

//    public List<User> getUsers() {return new ArrayList<>(users.values());}

    @Override
    public List<User> getUsers() {
        CompletableFuture<List<User>> future = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    CommonUser user = childSnapshot.getValue(CommonUser.class);
                    if (user != null) {
                        user.setUserId(childSnapshot.getKey());
                        userList.add(user);
                    }
                }
                future.complete(userList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error fetching users: " + error.getMessage());
                future.completeExceptionally(error.toException());
            }
        });

        try {
            return future.get(); // Block and retrieve the result
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return get(username);
    }
}

