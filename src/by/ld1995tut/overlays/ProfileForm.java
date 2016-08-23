package by.ld1995tut.overlays;

import by.ld1995tut.components.ImagePanel;
import by.ld1995tut.components.OverlayBackground;
import by.ld1995tut.mics.Reg;
import org.javagram.dao.Me;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfileForm extends OverlayBackground{
    private JPanel rootPanel;
    private JButton close;
    private JButton logoutButton;
    private JButton saveButton;
    private JPanel fastPanel;
    private JTextField fastName;
    private JPanel lastPanel;
    private JTextField lastName;
    private JTextPane text;
    private JLabel number;
    private JPanel photoPanel;
    private JButton EditPhotoButton;
    private JButton deletePhotoButton;

    private TelegramProxy telegramProxy;
    private int liftUp = 7;
    private int edge = 20;

    public ProfileForm() {
        this.lastName.setBorder(BorderFactory.createEmptyBorder());
        this.fastName.setBorder(BorderFactory.createEmptyBorder());
        if (lastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument)lastName.getDocument()).setDocumentFilter(new Reg());
        if (fastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument)fastName.getDocument()).setDocumentFilter(new Reg());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        logoutButton = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0,173,233));
                g.drawLine(edge,(logoutButton.getHeight()-liftUp),(logoutButton.getWidth() - edge),(logoutButton.getHeight()-liftUp));
            }
        };
        photoPanel = new ImagePanel(null,true,false,0);
    }

    public void setMePhoto(Image photo) {
        ((ImagePanel)photoPanel).setImage(photo);
    }

    public TelegramProxy getTelegramProxy() {
        return telegramProxy;
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        if (telegramProxy != null) {
            Me me = telegramProxy.getMe();
            lastName.setText(me.getLastName());
            fastName.setText(me.getFirstName());
            number.setText(me.getPhoneNumber());
        } else {
            lastName.setText("");
            fastName.setText("");
            number.setText("");
        }
        repaint();
    }

    public void addActionListenerForClose(ActionListener actionListener) {
        close.addActionListener(actionListener);
    }

    public void removeActionListenerForClose(ActionListener actionListener) {
        close.removeActionListener(actionListener);
    }

    public void addActionListenerForLogout(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }

    public void removeActionListenerForLogout(ActionListener actionListener) {
        logoutButton.removeActionListener(actionListener);
    }

    public void addActionListenerForSave(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
    }

    public void removeActionListenerForSave(ActionListener actionListener) {
        saveButton.removeActionListener(actionListener);
    }
}