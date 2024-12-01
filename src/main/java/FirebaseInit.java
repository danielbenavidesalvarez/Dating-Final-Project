import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;

public class FirebaseInit {
    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("/Users/aryanpuri/IdeaProjects/dating APP/csc207-dating-app-firebase-adminsdk-dohmq-a8af13b50c.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207-dating-app-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase Initialized");
            FirebaseAuth auth = FirebaseAuth.getInstance();
            System.out.println("FirebaseAuth instance ready for use.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

