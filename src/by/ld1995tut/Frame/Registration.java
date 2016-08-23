package by.ld1995tut.Frame;

import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.components.HintTextField;
import by.ld1995tut.components.ImagePanel;
import by.ld1995tut.mics.Reg;
import resources.Images;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionListener;

public class Registration extends ImagePanel{
    private JPanel registrationPanel;
    private JTextField lastName;
    private JButton nextReg;
    private JTextField fastName;
    private JPanel fastPanel;
    private JPanel logoPanel;
    private JTextPane infoPanel;
    private JPanel lastPanel;

    public Registration() {
        super(Images.getBackground(),true,false,0);
        this.lastName.setBorder(BorderFactory.createEmptyBorder());
        this.fastName.setBorder(BorderFactory.createEmptyBorder());
        GuiHelper.adjustTextPane(infoPanel);
        if (lastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) lastName.getDocument()).setDocumentFilter(new Reg());
        if (fastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) fastName.getDocument()).setDocumentFilter(new Reg());
    }

    public String getLastName() {
        return lastName.getText();
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
        logoPanel = new ImagePanel(Images.getLogoMini(),false,true,0);
        lastName = HintTextField.printHint("Имя");
        fastName = HintTextField.printHint("Фамилия");
    }



    public void transferFocusToLastTextField() {
        lastName.requestFocusInWindow();
    }

    public void transferFocusToFastTextField() {
        fastName.requestFocusInWindow();
    }

    public void clean()
    {
        lastName.setText("");
        fastName.setText("");
    }
}
