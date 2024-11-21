import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class ReadFromFirebase {
    public static void fetchUserProfiles() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String name = userSnapshot.child("name").getValue(String.class);
                    String email = userSnapshot.child("email").getValue(String.class);
                    System.out.println("User: " + name + ", Email: " + email);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error reading data: " + error.getMessage());
            }
        });
    }

}



