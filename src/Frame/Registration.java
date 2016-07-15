package Frame;

import components.HintTextField;
import components.TextEntry;
import resources.Fonts;
import resources.Images;
import components.GuiHelper;
import components.ImagePanel;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class Registration extends ImagePanel {
    private JPanel registrationPanel;
    private JTextField lastName;
    private JButton nextReg;
    private JTextField fastName;
    private JTextPane text;
    private JPanel logoPanel;
    private JPanel lastPanel;
    private JPanel fastPanel;

    public Registration() {
        super(Images.getBackground(), true, false, 0);
        GuiHelper.adjustTextPane(text);
        lastName.setBorder(BorderFactory.createEmptyBorder());
        fastName.setBorder(BorderFactory.createEmptyBorder());
        if (lastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) lastName.getDocument()).setDocumentFilter(new TextEntry());
        if (fastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) fastName.getDocument()).setDocumentFilter(new TextEntry());
    }

    public JPanel getRegistrationPanel() {
        return registrationPanel;
    }

    public String getLastName() {
        return lastName.getText();
    }

    public JButton getNextReg() {
        return nextReg;
    }

    public String getFastName() {
        return fastName.getText();
    }

    public void addActionListenerForConfirm(ActionListener actionListener) {
        nextReg.addActionListener(actionListener);
        fastName.addActionListener(actionListener);
        lastName.addActionListener(actionListener);
    }

    public void removeActionListenerForConfirm(ActionListener actionListener) {
        nextReg.removeActionListener(actionListener);
        fastName.removeActionListener(actionListener);
        lastName.removeActionListener(actionListener);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        registrationPanel = this;
        logoPanel = new ImagePanel(Images.getLogoMini(), false, true, 0);
        HintTextField lastHint = new HintTextField("","Имя",false)
        {
            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        lastHint.setHintForeground(new Color(200,200,200));
        lastHint.setHintFont(Fonts.getOpenSansRegular());
        lastName = lastHint;
        HintTextField fastHint = new HintTextField("","Фамилия", false)
        {
            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        fastHint.setHintForeground(new Color(200,200,200));
        fastHint.setHintFont(Fonts.getOpenSansRegular());
        fastName = fastHint;
    }

    public void clean() {
        lastName.setText("");
        fastName.setText("");
    }

}
