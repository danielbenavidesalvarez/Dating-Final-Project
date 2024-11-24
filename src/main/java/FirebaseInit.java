import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;

public class FirebaseInit {
    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("/Users/aryanpuri/IdeaProjects/dating app final new/csc207-dating-app-firebase-adminsdk-dohmq-9d2fed0bf8.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207-dating-app-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase Initialized");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
