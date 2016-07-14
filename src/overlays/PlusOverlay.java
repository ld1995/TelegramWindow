package overlays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by user on 02.07.2016.
 */
public class PlusOverlay extends JPanel {
    private JButton plusButton;
    private JPanel rootPanel;

    private void createUIComponents() {
        rootPanel = this;
    }

    public void addActionListener(ActionListener actionListener) {
        plusButton.addActionListener(actionListener);
    }

    public void remomeAddActionListener(ActionListener actionListener) {
        plusButton.removeActionListener(actionListener);
    }

}
