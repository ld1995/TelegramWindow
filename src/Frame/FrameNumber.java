package Frame;

import resources.Images;
import components.GuiHelper;
import components.ImagePanel;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FrameNumber extends ImagePanel {
    private JButton numberButton;
    private JPanel rootPanel;
    private JFormattedTextField numberField;
    private JTextPane text;
    private JPanel logoPanel;
    private JPanel numberPanel;
    private JPanel phonePanel;

    public FrameNumber() {
        super(Images.getBackground(), true, false, 0);
        GuiHelper.adjustTextPane(text);
        this.numberField.setBorder(BorderFactory.createEmptyBorder());
        this.logoPanel.setBorder(BorderFactory.createEmptyBorder());
        this.numberPanel.setBorder(BorderFactory.createEmptyBorder());
        try {
            MaskFormatter maskFormatter = new MaskFormatter("+### (##) ###-##-##");
            maskFormatter.setPlaceholder(null);
            maskFormatter.setPlaceholderCharacter(' ');
            numberField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public String getNumberField() {
        try {
            numberField.commitEdit();
            return numberField.getValue().toString();
        } catch (ParseException e) {
            return null;
        }
    }

    public void addActionListenerForConfirm(ActionListener actionListener) {
        numberButton.addActionListener(actionListener);
        numberField.addActionListener(actionListener);
    }

    public void removeActionListenerForConfirm(ActionListener actionListener) {
        numberButton.removeActionListener(actionListener);
        numberField.removeActionListener(actionListener);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Images.getLogo(), 0, 0, null);
            }
        };
        phonePanel = new ImagePanel(Images.getPhone(), false, true, 0);

    }

    public void clear() {
        numberField.setText("");
    }

}
