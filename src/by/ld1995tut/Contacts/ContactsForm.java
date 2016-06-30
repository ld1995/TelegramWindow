package by.ld1995tut.Contacts;

import by.ld1995tut.resurces.Images;
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

    public ContactsForm(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        $$$setupUI$$$();
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
                    mask = Images.getFotoMaskGray();
                } catch (IOException e) {
                    e.printStackTrace();
                    image = null;
                }
                if (image == null) {
                    image = Images.getDefaultUserFoto();
                }
                if (telegramProxy.isOnline(person)) {
                    mask = Images.getFotoMaskOnlineGray();
                }
                if (selected) {
                    mask = Images.getFotoMaskWhite();
                }
                if (selected && telegramProxy.isOnline(person)) {
                    mask = Images.getFotoMaskOnlineWhite();
                }
                BufferedImage combined = new BufferedImage(photoPanel.getWidth(), photoPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics graphics = combined.getGraphics();
                graphics.drawImage(image, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
                graphics.drawImage(mask, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
                g.drawImage(combined, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hasFocus) {
            g.setColor(new Color(35, 182, 228));
            g.fillRect(243, 0, rootPanel.getHeight(), rootPanel.getWidth());
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
            setBackground(Color.WHITE);
        } else {
            setBackground(new Color(230, 230, 230));
        }

        this.hasFocus = hasFocus;
        return this;
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
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setBackground(new Color(-1644826));
        rootPanel.setForeground(new Color(-1644826));
        rootPanel.setMinimumSize(new Dimension(250, 60));
        rootPanel.setOpaque(true);
        rootPanel.setPreferredSize(new Dimension(250, 60));
        photoPanel.setForeground(new Color(-1644826));
        photoPanel.setMinimumSize(new Dimension(41, 41));
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(41, 41));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 15, 0, 0);
        rootPanel.add(photoPanel, gbc);
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Open Sans Semibold", nameLabel.getFont().getStyle(), 14));
        nameLabel.setMinimumSize(new Dimension(150, 24));
        nameLabel.setOpaque(false);
        nameLabel.setPreferredSize(new Dimension(150, 24));
        nameLabel.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 0, 0);
        rootPanel.add(nameLabel, gbc);
        date = new JLabel();
        date.setFont(new Font("Open Sans Light", date.getFont().getStyle(), 8));
        date.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 5);
        rootPanel.add(date, gbc);
        lastMessage = new JTextPane();
        lastMessage.setMinimumSize(new Dimension(150, 25));
        lastMessage.setOpaque(false);
        lastMessage.setPreferredSize(new Dimension(150, 18));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 0, 0);
        rootPanel.add(lastMessage, gbc);
        final JSeparator separator1 = new JSeparator();
        separator1.setBackground(new Color(-3618616));
        separator1.setPreferredSize(new Dimension(0, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(separator1, gbc);
        final JSeparator separator2 = new JSeparator();
        separator2.setBackground(new Color(-3618616));
        separator2.setPreferredSize(new Dimension(0, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(separator2, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
