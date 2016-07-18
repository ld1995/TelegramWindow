package overlays;

import components.GuiHelper;
import components.TextEntry;
import org.javagram.dao.Me;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfileForm extends JPanel {
    private JButton logout;
    private JPanel rootPanel;
    private JTextField fast;
    private JButton save;
    private JTextPane text;
    private JButton close;
    private JLabel number;
    private JPanel lastPanel;
    private JTextField last;
    private JPanel fastPanel;

    private TelegramProxy telegramProxy;
    private int liftUp = 7;
    private int edge = 20;

    public ProfileForm() {
        last.setBorder(BorderFactory.createEmptyBorder());
        fast.setBorder(BorderFactory.createEmptyBorder());
        if (last.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) last.getDocument()).setDocumentFilter(new TextEntry());
        if (fast.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) fast.getDocument()).setDocumentFilter(new TextEntry());
    }

    private void createUIComponents() {
        rootPanel = this;
        close = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(35, 182, 228));
                g.drawLine(edge,(close.getHeight()-liftUp),(close.getWidth()-edge),(close.getHeight()-liftUp));
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = Color.black;
        g.setColor(GuiHelper.makeTransparent(color, 0.9f));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public TelegramProxy getTelegramProxy() {
        return telegramProxy;
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        if (telegramProxy != null) {
            Me me = telegramProxy.getMe();
            last.setText(me.getLastName());
            fast.setText(me.getFirstName());
            number.setText(me.getPhoneNumber());
        } else {
            last.setText("");
            fast.setText("");
            number.setText("");
        }
        repaint();
    }

    public void addActionListenerForLogout(ActionListener listener) {
        logout.addActionListener(listener);
    }

    public void removeActionListenerForLogout(ActionListener listener) {
        logout.removeActionListener(listener);
    }

    public void addActionListenerForClose(ActionListener listener) {
        close.addActionListener(listener);
    }

    public void removeActionListenerForClose(ActionListener listener) {
        close.removeActionListener(listener);
    }

    public void addActionListenerSave(ActionListener listener) {
        save.addActionListener(listener);
    }

    public void removeActionListenerForSave(ActionListener listener) {
        save.removeActionListener(listener);
    }

}
