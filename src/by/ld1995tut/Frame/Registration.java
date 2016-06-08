package by.ld1995tut.Frame;

import by.ld1995tut.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Registration {
    private JPanel registrationPanel;
    private JTextField lastName;
    private JButton nextReg;
    private JTextField fastName;

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
        registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridBagLayout());
        registrationPanel.setFocusCycleRoot(false);
        registrationPanel.setMaximumSize(new Dimension(2147483647, 2147483647));
        registrationPanel.setMinimumSize(new Dimension(256, 161));
        registrationPanel.setPreferredSize(new Dimension(256, 161));
        fastName = new JTextField();
        fastName.setMinimumSize(new Dimension(150, 24));
        fastName.setPreferredSize(new Dimension(150, 24));
        fastName.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        registrationPanel.add(fastName, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        registrationPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        registrationPanel.add(spacer2, gbc);
        nextReg = new JButton();
        nextReg.setInheritsPopupMenu(false);
        nextReg.setMaximumSize(new Dimension(100, 32));
        nextReg.setMinimumSize(new Dimension(150, 32));
        nextReg.setPreferredSize(new Dimension(150, 32));
        nextReg.setText("Завершить");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        registrationPanel.add(nextReg, gbc);
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Введите ваше имя и фамилию и установите");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registrationPanel.add(label1, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        registrationPanel.add(spacer3, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Имя");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        registrationPanel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Фамилия");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        registrationPanel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(0);
        label4.setHorizontalTextPosition(0);
        label4.setText("фотографию для завершения регистрации");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        registrationPanel.add(label4, gbc);
        lastName = new JTextField();
        lastName.setMinimumSize(new Dimension(150, 24));
        lastName.setPreferredSize(new Dimension(150, 24));
        lastName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        registrationPanel.add(lastName, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return registrationPanel;
    }
}