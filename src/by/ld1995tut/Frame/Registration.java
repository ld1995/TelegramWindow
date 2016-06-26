package by.ld1995tut.Frame;

import by.ld1995tut.Person;
import by.ld1995tut.mics.Reg;
import by.ld1995tut.resurces.TextAlignment;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Registration extends JPanel {
    private JPanel registrationPanel;
    private JTextField lastName;
    private JButton nextReg;
    private JTextField fastName;
    private JTextPane text;
    private JPanel logoPanel;
    private JPanel lastPanel;
    private JPanel fastPanel;

    private BufferedImage mainImage;
    private BufferedImage logo;

    public Registration() {
        $$$setupUI$$$();
        TextAlignment textAlignment = new TextAlignment(text);
        lastName.setBorder(BorderFactory.createEmptyBorder());
        fastName.setBorder(BorderFactory.createEmptyBorder());
        if (lastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) lastName.getDocument()).setDocumentFilter(new Reg());
        if (fastName.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) fastName.getDocument()).setDocumentFilter(new Reg());
    }

    //=============================================================

    public JPanel getRegistrationPanel() {
        return registrationPanel;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JButton getNextReg() {
        return nextReg;
    }

    public JTextField getFastName() {
        return fastName;
    }

    //=============================================================

    public Person getPerson() {
        Person person = new Person(getFastName().getText().trim(), getLastName().getText().trim());
        return person;
    }

    public void addActionListenerForConfirm(ActionListener actionListener) {
        nextReg.addActionListener(actionListener);
        fastName.addActionListener(actionListener);
        lastName.addActionListener(actionListener);
    }

    public void removeActionListenerForConfirm(ActionListener actionListener) {
        nextReg.removeActionListener(actionListener);
        fastName.removeActionListener(actionListener);
        lastName.removeActionListener(actionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mainImage == null) {
            return;
        }
        g.drawImage(mainImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void setMainImage(BufferedImage mainImage) {
        this.mainImage = mainImage;
        repaint();
    }

    public void setLogoImage(BufferedImage logo) {
        this.logo = logo;
        repaint();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        registrationPanel = this;
        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (logo == null) {
                    return;
                }
                g.drawImage(logo, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
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
        registrationPanel.setLayout(new GridBagLayout());
        registrationPanel.setFocusCycleRoot(false);
        registrationPanel.setMaximumSize(new Dimension(2147483647, 2147483647));
        registrationPanel.setMinimumSize(new Dimension(800, 600));
        registrationPanel.setOpaque(false);
        registrationPanel.setPreferredSize(new Dimension(800, 600));
        nextReg = new JButton();
        nextReg.setAlignmentX(0.0f);
        nextReg.setAlignmentY(0.5f);
        nextReg.setBorderPainted(false);
        nextReg.setDoubleBuffered(false);
        nextReg.setFont(new Font("Open Sans Light", nextReg.getFont().getStyle(), 18));
        nextReg.setForeground(new Color(-1115905));
        nextReg.setHorizontalTextPosition(0);
        nextReg.setIcon(new ImageIcon(getClass().getResource("/images/button-background.png")));
        nextReg.setLabel("ЗАВЕРШИТЬ");
        nextReg.setMargin(new Insets(0, 0, 0, 0));
        nextReg.setMaximumSize(new Dimension(150, 32));
        nextReg.setMinimumSize(new Dimension(150, 32));
        nextReg.setOpaque(false);
        nextReg.setPreferredSize(new Dimension(270, 45));
        nextReg.setText("ЗАВЕРШИТЬ");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 0, 105, 0);
        registrationPanel.add(nextReg, gbc);
        logoPanel.setMinimumSize(new Dimension(124, 98));
        logoPanel.setOpaque(false);
        logoPanel.setPreferredSize(new Dimension(124, 98));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(25, 0, 0, 0);
        registrationPanel.add(logoPanel, gbc);
        text = new JTextPane();
        text.setBackground(new Color(-1));
        text.setCaretColor(new Color(-1));
        text.setEditable(false);
        text.setEnabled(true);
        text.setFont(new Font("Open Sans", text.getFont().getStyle(), 16));
        text.setForeground(new Color(-1));
        text.setOpaque(false);
        text.setText("Введите ваше имя и фамилию \nдля завершения регистрации");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        registrationPanel.add(text, gbc);
        lastPanel = new JPanel();
        lastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        lastPanel.setAlignmentX(0.5f);
        lastPanel.setOpaque(false);
        lastPanel.setPreferredSize(new Dimension(200, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        registrationPanel.add(lastPanel, gbc);
        lastName = new JTextField();
        lastName.setCaretColor(new Color(-1));
        lastName.setDisabledTextColor(new Color(-1));
        lastName.setFont(new Font("Open Sans Light", lastName.getFont().getStyle(), 28));
        lastName.setForeground(new Color(-1));
        lastName.setHorizontalAlignment(2);
        lastName.setMinimumSize(new Dimension(250, 35));
        lastName.setOpaque(false);
        lastName.setPreferredSize(new Dimension(200, 35));
        lastName.setSelectedTextColor(new Color(-1));
        lastName.setSelectionColor(new Color(-14436636));
        lastName.setToolTipText("");
        lastPanel.add(lastName);
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
        fastPanel.setPreferredSize(new Dimension(200, 45));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        registrationPanel.add(fastPanel, gbc);
        fastName = new JTextField();
        fastName.setCaretColor(new Color(-1));
        fastName.setDisabledTextColor(new Color(-1));
        fastName.setFont(new Font("Open Sans Light", fastName.getFont().getStyle(), 28));
        fastName.setForeground(new Color(-1));
        fastName.setHorizontalAlignment(2);
        fastName.setOpaque(false);
        fastName.setPreferredSize(new Dimension(200, 35));
        fastName.setSelectedTextColor(new Color(-1));
        fastName.setSelectionColor(new Color(-14436636));
        fastName.setToolTipText("");
        fastPanel.add(fastName);
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
        return registrationPanel;
    }
}
