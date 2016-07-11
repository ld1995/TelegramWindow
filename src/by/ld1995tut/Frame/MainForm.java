package by.ld1995tut.Frame;

import by.ld1995tut.mics.TextEntry;
import by.ld1995tut.mics.HintText;
import by.ld1995tut.resurces.Images;
import components.GuiHelper;
import components.ImagePanel;

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

    private BufferedImage userPhotoMask = Images.getUserPhotoMask();
    private BufferedImage contactsPhotoMask = Images.getUserPhotoMaskWhite();

    private BufferedImage mePhoto;
    private String meText;
    private BufferedImage buddyPhoto;
    private String buddyText;

    public MainForm() {
        $$$setupUI$$$();
        list.add(new JPanel());
        message.add(new JPanel());
        GuiHelper.decorateScrollPane(messageTextScrollPanel);
        searchField.setBorder(BorderFactory.createEmptyBorder());
        if (searchField.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) searchField.getDocument()).setDocumentFilter(new TextEntry());
        HintText searchHint = new HintText(searchField, "Поиск", searchField.getCaretColor());
        HintText textHint = new HintText(messageText, "Введите сообщение", messageText.getCaretColor());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
        searchIcon = new ImagePanel(Images.getSearch(), false, true, 0);
        logoPanel = new ImagePanel(Images.getLogoMicro(), false, true, 0);
        userPhotoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage image = null;
                if (userPhotoMask != null) {
                    image = mePhoto;
                }
                if (mePhoto == null) {
                    return;
                }
                if (meText != null) {
                    userInfo.setText(meText);
                }
                g.drawImage(image, 0, 0, userPhotoPanel.getWidth(), userPhotoPanel.getHeight(), null);
                g.drawImage(userPhotoMask, 0, 0, userPhotoPanel.getWidth(), userPhotoPanel.getHeight(), null);
            }
        };
        contactsPhoto = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (contactsPhotoMask == null) {
                    return;
                }
                if (buddyPhoto == null) {
                    return;
                }
                if (buddyText != null) {
                    contactsInfo.setText(buddyText);
                }
                g.drawImage(buddyPhoto, 0, 0, contactsPhoto.getWidth(), contactsPhoto.getHeight(), null);
                g.drawImage(contactsPhotoMask, 0, 0, contactsPhoto.getWidth(), contactsPhoto.getHeight(), null);
            }
        };
