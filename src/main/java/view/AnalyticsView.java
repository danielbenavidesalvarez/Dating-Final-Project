package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.analytics.AnalyticsController;
import interface_adapter.analytics.AnalyticsState;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class AnalyticsView extends JPanel {
    private final AnalyticsController controller;
    private final AnalyticsState state;
    private ViewManagerModel viewManagerModel; // For navigation

    // Chart dataset and panel
    private DefaultCategoryDataset dataset;
    private ChartPanel chartPanel;

    public AnalyticsView(AnalyticsController controller, AnalyticsState state) {
        this.controller = controller;
        this.state = state;

        // Layout and components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Analytics Dashboard");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Create the chart and add it to the view
        JFreeChart chart = createChart();
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.add(chartPanel);

        // Fetch analytics when the view is initialized
        String userId = "dummy_user_id"; // Replace with actual user ID logic
        controller.executeAnalytics(userId);

        // Display the analytics data in text form (optional)
        JLabel likesGivenLabel = new JLabel("Likes Given: " + state.getLikesGiven());
        JLabel likesReceivedLabel = new JLabel("Likes Received: " + state.getLikesReceived());
        JLabel matchesLabel = new JLabel("Matches: " + state.getMatches());
        JLabel sharedInterestsLabel = new JLabel("Shared Interests: " + state.getSharedInterests());

        this.add(likesGivenLabel);
        this.add(likesReceivedLabel);
        this.add(matchesLabel);
        this.add(sharedInterestsLabel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> navigateToLoggedInView());
        this.add(backButton);

        // Update the chart with fetched data
        updateChart();
    }

    private JFreeChart createChart() {
        // Initialize the dataset
        dataset = new DefaultCategoryDataset();
        dataset.addValue(0, "Values", "Likes Given");
        dataset.addValue(0, "Values", "Likes Received");
        dataset.addValue(0, "Values", "Matches");
        dataset.addValue(0, "Values", "Shared Interests");

        // Create the chart
        return ChartFactory.createBarChart(
                "Analytics Summary", // Chart title
                "Category",          // X-axis label
                "Count",             // Y-axis label
                dataset              // Dataset
        );
    }

    private void updateChart() {
        // Fetch data from the state and update the dataset
        dataset.setValue(state.getLikesGiven(), "Values", "Likes Given");
        dataset.setValue(state.getLikesReceived(), "Values", "Likes Received");
        dataset.setValue(state.getMatches(), "Values", "Matches");
        dataset.setValue(state.getSharedInterests(), "Values", "Shared Interests");
    }

    private void navigateToLoggedInView() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        } else {
            System.err.println("ViewManagerModel is not set! Cannot navigate to LoggedInView.");
        }
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        System.out.println("ViewManagerModel injected: " + (viewManagerModel != null));
        this.viewManagerModel = viewManagerModel;
    }
}
