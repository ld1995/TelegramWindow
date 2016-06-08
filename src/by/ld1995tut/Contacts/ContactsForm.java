package by.ld1995tut.Contacts;

import org.javagram.dao.*;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;

public class ContactsForm extends JPanel implements ListCellRenderer<Person> {
    private JTextField lastMessage;
    private JLabel nameLabel;
    private JPanel fotoPanel;
    private JPanel rootPanel;

    private Person person;
    private boolean hasFocus;

    public ContactsForm() {

    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JPanel getFotoPanel() {
        return fotoPanel;
    }

    public void setFotoPanel(JPanel fotoPanel) {
        this.fotoPanel.add(fotoPanel);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> jList,
                                                  Person person, int index,
                                                  boolean selected, boolean hasFocus) {
        this.person = person;
        jList.getModel();
        setPreferredSize(new Dimension(0, 50));
        this.nameLabel.setText(person.getFirstName() + " " + person.getLastName());
        if (selected)
            setBackground(Color.white);
        else {
            setBackground(Color.lightGray);
        }
        this.hasFocus = hasFocus;
        return this;
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
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setPreferredSize(new Dimension(50, 200));
        fotoPanel = new JPanel();
        fotoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fotoPanel.setMinimumSize(new Dimension(50, 50));
        fotoPanel.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(fotoPanel, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(spacer1, gbc);
        lastMessage = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(lastMessage, gbc);
        nameLabel = new JLabel();
        nameLabel.setMinimumSize(new Dimension(150, 16));
        nameLabel.setPreferredSize(new Dimension(150, 16));
        nameLabel.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(nameLabel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
