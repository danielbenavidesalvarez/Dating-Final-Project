import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadFromFirebase {

    public static void fetchUserProfiles() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String name = userSnapshot.child("username").getValue(String.class);
                    System.out.println("Username: " + name);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error reading data: " + error.getMessage());
            }
        });
    }

    public static void userExistance(String newusername, UserExistenceCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean exists = false;
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String name = userSnapshot.child("username").getValue(String.class);
                    if (newusername.equals(name)) {
                        exists = true;
                        break; // No need to continue checking
                    }
                }
                callback.onResult(exists);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error reading data: " + error.getMessage());
            }
        });

    }
}
