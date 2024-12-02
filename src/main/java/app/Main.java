package app;

import data_access.FirebaseInit;
import data_access.FirebaseUserDataAccessObject;
import entity.CommonUser;
import entity.User;

import javax.swing.*;

/**
 * The Main class of our application.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize Firebase before building the application
        FirebaseInit.initializeFirebase();

        // Set up shutdown hook to clear Firebase-related cache
        FirebaseInit.clearCacheOnShutdown();

        FirebaseUserDataAccessObject dao = new FirebaseUserDataAccessObject();

        // Test user creation for Firebase
        User testUser = new CommonUser("testUser", "password123");
        testUser.setUserId("testUserId");
        dao.save(testUser);
        System.out.println("User saved successfully!");

        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addEditProfileView()
                .addEditProfileUseCase()
                .addLikeView()
                .addLikeUseCase()
                .addAnalyticsView()
                .addAnalyticsUseCase()
                .addReportAccountView()
                .addPeopleView()
                .addPeopleUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
