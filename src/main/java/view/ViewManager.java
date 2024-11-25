package view;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;

/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */

public class ViewManager implements PropertyChangeListener {
    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }


    }

    private final CardLayout cardLayout;
    private final JPanel views;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.cardLayout = cardLayout;
        this.views = views;
        this.viewManagerModel.addPropertyChangeListener(this);
    }
}
// HELLO