//        messageInfo = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                int leftMostPoint = buddyEditButton.getX();
//                int rightMostPoint = 2;
//
//                if (buddyPhoto != null) {
//                    int inset = 2;
//                    BufferedImage image = buddyPhoto;
//                    rightMostPoint = GuiHelper.drowImage(g, image, rightMostPoint, 0, leftMostPoint - rightMostPoint, this.getHeight(), inset, false);
//                }
//
//                if (buddyText != null) {
//                    int inset = 10;
//                    Font font = Fonts.getNameFont().deriveFont(Font.ITALIC, 12);
//                    Color color = Color.cyan;
//                    String text = buddyText;
//
//                    rightMostPoint = GuiHelper.drowText(g, text, color, font, rightMostPoint, 0, leftMostPoint - rightMostPoint, this.getHeight(), inset, false);
//                }
//            }
//        };
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
//        if (!Objects.equals(this.buddyText, buddyText))
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.setMinimumSize(new Dimension(800, 600));
        rootPanel.setPreferredSize(new Dimension(800, 600));
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBackground(new Color(-14436636));
        titlePanel.setPreferredSize(new Dimension(600, 45));
        rootPanel.add(titlePanel, BorderLayout.NORTH);
        logoPanel.setFocusable(false);
        logoPanel.setOpaque(false);
        logoPanel.setPreferredSize(new Dimension(102, 24));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 0);
        titlePanel.add(logoPanel, gbc);
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setPreferredSize(new Dimension(180, 31));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.EAST;
        titlePanel.add(infoPanel, gbc);
        userPhotoPanel.setBackground(new Color(-14436636));
        userPhotoPanel.setFocusable(false);
        userPhotoPanel.setPreferredSize(new Dimension(29, 29));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        infoPanel.add(userPhotoPanel, gbc);
        userInfo = new JLabel();
        userInfo.setFocusable(false);
        userInfo.setFont(new Font("Open Sans Light", userInfo.getFont().getStyle(), 14));
        userInfo.setForeground(new Color(-3223858));
        userInfo.setMinimumSize(new Dimension(100, 24));
        userInfo.setOpaque(false);
        userInfo.setPreferredSize(new Dimension(100, 24));
        userInfo.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        infoPanel.add(userInfo, gbc);
        gearButton = new JButton();
        gearButton.setBorderPainted(false);
        gearButton.setContentAreaFilled(false);
        gearButton.setFocusPainted(true);
        gearButton.setFocusable(false);
        gearButton.setHorizontalTextPosition(0);
        gearButton.setIcon(new ImageIcon(getClass().getResource("/images/icon-settings.png")));
        gearButton.setLabel("");
        gearButton.setMinimumSize(new Dimension(22, 22));
        gearButton.setOpaque(false);
        gearButton.setPreferredSize(new Dimension(23, 23));
        gearButton.setRequestFocusEnabled(false);
        gearButton.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        infoPanel.add(gearButton, gbc);
        listContacts = new JPanel();
        listContacts.setLayout(new BorderLayout(0, 0));
        listContacts.setBackground(new Color(-1644826));
        listContacts.setDoubleBuffered(true);
        listContacts.setForeground(new Color(-1644826));
        listContacts.setMinimumSize(new Dimension(250, 50));
        listContacts.setOpaque(true);
        listContacts.setPreferredSize(new Dimension(275, 500));
        rootPanel.add(listContacts, BorderLayout.WEST);
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.setBackground(new Color(-1));
        searchPanel.setForeground(new Color(-1644826));
        searchPanel.setOpaque(true);
        searchPanel.setPreferredSize(new Dimension(205, 45));
        listContacts.add(searchPanel, BorderLayout.NORTH);
        searchIcon.setFocusable(false);
        searchIcon.setMinimumSize(new Dimension(20, 20));
        searchIcon.setOpaque(false);
        searchIcon.setPreferredSize(new Dimension(20, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.insets = new Insets(0, 10, 0, 0);
        searchPanel.add(searchIcon, gbc);
        searchField = new JTextField();
        searchField.setFocusable(true);
        searchField.setFont(new Font("Open Sans", searchField.getFont().getStyle(), 16));
        searchField.setForeground(new Color(-3223858));
        searchField.setOpaque(false);
        searchField.setPreferredSize(new Dimension(200, 24));
        searchField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchField, gbc);
        list = new JPanel();
        list.setLayout(new BorderLayout(0, 0));
        list.setBackground(new Color(-1644826));
        list.setForeground(new Color(-1644826));
        list.setOpaque(false);
        list.setPreferredSize(new Dimension(275, 50));
        listContacts.add(list, BorderLayout.EAST);
        messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout(0, 0));
        messagePanel.setBackground(new Color(-1));
        messagePanel.setFocusable(false);
        messagePanel.setPreferredSize(new Dimension(29, 29));
        rootPanel.add(messagePanel, BorderLayout.CENTER);
        messageInfo = new JPanel();
        messageInfo.setLayout(new GridBagLayout());
        messageInfo.setMinimumSize(new Dimension(238, 45));
        messageInfo.setOpaque(false);
        messageInfo.setPreferredSize(new Dimension(600, 45));
        messagePanel.add(messageInfo, BorderLayout.NORTH);
        buddyEditButton = new JButton();
        buddyEditButton.setBorderPainted(false);
        buddyEditButton.setContentAreaFilled(false);
        buddyEditButton.setFocusable(false);
        buddyEditButton.setIcon(new ImageIcon(getClass().getResource("/images/icon-edit.png")));
        buddyEditButton.setLabel("");
        buddyEditButton.setOpaque(false);
        buddyEditButton.setPreferredSize(new Dimension(22, 22));
        buddyEditButton.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        messageInfo.add(buddyEditButton, gbc);
        final JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(-1644826));
        separator1.setPreferredSize(new Dimension(0, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        messageInfo.add(separator1, gbc);
        final JSeparator separator2 = new JSeparator();
        separator2.setBackground(new Color(-1644826));
        separator2.setForeground(new Color(-1644826));
        separator2.setOpaque(false);
        separator2.setOrientation(1);
        separator2.setPreferredSize(new Dimension(1, 44));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        messageInfo.add(separator2, gbc);
        contactsPhoto.setMinimumSize(new Dimension(29, 29));
        contactsPhoto.setPreferredSize(new Dimension(29, 29));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 20, 0, 0);
        messageInfo.add(contactsPhoto, gbc);
        contactsInfo = new JLabel();
        contactsInfo.setFont(new Font("Open Sans", contactsInfo.getFont().getStyle(), 14));
        contactsInfo.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        messageInfo.add(contactsInfo, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setBackground(new Color(-1644826));
        panel1.setForeground(new Color(-1644826));
        panel1.setOpaque(false);
        messagePanel.add(panel1, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBackground(new Color(-1));
        panel2.setPreferredSize(new Dimension(550, 80));
        panel1.add(panel2, BorderLayout.SOUTH);
        sendMessageButton = new JButton();
        sendMessageButton.setBorderPainted(false);
        sendMessageButton.setContentAreaFilled(false);
        sendMessageButton.setIcon(new ImageIcon(getClass().getResource("/images/button-send.png")));
        sendMessageButton.setOpaque(false);
        sendMessageButton.setPreferredSize(new Dimension(62, 45));
        sendMessageButton.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(sendMessageButton, gbc);
        messageTextScrollPanel = new JScrollPane();
        messageTextScrollPanel.setForeground(new Color(-329473));
        messageTextScrollPanel.setMinimumSize(new Dimension(420, 45));
        messageTextScrollPanel.setOpaque(false);
        messageTextScrollPanel.setPreferredSize(new Dimension(500, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(messageTextScrollPanel, gbc);
        messageText = new JTextArea();
        messageText.setBackground(new Color(-1644826));
        messageText.setFont(new Font("Open Sans", messageText.getFont().getStyle(), 16));
        messageText.setPreferredSize(new Dimension(450, 45));
        messageText.setSelectedTextColor(new Color(-1));
        messageText.setSelectionColor(new Color(-16731159));
        messageText.setText("");
        messageTextScrollPanel.setViewportView(messageText);
        message = new JPanel();
        message.setLayout(new BorderLayout(0, 0));
        message.setBackground(new Color(-1));
        message.setPreferredSize(new Dimension(550, 430));
        panel1.add(message, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
