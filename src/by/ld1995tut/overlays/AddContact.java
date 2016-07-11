package by.ld1995tut.overlays;

import by.ld1995tut.mics.HintText;
import by.ld1995tut.mics.TextEntry;
import components.GuiHelper;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AddContact extends JPanel {
    private JButton logout;
    private JButton addButton;
    private JTextField fast;
    private JTextField last;
    private JTextPane text;
    private JPanel rootPanel;
    private JFormattedTextField numberField;
    private JTextPane textHelp;

    private TelegramProxy telegramProxy;

    public AddContact() {
        $$$setupUI$$$();
        GuiHelper.adjustTextPane(textHelp);
        if (last.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) last.getDocument()).setDocumentFilter(new TextEntry());
        if (fast.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) fast.getDocument()).setDocumentFilter(new TextEntry());
        HintText lastName = new HintText(last, "Имя", this.last.getCaretColor());
        HintText fastName = new HintText(fast, "Фамилия", this.fast.getCaretColor());
        try {
            MaskFormatter maskFormatter = new MaskFormatter("+### (##) ###-##-##");
            maskFormatter.setPlaceholder(null);
            maskFormatter.setPlaceholderCharacter('.');
            numberField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Color color = Color.black;
        g.setColor(GuiHelper.makeTranspatent(color, 0.7f));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void createUIComponents() {
        rootPanel = this;
    }

    public TelegramProxy getTelegramProxy() {
        return telegramProxy;
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        if (telegramProxy != null) {
            telegramProxy.
        }
    }

    public void addActionListenerForLogout(ActionListener listener) {
        logout.addActionListener(listener);
    }

    public void removeActionListenerForLogout(ActionListener listener) {
        logout.removeActionListener(listener);
    }

    public void addActionListenerForAddButton(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void removeActionListenerForAddButton(ActionListener listener) {
        addButton.removeActionListener(listener);
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        createUIComponents();
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setMinimumSize(new Dimension(800, 600));
        rootPanel.setOpaque(false);
        rootPanel.setPreferredSize(new Dimension(800, 600));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(rootPanel, gbc);
        logout = new JButton();
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setIcon(new ImageIcon(getClass().getResource("/images/icon-back.png")));
        logout.setMaximumSize(new Dimension(35, 35));
        logout.setMinimumSize(new Dimension(35, 35));
        logout.setPreferredSize(new Dimension(35, 35));
        logout.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(logout, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setName("");
        label3.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
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
        addButton = new JButton();
        addButton.setAlignmentX(0.0f);
        addButton.setAlignmentY(0.5f);
        addButton.setBackground(new Color(-16731159));
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setDoubleBuffered(false);
        addButton.setFocusable(false);
        addButton.setFont(new Font("Open Sans", addButton.getFont().getStyle(), 20));
        addButton.setForeground(new Color(-1));
        addButton.setHorizontalTextPosition(0);
        addButton.setIcon(new ImageIcon(getClass().getResource("/images/button-background.png")));
        addButton.setLabel("ДОБАВИТЬ");
        addButton.setMargin(new Insets(0, 0, 0, 0));
        addButton.setMaximumSize(new Dimension(150, 32));
        addButton.setMinimumSize(new Dimension(150, 32));
        addButton.setOpaque(false);
        addButton.setPreferredSize(new Dimension(335, 65));
        addButton.setText("ДОБАВИТЬ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        rootPanel.add(addButton, gbc);
        final JLabel label5 = new JLabel();
        label5.setName("");
        label5.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label5, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setOpaque(false);
        panel2.setPreferredSize(new Dimension(350, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        rootPanel.add(panel2, gbc);
        fast = new JTextField();
        fast.setCaretColor(new Color(-1));
        fast.setDisabledTextColor(new Color(-1));
        fast.setFont(new Font("Open Sans Light", fast.getFont().getStyle(), 28));
        fast.setForeground(new Color(-1));
        fast.setHorizontalAlignment(0);
        fast.setMinimumSize(new Dimension(250, 35));
        fast.setOpaque(false);
        fast.setPreferredSize(new Dimension(350, 35));
        fast.setSelectedTextColor(new Color(-1));
        fast.setSelectionColor(new Color(-14436636));
        fast.setText("");
        fast.setToolTipText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(fast, gbc);
        final JSeparator separator1 = new JSeparator();
        separator1.setBackground(new Color(-1));
        separator1.setMinimumSize(new Dimension(320, 2));
        separator1.setPreferredSize(new Dimension(320, 2));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(separator1, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setMaximumSize(new Dimension(250, 45));
        panel3.setMinimumSize(new Dimension(250, 45));
        panel3.setOpaque(false);
        panel3.setPreferredSize(new Dimension(350, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        rootPanel.add(panel3, gbc);
        last = new JTextField();
        last.setCaretColor(new Color(-1));
        last.setDisabledTextColor(new Color(-1));
        last.setEditable(true);
        last.setFont(new Font("Open Sans Light", last.getFont().getStyle(), 28));
        last.setForeground(new Color(-1));
        last.setHorizontalAlignment(0);
        last.setMinimumSize(new Dimension(250, 35));
        last.setOpaque(false);
        last.setPreferredSize(new Dimension(350, 35));
        last.setSelectedTextColor(new Color(-1));
        last.setSelectionColor(new Color(-14436636));
        last.setText("");
        last.setToolTipText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(last, gbc);
        final JSeparator separator2 = new JSeparator();
        separator2.setBackground(new Color(-1));
        separator2.setMinimumSize(new Dimension(320, 2));
        separator2.setPreferredSize(new Dimension(320, 2));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(separator2, gbc);
        text = new JTextPane();
        text.setBackground(new Color(-16731159));
        text.setEditable(false);
        text.setEnabled(true);
        text.setFont(new Font("Open Sans", text.getFont().getStyle(), 40));
        text.setForeground(new Color(-16731159));
        text.setOpaque(false);
        text.setText("Добавление контакта");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        rootPanel.add(text, gbc);
        final JLabel label6 = new JLabel();
        label6.setMinimumSize(new Dimension(35, 16));
        label6.setPreferredSize(new Dimension(35, 16));
        label6.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(label6, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel4.setMaximumSize(new Dimension(250, 45));
        panel4.setMinimumSize(new Dimension(350, 45));
        panel4.setOpaque(false);
        panel4.setPreferredSize(new Dimension(350, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        rootPanel.add(panel4, gbc);
        final JSeparator separator3 = new JSeparator();
        separator3.setBackground(new Color(-1));
        separator3.setMinimumSize(new Dimension(320, 2));
        separator3.setPreferredSize(new Dimension(320, 2));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(separator3, gbc);
        numberField = new JFormattedTextField();
        numberField.setBackground(new Color(-1));
        numberField.setCaretColor(new Color(-1));
        numberField.setDisabledTextColor(new Color(-1));
        numberField.setFont(new Font("Open Sans Light", numberField.getFont().getStyle(), 36));
        numberField.setForeground(new Color(-1115905));
        numberField.setHorizontalAlignment(2);
        numberField.setMargin(new Insets(0, 0, 0, 0));
        numberField.setMinimumSize(new Dimension(350, 35));
        numberField.setOpaque(false);
        numberField.setPreferredSize(new Dimension(350, 35));
        numberField.setSelectedTextColor(new Color(-1115905));
        numberField.setSelectionColor(new Color(-14436636));
        numberField.setText("");
        numberField.setToolTipText("");
        numberField.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel4.add(numberField, gbc);
    }

}
