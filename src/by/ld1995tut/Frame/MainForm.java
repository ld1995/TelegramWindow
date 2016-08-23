package by.ld1995tut.Frame;

import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.components.HintTextField;
import by.ld1995tut.components.ImageButton;
import by.ld1995tut.components.ImagePanel;
import by.ld1995tut.mics.Reg;
import resources.Fonts;
import resources.Images;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MainForm extends JPanel {
    private JPanel rootPanel;
    private JPanel titlePanel;
    private JPanel listContacts;
    private JPanel messagePanel;
    private JPanel logoPanel;
    private JButton gearButton;
    private JTextField searchTextField;
    private JPanel searchIconPanel;
    private JPanel messageInfoPanel;
    private JButton sendMessageButton;
    private JTextArea messageText;
    private JPanel userInfoPanel;
    private JScrollPane messageTextScrollPanel;
    private JButton buddyEditButton;
    private JPanel buddyPhotoPanel;
    private JLabel messageBuddyInfo;

    private String meText;
    private BufferedImage mePhoto;
    private String buddyText;
    private BufferedImage buddyPhoto;

    public MainForm() {
        this.searchTextField.setBorder(BorderFactory.createEmptyBorder());
        this.messageTextScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        GuiHelper.decorateScrollPane(messageTextScrollPanel);
        listContacts.add(new JPanel());
        messagePanel.add(new JPanel());
        if (searchTextField.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) searchTextField.getDocument()).setDocumentFilter(new Reg());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        logoPanel = new ImagePanel(Images.getLogoMicro(),false,true,0);
        searchIconPanel = new ImagePanel(Images.getIconSearch(),false,true,0);
        HintTextField hintTextFieldSearch = new HintTextField("","Поиск", false);
        hintTextFieldSearch.setHintForeground(new Color(230,230,230));
        hintTextFieldSearch.setHintFont(Fonts.getOpenSansRegular().deriveFont(0,16));
        searchTextField = hintTextFieldSearch;
        userInfoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int leftMostPoint = 0;
                int rightMostPoint = 12;
                if (meText != null) {
                    int inset = 2;
                    Font font = Fonts.getOpenSansLight().deriveFont(0,14);
                    Color color = new Color(230,230,230);
                    String text = meText;
                    leftMostPoint = GuiHelper.drawText(g,text,color,font,0,0,this.getWidth() - rightMostPoint,this.getHeight(),inset, true);
                }

                if (mePhoto != null) {
                    int inset = 0;
                    BufferedImage image = mePhoto;
                    GuiHelper.drawImage(g,GuiHelper.makeCircle(image),0,0,leftMostPoint - rightMostPoint, this.getHeight(),inset,true);
                }
            }
        };
        buddyPhotoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (buddyPhoto == null) {
                    return;
                }
                if (buddyText != null) {
                    messageBuddyInfo.setText(buddyText);
                    messageBuddyInfo.setForeground(new Color(187,187,187));
                    buddyEditButton.setVisible(true);
                }
                g.drawImage(GuiHelper.makeCircle(buddyPhoto),0,0,buddyPhotoPanel.getWidth(),buddyPhotoPanel.getHeight(),null);
            }
        };
        buddyEditButton = new ImageButton(Images.getIconEdit());
    }

    public String getSearchTextField() {
        return searchTextField.getText();
    }

    public String getMessageText() {
        return messageText.getText();
    }

    public void setMessageText(String messageText) {
        this.messageText.setText(messageText);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public Component getListContacts() {
        return this.listContacts.getComponent(0);
    }

    public void setListContacts(Component contactsPanel) {
        this.listContacts.removeAll();
        this.listContacts.add(contactsPanel);
    }

    public Component getMessagePanel() {
        return this.messagePanel.getComponent(0);
    }

    public void setMessagePanel(Component messagesPanel) {
        this.messagePanel.removeAll();
        this.messagePanel.add(messagesPanel);
    }

    public String getMeText() {
        return meText;
    }

    public void setMeText(String meText)
    {
        if (!Objects.equals(this.meText,meText))
        {
            this.meText = meText;
            repaint();
        }
    }

    public BufferedImage getMePhoto() {
        return mePhoto;
    }

    public void setMePhoto(BufferedImage mePhoto)
    {
        this.mePhoto = mePhoto;
        repaint();
    }

    public String getBuddyText() {
        return buddyText;
    }

    public void setBuddyText(String buddyText)
    {
        if (!Objects.equals(this.buddyText,buddyText))
        {
            this.buddyText = buddyText;
            repaint();
        }
    }

    public BufferedImage getBufferedPhoto() {
        return buddyPhoto;
    }

    public void setBuddyPhoto(BufferedImage bufferedPhoto)
    {
        this.buddyPhoto = bufferedPhoto;
        repaint();
    }

    public void addSendMessageListener(ActionListener actionListener)
    {
        this.sendMessageButton.addActionListener(actionListener);
    }

    public void removeSendMessageListener(ActionListener actionListener)
    {
        this.sendMessageButton.removeActionListener(actionListener);
    }

    public void addGearEventListener(ActionListener actionListener)
    {
        this.gearButton.addActionListener(actionListener);
    }

    public void removeGearEventListener(ActionListener actionListener)
    {
        this.gearButton.removeActionListener(actionListener);
    }

    public void addSearchEventListener(ActionListener actionListener)
    {
        this.searchTextField.addActionListener(actionListener);
    }

    public void removeSearchEventListener(ActionListener actionListener)
    {
        this.searchTextField.removeActionListener(actionListener);
    }

    public void addBuddyEditEventListener(ActionListener actionListener)
    {
        this.buddyEditButton.addActionListener(actionListener);
    }

    public void removeBuddyEditEventListener(ActionListener actionListener)
    {
        this.buddyEditButton.removeActionListener(actionListener);
    }

    public boolean isBuddyEditVisible()
    {
        return buddyEditButton.isVisible();
    }

    public void setBuddyEditEnabled(boolean enabled) {
        buddyEditButton.setEnabled(enabled);
    }

    public void clear() {
        messageBuddyInfo.setText("");
    }
}