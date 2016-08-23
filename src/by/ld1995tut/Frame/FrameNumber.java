package by.ld1995tut.Frame;

import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.components.ImagePanel;
import resources.Images;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FrameNumber extends ImagePanel{
    private JButton numberButton;
    private JPanel numberPanel;
    private JFormattedTextField numberField;
    private JPanel logoPanel;
    private JPanel phonLogoPanel;
    private JTextPane infoPanel;

    public FrameNumber() {
        super(Images.getBackground(),true,false,0);
        this.numberField.setBorder(BorderFactory.createEmptyBorder());
        GuiHelper.adjustTextPane(infoPanel);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("+### ## ###-##-##");
            maskFormatter.setPlaceholder(null);
            maskFormatter.setPlaceholderCharacter(' ');
            numberField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JPanel getNumberPanel() {
        return numberPanel;
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
        numberPanel = this;
        logoPanel = new ImagePanel(Images.getLogo(),false, true,0);
        phonLogoPanel = new ImagePanel(Images.getIconPhone(),false,true,0);
    }

    public void transferFocusTo() {
        numberField.requestFocusInWindow();
    }

    public void clear() {
        numberField.setText("");
    }
}