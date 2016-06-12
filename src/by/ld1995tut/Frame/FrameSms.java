package by.ld1995tut.Frame;

import by.ld1995tut.Person;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameSms {
    private JPasswordField passwordField;
    private JButton smsButton;
    private JLabel number;
    private JPanel smsPanel;

    public FrameSms()
    {

    }

    //=============================================================

    public JPanel getSmsPanel() {
        return smsPanel;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getSmsButton() {
        return smsButton;
    }

    public void setNamber(String number) {
        this.number.setText(number);
    }

    public void setPerson(Person person) {
        setNamber(person.getNamber());
    }

    //=============================================================

    public Person getPerson() {
        Person person = new Person(getPasswordField().getPassword());
        return person;
    }

    public void addActionListenerForConfirm(ActionListener actionListener) {
        smsButton.addActionListener(actionListener);
        passwordField.addActionListener(actionListener);
    }

    public void removeActionListenerForConfirm(ActionListener actionListener) {
        smsButton.removeActionListener(actionListener);
        passwordField.removeActionListener(actionListener);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        smsPanel = new JPanel();
        smsPanel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("На данный номер телефона было отправлено");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        smsPanel.add(label1, gbc);
        smsButton = new JButton();
        smsButton.setMaximumSize(new Dimension(150, 32));
        smsButton.setMinimumSize(new Dimension(150, 32));
        smsButton.setPreferredSize(new Dimension(150, 32));
        smsButton.setText("Продолжить");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        smsPanel.add(smsButton, gbc);
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("SMS-сообщение с кодом подтверждения.");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        smsPanel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(0);
        label3.setHorizontalTextPosition(0);
        label3.setText("Пожалуйста, введите этот код в поле ниже:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        smsPanel.add(label3, gbc);
        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(0);
        passwordField.setMaximumSize(new Dimension(50, 23));
        passwordField.setMinimumSize(new Dimension(50, 23));
        passwordField.setPreferredSize(new Dimension(60, 23));
        passwordField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        smsPanel.add(passwordField, gbc);
        number = new JLabel();
        number.setAutoscrolls(false);
        number.setHorizontalAlignment(0);
        number.setHorizontalTextPosition(0);
        number.setName("");
        number.setText("Label");
        number.setToolTipText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        smsPanel.add(number, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        smsPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        smsPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        smsPanel.add(spacer3, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return smsPanel;
    }
}
