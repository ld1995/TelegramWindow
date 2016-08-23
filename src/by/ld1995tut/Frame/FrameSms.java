package by.ld1995tut.Frame;

import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.components.ImagePanel;
import by.ld1995tut.mics.Pass;
import resources.Images;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionListener;

public class FrameSms extends ImagePanel {
    private JPasswordField passwordField;
    private JButton smsButton;
    private JLabel number;
    private JPanel smsPanel;
    private JPanel lockPanel;
    private JTextPane infoPanel;
    private JPanel logoPanel;

    public FrameSms()
    {
        super(Images.getBackground(),true,false,0);
        this.passwordField.setBorder(BorderFactory.createEmptyBorder());
        GuiHelper.adjustTextPane(infoPanel);
        if (passwordField.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) passwordField.getDocument()).setDocumentFilter(new Pass(5));
    }

    public JPanel getSmsPanel() {
        return smsPanel;
    }

    public String getPasswordField()
    {
        return new String(passwordField.getPassword());
    }

    public void setNumber(String number) {
        this.number.setText(number);
    }

    public void addActionListenerForConfirm(ActionListener actionListener) {
        smsButton.addActionListener(actionListener);
        passwordField.addActionListener(actionListener);
    }

    public void removeActionListenerForConfirm(ActionListener actionListener) {
        smsButton.removeActionListener(actionListener);
        passwordField.removeActionListener(actionListener);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        smsPanel = this;
        lockPanel = new ImagePanel(Images.getIconLock(),false,true,0);
        logoPanel = new ImagePanel(Images.getLogoMini(),false,true,0);
    }

    public void transferFocusTo() {
        passwordField.requestFocusInWindow();
    }

    public void clear() {
        passwordField.setText("");
        number.setText("");
    }
}
