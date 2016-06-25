package by.ld1995tut.Frame;

import by.ld1995tut.Person;
import by.ld1995tut.mics.Reg;

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

    private BufferedImage mainImage;
    private BufferedImage logo;

    public Registration() {
        $$$setupUI$$$();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
        text.setParagraphAttributes(attributeSet, false);
        text.setHighlighter(null);
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
        fastName = new JTextField();
        fastName.setForeground(new Color(-1115905));
        fastName.setHorizontalAlignment(2);
        fastName.setMinimumSize(new Dimension(150, 24));
        fastName.setOpaque(false);
        fastName.setPreferredSize(new Dimension(150, 24));
        fastName.setText("Фамилия");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        registrationPanel.add(fastName, gbc);
        nextReg = new JButton();
        nextReg.setHorizontalTextPosition(0);
        nextReg.setInheritsPopupMenu(false);
        nextReg.setMaximumSize(new Dimension(100, 32));
        nextReg.setMinimumSize(new Dimension(150, 32));
        nextReg.setOpaque(false);
        nextReg.setPreferredSize(new Dimension(150, 32));
        nextReg.setText("Завершить");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        registrationPanel.add(nextReg, gbc);
        text = new JTextPane();
        text.setForeground(new Color(-1115905));
        text.setOpaque(false);
        text.setText("Введите ваше имя и фамилию и установите\nфотографию для завершения регистрации");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        registrationPanel.add(text, gbc);
        logoPanel.setMinimumSize(new Dimension(124, 98));
        logoPanel.setOpaque(false);
        logoPanel.setPreferredSize(new Dimension(124, 98));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        registrationPanel.add(logoPanel, gbc);
        lastName = new JTextField();
        lastName.setForeground(new Color(-1115905));
        lastName.setHorizontalAlignment(2);
        lastName.setMinimumSize(new Dimension(150, 24));
        lastName.setOpaque(false);
        lastName.setPreferredSize(new Dimension(150, 24));
        lastName.setText("");
        lastName.setToolTipText("Имя");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        registrationPanel.add(lastName, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return registrationPanel;
    }
}
