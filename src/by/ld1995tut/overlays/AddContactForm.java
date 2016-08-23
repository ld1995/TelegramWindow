package by.ld1995tut.overlays;

import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.components.HintTextField;
import by.ld1995tut.components.ImagePanel;
import by.ld1995tut.components.OverlayBackground;
import by.ld1995tut.mics.Reg;
import resources.Images;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AddContactForm extends OverlayBackground{
    private JPanel rootPanel;
    private JButton close;
    private JButton addButton;
    private JTextPane text;
    private JPanel phonLogoPanel;
    private JFormattedTextField numberField;
    private JPanel fastPanel;
    private JTextField fastName;
    private JPanel lastPanel;
    private JTextField lastName;
    private JTextPane textHelp;

    public AddContactForm() {
        setContactInfo(new ContactInfo());
        GuiHelper.adjustTextPane(textHelp);
        this.lastName.setBorder(BorderFactory.createEmptyBorder());
        this.fastName.setBorder(BorderFactory.createEmptyBorder());
        this.numberField.setBorder(BorderFactory.createEmptyBorder());

        if (lastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument)lastName.getDocument()).setDocumentFilter(new Reg());
        if (fastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument)fastName.getDocument()).setDocumentFilter(new Reg());

        ((HintTextField)lastName).setHintAlignment(JTextField.CENTER);
        ((HintTextField)fastName).setHintAlignment(JTextField.CENTER);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("+### ## ###-##-##");
            maskFormatter.setPlaceholder(null);
            maskFormatter.setPlaceholderCharacter(' ');
            numberField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setContactInfo (ContactInfo contactInfo) {
        fastName.setText(contactInfo.getFirstName());
        lastName.setText(contactInfo.getLastName());
        numberField.setText(contactInfo.getPhone());
    }

    public ContactInfo getContactInfo() {
        return new ContactInfo(numberField.getText().trim(),
                fastName.getText().trim(),lastName.getText().trim());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        lastName = HintTextField.printHint("Имя");
        fastName = HintTextField.printHint("Фамилия");
        phonLogoPanel = new ImagePanel(Images.getIconPhone(),false,true,0);
    }

    public void addActionListenerForAdd(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void removeActionListanerForAdd(ActionListener actionListener) {
        addButton.removeActionListener(actionListener);
    }

    public void addActionListenerForClose(ActionListener actionListener) {
        close.addActionListener(actionListener);
    }

    public void removeActionListanerForClose(ActionListener actionListener) {
        close.removeActionListener(actionListener);
    }
}
