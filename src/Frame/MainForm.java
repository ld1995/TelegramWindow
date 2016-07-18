package Frame;

import components.GuiHelper;
import components.HintTextField;
import components.ImagePanel;
import components.TextEntry;
import resources.Fonts;
import resources.Images;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MainForm extends JPanel
{
    private JPanel rootPanel;
    private JPanel titlePanel;
    private JPanel listContacts;
    private JPanel messagePanel;
    private JPanel searchPanel;
    private JPanel searchIcon;
    private JTextField searchField;
    private JPanel list;
    private JPanel logoPanel;
    private JButton gearButton;
    private JPanel infoPanel;
    private JLabel userInfo;
    private JPanel userPhotoPanel;
    private JButton buddyEditButton;
    private JPanel message;
    private JPanel contactsPhoto;
    private JPanel messageInfo;
    private JButton sendMessageButton;
    private JTextArea messageText;
    private JLabel contactsInfo;
    private JScrollPane messageTextScrollPanel;

    private BufferedImage mePhoto;
    private String meText;
    private BufferedImage buddyPhoto;
    private String buddyText;


    public MainForm() {
        list.add(new JPanel());
        message.add(new JPanel());
        GuiHelper.decorateScrollPane(messageTextScrollPanel);
        searchField.setBorder(BorderFactory.createEmptyBorder());
        this.messageTextScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        if (searchField.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) searchField.getDocument()).setDocumentFilter(new TextEntry());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        searchIcon = new ImagePanel(Images.getSearch(), false, true, 0);
        logoPanel = new ImagePanel(Images.getLogoMicro(), false, true, 0);
        HintTextField hintTextFieldSearch = new HintTextField("", "Поиск", false)
        {
            @Override
            protected void paintBorder (Graphics g){

            }
        };
        hintTextFieldSearch.setHintForeground(new Color(200,200,200));
        hintTextFieldSearch.setFont(Fonts.getOpenSansRegular().deriveFont(0,16));
        searchField = hintTextFieldSearch;

        userPhotoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage image = null;
                if (mePhoto != null) {
                    image = mePhoto;
                }
                if (mePhoto == null) {
                    return;
                }
                if (meText != null) {
                    userInfo.setText(meText);
                }
                g.drawImage(image, 0, 0, userPhotoPanel.getWidth(), userPhotoPanel.getHeight(), null);
                g.drawImage(Images.getUserPhotoMask(), 0, 0, userPhotoPanel.getWidth(), userPhotoPanel.getHeight(), null);
            }
        };
        contactsPhoto = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (buddyPhoto == null) {
                    return;
                }
                if (buddyText != null) {
                    contactsInfo.setText(buddyText);
                    contactsInfo.setForeground(new Color(187, 187, 187));
                }
                g.drawImage(buddyPhoto, 0, 0, contactsPhoto.getWidth(), contactsPhoto.getHeight(), null);
                g.drawImage(Images.getUserPhotoMaskWhite(), 0, 0, contactsPhoto.getWidth(), contactsPhoto.getHeight(), null);
            }
        };
    }

    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Component getListContacts() {
        return list.getComponent(0);
    }

    public void setList(Component contactsPanel) {
        this.list.removeAll();
        this.list.add(contactsPanel);
    }

    public Component getMessages() {
        return message.getComponent(0);
    }

    public void setMessages(Component messagePanel) {
        this.message.removeAll();
        this.message.add(messagePanel);
    }

    public String getMeText() {
        return meText;
    }

    public void setMeText(String meText) {
        if (!Objects.equals(this.meText, meText))
            this.meText = meText;
        repaint();
    }

    public BufferedImage getMePhoto() {
        return mePhoto;
    }

    public void setMePhoto(BufferedImage mePhoto) {
        if (this.mePhoto != mePhoto) {
            this.mePhoto = mePhoto;
            repaint();
        }
    }

    public BufferedImage getBuddyPhoto() {
        return buddyPhoto;
    }

    public void setBuddyPhoto(BufferedImage buddyPhoto) {
        this.buddyPhoto = buddyPhoto;
        repaint();
    }

    public String getBuddyText() {
        return buddyText;
    }

    public void setBuddyText(String buddyText) {
        this.buddyText = buddyText;
        repaint();
    }

    public String getMessageText() {
        return messageText.getText();
    }

    public void setMessageText(String messageText) {
        this.messageText.setText(messageText);
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public void addSendMessageListener(ActionListener listener) {
        this.sendMessageButton.addActionListener(listener);
    }

    public void addGearEventListener(ActionListener listener) {
        this.gearButton.addActionListener(listener);
    }

    public void addSearchEventListener(ActionListener actionListener) {
        this.searchField.addActionListener(actionListener);
    }

    public void addBuddyEditEventListener(ActionListener actionListener) {
        this.buddyEditButton.addActionListener(actionListener);
    }

    public void removeSendMessageListener(ActionListener actionListener) {
        this.sendMessageButton.removeActionListener(actionListener);
    }

    public void removeGearEventListener(ActionListener actionListener) {
        this.gearButton.removeActionListener(actionListener);
    }

    public void removeBuddyEditEventListener(ActionListener actionListener) {
        this.buddyEditButton.removeActionListener(actionListener);
    }

}
