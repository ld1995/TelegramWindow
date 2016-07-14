package Frame;

import mics.Pass;
import resources.Images;
import components.GuiHelper;
import components.ImagePanel;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameSms extends ImagePanel {
    private JPasswordField passwordField;
    private JButton smsButton;
    private JLabel number;
    private JPanel smsPanel;
    private JTextPane text;
    private JPanel logoPanel;
    private JPanel codePanel;
    private JPanel lockPanel;

    public FrameSms() {
        super(Images.getBackground(), true, false, 0);
        GuiHelper.adjustTextPane(text);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        this.lockPanel.setBorder(BorderFactory.createEmptyBorder());
        this.codePanel.setBorder(BorderFactory.createEmptyBorder());
        if (passwordField.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) passwordField.getDocument()).setDocumentFilter(new Pass(5));
    }

    public JPanel getSmsPanel() {
        return smsPanel;
    }

    public String getPasswordField() {
        return new String(passwordField.getPassword());
    }

    public void setNamber(String number) {
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
        logoPanel = new ImagePanel(Images.getLogoMini(), false, true, 0);
        lockPanel = new ImagePanel(Images.getLock(), false, true, 0);
    }

    public void clear() {
        passwordField.setText("");
        number.setText("");
    }

}
