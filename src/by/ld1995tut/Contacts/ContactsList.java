package by.ld1995tut.Contacts;

import by.ld1995tut.components.GuiHelper;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class ContactsList extends JPanel {
    private JPanel contactsPanel;
    private JList<Person> list;
    private JScrollPane scrollPanel;
    TelegramProxy telegramProxy;

    public ContactsList() {
        this.scrollPanel.setBorder(BorderFactory.createEmptyBorder());
        GuiHelper.decorateScrollPane(scrollPanel);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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

    public Person getSelectedValue()
    {
        return list.getSelectedValue();
    }

    public void setSelectedValue(Person person) {
        if (person != null) {
            ListModel<Person> model = list.getModel();
            for (int i =0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equals(person)) {
                    list.setSelectedIndex(i);
                    return;
                }
            }
        }
        list.clearSelection();
    }

}
