package by.ld1995tut.Contacts;

import components.GuiHelper;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ContactsList extends JPanel {
    private JPanel contactsPanel;
    private JList<Person> list;
    private JScrollPane scrollPanel;
    TelegramProxy telegramProxy;

    public ContactsList() {
        $$$setupUI$$$();
        GuiHelper.decorateScrollPane(scrollPanel);
    }

    private void createUIComponents() {
        contactsPanel = this;
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        if (telegramProxy != null) {
            List<Person> dialogs = telegramProxy.getPersons();
            list.setCellRenderer(new ContactsForm(telegramProxy));
            list.setListData(dialogs.toArray(new Person[dialogs.size()]));
        } else {
            list.setCellRenderer(new DefaultListCellRenderer());
            list.setListData(new Person[0]);
        }
    }

    public JPanel getContactsPanel() {
        return contactsPanel;
    }

    public void addListSelectionListener(ListSelectionListener listSelectionListener) {
        list.addListSelectionListener(listSelectionListener);
    }

    public void removeListSelectionListener(ListSelectionListener listSelectionListener) {
        list.removeListSelectionListener(listSelectionListener);
    }

    public Person getSelectedValue() {
        return list.getSelectedValue();
    }

    public void setSelectedValue(Person person) {
        if (person != null) {
            ListModel<Person> model = list.getModel();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equals(person)) {
                    list.setSelectedIndex(i);
                    return;
                }
            }
        }
        list.clearSelection();
    }

    public TelegramProxy getTelegramProxy() {
        return telegramProxy;
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
        contactsPanel.setLayout(new BorderLayout(0, 0));
        contactsPanel.setBackground(new Color(-1644826));
        contactsPanel.setPreferredSize(new Dimension(250, 128));
        scrollPanel = new JScrollPane();
        scrollPanel.setBackground(new Color(-1644826));
        scrollPanel.setForeground(new Color(-1644826));
        scrollPanel.setOpaque(true);
        contactsPanel.add(scrollPanel, BorderLayout.CENTER);
        list = new JList();
        list.setBackground(new Color(-12828863));
        list.setDoubleBuffered(false);
        list.setDragEnabled(false);
        list.setFocusable(true);
        list.setForeground(new Color(-1644826));
        list.setOpaque(true);
        list.setRequestFocusEnabled(true);
        list.setSelectionBackground(new Color(-328961));
        list.setSelectionForeground(new Color(-14436636));
        list.setSelectionMode(0);
        scrollPanel.setViewportView(list);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contactsPanel;
    }
}
