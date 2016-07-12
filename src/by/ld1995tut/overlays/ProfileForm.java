package by.ld1995tut.overlays;

import by.ld1995tut.mics.TextEntry;
import components.GuiHelper;
import org.javagram.dao.Me;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    public ProfileForm() {
        $$$setupUI$$$();
        close.setText("выход");
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
                g.drawLine(0, 29, 70, 29);
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = Color.black;
        g.setColor(GuiHelper.makeTranspatent(color, 0.9f));
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

    private void saveChanges() {
        if (telegramProxy.getMe().getLastName().equals(last.getText()) && telegramProxy.getMe().getFirstName().equals(fast.getText())) {
            return;
        } else {
//            telegramProxy.
        }

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
        rootPanel.setMinimumSize(new Dimension(800, 600));
        rootPanel.setOpaque(false);
        rootPanel.setPreferredSize(new Dimension(800, 600));
        logout = new JButton();
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setIcon(new ImageIcon(getClass().getResource("/images/icon-back.png")));
        logout.setMaximumSize(new Dimension(35, 35));
        logout.setMinimumSize(new Dimension(35, 35));
        logout.setPreferredSize(new Dimension(35, 35));
        logout.setRequestFocusEnabled(false);
        logout.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(logout, gbc);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setFont(new Font(close.getFont().getName(), close.getFont().getStyle(), 12));
        close.setForeground(new Color(-14436636));
        close.setMinimumSize(new Dimension(36, 36));
        close.setOpaque(false);
        close.setPreferredSize(new Dimension(70, 35));
        close.setRequestFocusEnabled(false);
        close.setText("выйти");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        rootPanel.add(close, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setPreferredSize(new Dimension(20, 20));
        label2.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setName("");
        label3.setPreferredSize(new Dimension(20, 20));
        label3.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label4, gbc);
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
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        rootPanel.add(save, gbc);
        final JLabel label5 = new JLabel();
        label5.setName("");
        label5.setPreferredSize(new Dimension(0, 0));
        label5.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label5, gbc);
        text = new JTextPane();
        text.setBackground(new Color(-16731159));
        text.setEditable(false);
        text.setEnabled(true);
        text.setFont(new Font("Open Sans", text.getFont().getStyle(), 40));
        text.setForeground(new Color(-16731159));
        text.setOpaque(false);
        text.setText("Настройки профиля");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rootPanel.add(text, gbc);
        number = new JLabel();
        number.setEnabled(false);
        number.setFont(new Font("Open Sans", number.getFont().getStyle(), 14));
        number.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(number, gbc);
        lastPanel = new JPanel();
        lastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        lastPanel.setAlignmentX(0.5f);
        lastPanel.setOpaque(false);
        lastPanel.setPreferredSize(new Dimension(250, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        rootPanel.add(lastPanel, gbc);
        last = new JTextField();
        last.setCaretColor(new Color(-1));
        last.setDisabledTextColor(new Color(-1));
        last.setFont(new Font("Open Sans Light", last.getFont().getStyle(), 28));
        last.setForeground(new Color(-1));
        last.setHorizontalAlignment(2);
        last.setMinimumSize(new Dimension(250, 35));
        last.setOpaque(false);
        last.setPreferredSize(new Dimension(250, 35));
        last.setSelectedTextColor(new Color(-1));
        last.setSelectionColor(new Color(-14436636));
        last.setToolTipText("");
        lastPanel.add(last);
        final JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(-1115905));
        separator1.setOpaque(true);
        separator1.setOrientation(0);
        separator1.setPreferredSize(new Dimension(320, 2));
        lastPanel.add(separator1);
        fastPanel = new JPanel();
        fastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fastPanel.setAlignmentX(0.5f);
        fastPanel.setOpaque(false);
        fastPanel.setPreferredSize(new Dimension(250, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        rootPanel.add(fastPanel, gbc);
        fast = new JTextField();
        fast.setCaretColor(new Color(-1));
        fast.setDisabledTextColor(new Color(-1));
        fast.setFont(new Font("Open Sans Light", fast.getFont().getStyle(), 28));
        fast.setForeground(new Color(-1));
        fast.setHorizontalAlignment(2);
        fast.setMinimumSize(new Dimension(250, 35));
        fast.setOpaque(false);
        fast.setPreferredSize(new Dimension(250, 35));
        fast.setSelectedTextColor(new Color(-1));
        fast.setSelectionColor(new Color(-14436636));
        fast.setToolTipText("");
        fastPanel.add(fast);
        final JSeparator separator2 = new JSeparator();
        separator2.setForeground(new Color(-1115905));
        separator2.setOpaque(true);
        separator2.setOrientation(0);
        separator2.setPreferredSize(new Dimension(320, 2));
        fastPanel.add(separator2);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
