package Contacts;

import resources.Images;
import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ContactsForm extends JPanel implements ListCellRenderer<Person> {
    private JTextPane lastMessage;
    private JLabel nameLabel;
    private JPanel photoPanel;
    private JPanel rootPanel;
    private JLabel date;

    private TelegramProxy telegramProxy;
    private Person person;
    private boolean hasFocus;
    private boolean selected;
    private BufferedImage mask;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    private final DateFormat dayFormat = new SimpleDateFormat("HH:mm");
    private int indicator = 2;

    public ContactsForm(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        lastMessage.setBorder(BorderFactory.createEmptyBorder());
    }

    private void createUIComponents() {
        rootPanel = this;
        photoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                boolean small = true;
                BufferedImage image;
                try {
                    image = telegramProxy.getPhoto(person, small);
                    mask = Images.getPhotoMaskGray();
                } catch (IOException e) {
                    e.printStackTrace();
                    image = null;
                }
                if (image == null) {
                    image = Images.getDefaultUserPhoto();
                }
                g.drawImage(image, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
                if (telegramProxy.isOnline(person)) {
                    mask = Images.getPhotoMaskOnlineGray();
                }
                if (selected) {
                    mask = Images.getPhotoMaskWhite();
                }
                if (selected && telegramProxy.isOnline(person)) {
                    mask = Images.getPhotoMaskOnlineWhite();
                }
                g.drawImage(mask, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200, 200, 200));
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        if (hasFocus) {
            g.setColor(new Color(35, 182, 228));
            g.fillRect((this.getWidth() - indicator), 0, this.getWidth(), this.getHeight());
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> jList,
                                                  Person person, int index,
                                                  boolean selected, boolean hasFocus) {
        this.person = person;
        this.selected = selected;
        Dialog dialog = telegramProxy.getDialog(person);
        this.nameLabel.setText(person.getFirstName() + " " + person.getLastName());
        if (dialog != null) {
            this.lastMessage.setText(dialog.getLastMessage().getText());
            this.date.setText(dateFormat.format(dialog.getLastMessage().getDate()));
        } else {
            this.lastMessage.setText("");
            this.date.setText("");
        }
        if (selected) {
            setBackground(Color.white);
        } else {
            setBackground(new Color(230, 230, 230));
        }
        this.hasFocus = hasFocus;
        return this;
    }

}
