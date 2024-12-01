import javax.swing.*;
import java.awt.*;

public class PhotoDisplay {

    public static void displayPhotoInSwing(String photoPath) {
        JFrame frame = new JFrame("User Profile Photo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        try {
            // Load image from the local file path
            ImageIcon imageIcon = new ImageIcon(photoPath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));

            frame.add(label);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Failed to load image: " + e.getMessage());
            frame.add(errorLabel);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Full path to your screenshot
        String photoPath = "src/637bea7dac51e.image.jpg";
        displayPhotoInSwing(photoPath);
    }
}
