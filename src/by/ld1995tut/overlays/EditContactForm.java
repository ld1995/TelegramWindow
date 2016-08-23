package by.ld1995tut.overlays;

import by.ld1995tut.components.HintTextField;
import by.ld1995tut.components.ImagePanel;
import by.ld1995tut.components.OverlayBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditContactForm extends OverlayBackground {
    private JPanel rootPanel;
    private JButton close;
    private JButton saveButton;
    private JTextPane text;
    private JTextField info;
    private JPanel contactPanel;
    private JButton deleteUserButton;
    private JPanel photoPanel;
    private JLabel number;

    private int id;

    {
        setContactInfo(new ContactInfo());
        setPhoto(null);
        this.info.setBorder(BorderFactory.createEmptyBorder());
        ((HintTextField)info).setHintAlignment(JTextField.CENTER);
    }

    public void setContactInfo (ContactInfo contactInfo) {
        info.setText(contactInfo.getFirstName() + " " + contactInfo.getLastName());
        number.setText(contactInfo.getPhone());
        id = contactInfo.getId();
    }

    public ContactInfo getContactInfo() {
        String infoUser = info.getText().trim();
        String fIOUser[] = infoUser.split("\\s+");
        if (fIOUser.length == 2) {
            return new ContactInfo(number.getText().trim(),
                    fIOUser[0].trim(), fIOUser[1].trim(), id);
        } else {
            return null;
        }
    }

    public void setPhoto (Image photo) {
        ((ImagePanel)photoPanel).setImage(photo);
    }

    public Image getPhoto() {
        return ((ImagePanel)photoPanel).getImage();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        photoPanel = new ImagePanel(null,true,false,0);
        contactPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.drawLine(0,(contactPanel.getHeight() / 2), contactPanel.getWidth(),(contactPanel.getHeight() / 2));
            }
        };

        deleteUserButton = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(250,83,83));
                g.drawRect(0,0,(deleteUserButton.getWidth()-1),(deleteUserButton.getHeight()-1));
            }
        };
        info = HintTextField.printHint("Имя и фамилия");
    }

    public void addActionListenerForSave(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
    }

    public void removeActionListenerForSave(ActionListener actionListener) {
        saveButton.removeActionListener(actionListener);
    }

    public void addActionListenerForClose(ActionListener actionListener) {
        close.addActionListener(actionListener);
    }

    public void removeActionListenerForClose(ActionListener actionListener) {
        close.removeActionListener(actionListener);
    }

    public void addActionListenerForDelete(ActionListener actionListener) {
        deleteUserButton.addActionListener(actionListener);
    }

    public void removeActionListenerForDelete(ActionListener actionListener) {
        deleteUserButton.removeActionListener(actionListener);
    }
}
