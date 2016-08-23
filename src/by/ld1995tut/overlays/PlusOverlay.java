package by.ld1995tut.overlays;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PlusOverlay extends JPanel {
    private JPanel rootPanel;
    private JButton plusButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    public void addActionListenerPlus(ActionListener actionListener) {
        plusButton.addActionListener(actionListener);
    }

    public void removeActionListenerPlus(ActionListener actionListener) {
        plusButton.removeActionListener(actionListener);
    }
}
