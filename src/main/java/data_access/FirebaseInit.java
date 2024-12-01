package data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInit {
    private static boolean initialized = false; // Ensures Firebase is initialized only once.
    public static void initializeFirebase() {
        if (initialized) return;

        try {
            // Provide the path to your Firebase Admin SDK JSON file
            FileInputStream serviceAccount = new FileInputStream("google-services.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://datingapp207-98113-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;

            System.out.println("Firebase initialized successfully.");

        } catch (IOException e) {
            System.err.println("Failed to initialize Firebase: " + e.getMessage());
        }
    }
}
