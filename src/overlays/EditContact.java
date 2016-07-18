package overlays;

import resources.Images;
import components.GuiHelper;

import javax.swing.*;
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
    private JTextField infoContact;
    private JButton delete;
    private JLabel number;

    private BufferedImage mask = Images.getMaskDarkGrayBig();
    private BufferedImage buddyPhoto;
    private String buddyText;

    public EditContact() {
        infoContact.setBorder(BorderFactory.createEmptyBorder());
    }

    private void createUIComponents() {
        rootPanel = this;
        contactPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 255, 255));
                g.drawLine(0, (contactPanel.getHeight()/2), 450,(contactPanel.getHeight()/2));
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
        delete = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(250, 83, 83));
                g.drawRect(0, 0, (delete.getWidth()-1),(delete.getHeight()-1));
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = Color.black;
        g.setColor(GuiHelper.makeTransparent(color, 0.9f));
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

    public String getNumber() {
        return number.getText();
    }

    public void setNumber(String number) {
        this.number.setText(number);
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

}
