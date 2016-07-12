package by.ld1995tut.overlays;

import by.ld1995tut.mics.TextEntry;
import by.ld1995tut.resurces.Images;
import components.GuiHelper;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class EditContact extends JPanel {

    private JPanel rootPanel;
    private JButton save;
    private JTextPane text;
    private JPanel contactPanel;
    private JButton logout;
    private JPanel photoPanel;
    private JPanel infoPanel;
    private JTextField infoContact;

    private BufferedImage mask = Images.getMaskDarkGrayBig();
    private BufferedImage buddyPhoto;
    private String buddyText;

    public EditContact() {
        $$$setupUI$$$();
        infoContact.setBorder(BorderFactory.createEmptyBorder());
    }

    private void createUIComponents() {
        rootPanel = this;
        contactPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 255, 255));
                g.drawLine(0, 100, contactPanel.getHeight(), 100);
            }
        };
        photoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (mask == null) {
                    return;
                }
                if (buddyPhoto == null) {
                    return;
                }
                if (buddyText != null) {
                    infoContact.setText(buddyText);
                }
                g.drawImage(buddyPhoto, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
                g.drawImage(mask, 0, 0, photoPanel.getWidth(), photoPanel.getHeight(), null);
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = Color.black;
        g.setColor(GuiHelper.makeTranspatent(color, 0.9f));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
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

    public void addActionListenerForLogout(ActionListener listener) {
        logout.addActionListener(listener);
    }

    public void removeActionListenerForLogout(ActionListener listener) {
        logout.removeActionListener(listener);
    }

    public void addActionListenerForSave(ActionListener listener) {
        save.addActionListener(listener);
    }

    public void removeActionListenerForSave(ActionListener listener) {
        save.removeActionListener(listener);
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
        rootPanel.setInheritsPopupMenu(false);
        rootPanel.setMinimumSize(new Dimension(800, 600));
        rootPanel.setOpaque(false);
        rootPanel.setPreferredSize(new Dimension(800, 600));
        rootPanel.setRequestFocusEnabled(true);
        logout = new JButton();
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setIcon(new ImageIcon(getClass().getResource("/images/icon-back.png")));
        logout.setMaximumSize(new Dimension(40, 40));
        logout.setMinimumSize(new Dimension(40, 40));
        logout.setPreferredSize(new Dimension(40, 40));
        logout.setRequestFocusEnabled(true);
        logout.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(logout, gbc);
        final JLabel label1 = new JLabel();
        label1.setPreferredSize(new Dimension(20, 20));
        label1.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setPreferredSize(new Dimension(20, 20));
        label2.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setMinimumSize(new Dimension(20, 20));
        label3.setPreferredSize(new Dimension(20, 20));
        label3.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setMinimumSize(new Dimension(40, 40));
        label4.setPreferredSize(new Dimension(40, 40));
        label4.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setPreferredSize(new Dimension(20, 20));
        label5.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label5, gbc);
        save = new JButton();
        save.setAlignmentX(0.0f);
        save.setAlignmentY(0.5f);
        save.setBackground(new Color(-16731159));
        save.setBorderPainted(false);
        save.setContentAreaFilled(false);
        save.setDoubleBuffered(false);
        save.setFocusable(false);
        save.setFont(new Font("Open Sans", save.getFont().getStyle(), 20));
        save.setForeground(new Color(-1));
        save.setHorizontalTextPosition(0);
        save.setIcon(new ImageIcon(getClass().getResource("/images/button-background.png")));
        save.setLabel("СОХРАНИТЬ");
        save.setMargin(new Insets(0, 0, 0, 0));
        save.setMaximumSize(new Dimension(150, 32));
        save.setMinimumSize(new Dimension(150, 32));
        save.setOpaque(false);
        save.setPreferredSize(new Dimension(335, 65));
        save.setText("СОХРАНИТЬ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        rootPanel.add(save, gbc);
        text = new JTextPane();
        text.setBackground(new Color(-16731159));
        text.setEditable(false);
        text.setEnabled(true);
        text.setFont(new Font("Open Sans", text.getFont().getStyle(), 40));
        text.setForeground(new Color(-16731159));
        text.setOpaque(false);
        text.setText("Редактирование контакта");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        rootPanel.add(text, gbc);
        contactPanel.setLayout(new GridBagLayout());
        contactPanel.setOpaque(false);
        contactPanel.setPreferredSize(new Dimension(450, 150));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 0.2;
        rootPanel.add(contactPanel, gbc);
        photoPanel.setMinimumSize(new Dimension(66, 66));
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(66, 66));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        contactPanel.add(photoPanel, gbc);
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        infoPanel.setAlignmentX(0.5f);
        infoPanel.setMinimumSize(new Dimension(266, 50));
        infoPanel.setOpaque(false);
        infoPanel.setPreferredSize(new Dimension(350, 50));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        contactPanel.add(infoPanel, gbc);
        infoContact = new JTextField();
        infoContact.setCaretColor(new Color(-1));
        infoContact.setDisabledTextColor(new Color(-1));
        infoContact.setFont(new Font("Open Sans Light", infoContact.getFont().getStyle(), 28));
        infoContact.setForeground(new Color(-1));
        infoContact.setHorizontalAlignment(2);
        infoContact.setMinimumSize(new Dimension(250, 35));
        infoContact.setOpaque(false);
        infoContact.setPreferredSize(new Dimension(350, 35));
        infoContact.setSelectedTextColor(new Color(-1));
        infoContact.setSelectionColor(new Color(-14436636));
        infoContact.setToolTipText("");
        infoPanel.add(infoContact);
        final JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(-1));
        separator1.setMinimumSize(new Dimension(350, 2));
        separator1.setOpaque(true);
        separator1.setOrientation(0);
        separator1.setPreferredSize(new Dimension(350, 2));
        infoPanel.add(separator1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}