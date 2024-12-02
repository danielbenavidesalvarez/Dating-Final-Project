package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.premessage.PreMessageViewModel;
import interface_adapter.premessage.PreMessageController;
import interface_adapter.premessage.PreMessageState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PreMessageView extends JPanel implements ActionListener, PropertyChangeListener {
    private ViewManagerModel viewManagerModel;

    private final String viewName = "Message Info";
    private final PreMessageViewModel preMessageViewModel;

    private final JTextField username1InputField = new JTextField(15);
    private final JLabel username1ErrorField = new JLabel();

    private final JTextField username2InputField = new JTextField(15);
    private final JLabel username2ErrorField = new JLabel();

    private final JButton messageButton;
    private PreMessageController preMessageController;

    public PreMessageView(PreMessageViewModel preMessageViewModel) {
        this.preMessageViewModel = preMessageViewModel;
        this.preMessageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Pre-Message Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel username1Info = new LabelTextPanel(
                new JLabel("Username 1"), username1InputField);
        final LabelTextPanel username2Info = new LabelTextPanel(
                new JLabel("Username 2"), username2InputField);

        final JPanel buttons = new JPanel();
        messageButton = new JButton("Start Messaging");
        buttons.add(messageButton);

        JButton cancel = new JButton("Cancel");
        buttons.add(cancel);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == messageButton) {
                    handleBackAction();
                }
            }
        });


        messageButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(messageButton)) {
                            final PreMessageState currentState = preMessageViewModel.getState();

                            preMessageController.execute(
                                    currentState.getUsername1(),
                                    currentState.getUsername2()
                            );
                        }
                    }
                }
        );

        username1InputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final PreMessageState currentState = preMessageViewModel.getState();
                currentState.setUsername1(username1InputField.getText());
                preMessageViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        username2InputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final PreMessageState currentState = preMessageViewModel.getState();
                currentState.setUsername2(username2InputField.getText());
                preMessageViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username1Info);
        this.add(username1ErrorField);
        this.add(username2Info);
        this.add(username2ErrorField);
        this.add(buttons);
    }

    private void handleBackAction() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in"); // Navigate back to LoggedInView
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PreMessageState state = (PreMessageState) evt.getNewValue();
        setFields(state);
        username1ErrorField.setText(state.getUsername1Error());
        username2ErrorField.setText(state.getUsername2Error());
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    private void setFields(PreMessageState state) {
        username1InputField.setText(state.getUsername1());
        username2InputField.setText(state.getUsername2());
    }

    public String getViewName() {
        return viewName;
    }

    public void setPreMessageController(PreMessageController preMessageController) {
        this.preMessageController = preMessageController;
    }
}
