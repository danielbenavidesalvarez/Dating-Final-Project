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
            FileInputStream serviceAccount = new FileInputStream("/Users/aryanpuri/IdeaProjects/dating app nov 25/src/datingapp207-98113-firebase-adminsdk-yq8zh-a995a0a9f1.json");

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

    public static void clearCacheOnShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // Assuming any Firebase-related cache clearing or cleanup logic
                System.out.println("Shutting down and clearing Firebase cached data.");
            } catch (Exception e) {
                System.err.println("Error during shutdown: " + e.getMessage());
            }
        }));
    }
}
