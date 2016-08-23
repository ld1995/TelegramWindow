package by.ld1995tut.Frame;

import by.ld1995tut.Contacts.ContactsList;
import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.messages.MessagesForm;
import by.ld1995tut.overlays.*;
import by.ld1995tut.undecorated.ComponentResizerAbstract;
import by.ld1995tut.undecorated.FrameWindow;
import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.proxy.TelegramProxy;
import org.javagram.dao.proxy.changes.UpdateChanges;
import resources.Images;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Frame extends JFrame
{
    private TelegramDAO telegramDAO;
    private TelegramProxy telegramProxy;

    private FrameWindow frameWindow;
    private FrameNumber number = new FrameNumber();
    private FrameSms sms = new FrameSms();
    private Registration reg = new Registration();
    private ContactsList contacts = new ContactsList();
    private MainForm mainForm = new MainForm();

    private PlusOverlay plusOverlay = new PlusOverlay();
    private MyLayeredPane myLayeredPane = new MyLayeredPane();

    private AddContactForm addContact = new AddContactForm();
    private ProfileForm profileForm = new ProfileForm();
    private EditContactForm editContact = new EditContactForm();
    private MyBufferedOverlayDialog mainWindowsManager = new MyBufferedOverlayDialog(mainForm, profileForm, addContact,editContact);
    private static final int MAIN_WINDOW = -1, PROFILE_FORM = 0, ADD_CONTACT_FORM = 1, EDIT_CONTACT_FORM = 2;

    private Timer timer;
    private int messagesFrozen;

    public Frame(TelegramDAO telegramDAO) throws Exception
    {
        frameWindow = new FrameWindow(this, ComponentResizerAbstract.KEEP_RATIO_CENTER);
        setIconImage(Images.getIconApplication());
        changeContentPanel(number);
        this.telegramDAO = telegramDAO;
        setSize(800,600);
        setMinimumSize(new Dimension(800,600));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                exitMessage("Уверены, что хотите выйти?","Вопрос");
            }

            @Override
            public void windowOpened(WindowEvent e) {
                number.transferFocusTo();
            }
        });

        number.addActionListenerForConfirm(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = number.getNumberField();
                if (phoneNumber == null) {
                    errorMessage("Введите номер телефона!","Ошибка");
                    number.transferFocusTo();
                } else {
                    switchPhoneToCod(phoneNumber);
                }
            }
        });

        sms.addActionListenerForConfirm(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authCode = sms.getPasswordField();
                switchCod(authCode);
            }
        });

        reg.addActionListenerForConfirm(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = sms.getPasswordField();
                String last = reg.getLastName();
                String fast = reg.getFastName();
                if (last.isEmpty()) {
                    warningMessage("Поле имени не заполнено","Внимание!");
                    reg.transferFocusToLastTextField();
                } else if (fast.isEmpty()) {
                    warningMessage("Поле имени не заполнено","Внимание!");
                    reg.transferFocusToFastTextField();
                } else {
                    registration(code,last,fast);
                }
            }
        });

        mainForm.setListContacts(myLayeredPane);
        myLayeredPane.add(contacts, new Integer(0));
        myLayeredPane.add(plusOverlay, new Integer(1));

        contacts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (messagesFrozen != 0)
                    return;

                if (telegramProxy == null) {
                    displayDialog(null);
                } else {
                    displayDialog(contacts.getSelectedValue());
                }
            }
        });

        mainForm.addBuddyEditEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = contacts.getSelectedValue();
                if (person instanceof Contact) {
                    editContact.setContactInfo(new ContactInfo((Contact) person));
                    editContact.setPhoto(GuiHelper.getPhoto(telegramProxy,person,false,true));
                    mainWindowsManager.setIndex(EDIT_CONTACT_FORM);
                }
            }
        });

        mainForm.addGearEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileForm.setMePhoto(GuiHelper.getPhoto(telegramProxy,telegramProxy.getMe(),true,false));
                profileForm.setTelegramProxy(telegramProxy);
                mainWindowsManager.setIndex(PROFILE_FORM);
            }
        });

        mainForm.addSearchEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFor(mainForm.getSearchTextField());
            }
        });

        mainForm.addSendMessageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person buddy = contacts.getSelectedValue();
                String text = mainForm.getMessageText().trim();
                if (telegramProxy != null && buddy != null && !text.isEmpty()) {
                    try {
                        telegramProxy.sendMessage(buddy,text);
                        mainForm.setMessageText("");
                        checkForUpdates();
                    } catch (Exception e1) {
                        errorMessage("Ошибка отправления сообщения","Ошибка!");
                    }
                }
            }
        });

        plusOverlay.addActionListenerPlus(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact.setContactInfo(new ContactInfo());
                mainWindowsManager.setIndex(ADD_CONTACT_FORM);
            }
        });

        addContact.addActionListenerForAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryAddContact(addContact.getContactInfo());
            }
        });

        addContact.addActionListenerForClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowsManager.setIndex(MAIN_WINDOW);
            }
        });

        profileForm.addActionListenerForClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowsManager.setIndex(MAIN_WINDOW);
            }
        });

        profileForm.addActionListenerForLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBegin();
            }
        });

        profileForm.addActionListenerForSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editContact.addActionListenerForClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowsManager.setIndex(MAIN_WINDOW);
            }
        });

        editContact.addActionListenerForSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryUpdateContact(editContact.getContactInfo());
            }
        });

        editContact.addActionListenerForDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryDeleteContact(editContact.getContactInfo());
            }
        });

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkForUpdates();
            }
        });
        timer.start();
    }

    protected void checkForUpdates() {
        if (telegramProxy != null) {
            UpdateChanges updateChanges = telegramProxy.update();

            int photosChangedCount = updateChanges.getLargePhotosChanged().size() +
                    updateChanges.getSmallPhotosChanged().size() +
                    updateChanges.getStatusesChanged().size();
            if (updateChanges.getListChanged()) {
                updateContacts();
            } else if (photosChangedCount != 0) {
                contacts.repaint();
            }

            Person currentBuddy = getMessagesForm().getPerson();
            Person targetPerson = contacts.getSelectedValue();

            Dialog currentDialog = currentBuddy != null ? telegramProxy.getDialog(currentBuddy) : null;

            if (!Objects.equals(targetPerson,currentBuddy) ||
                    updateChanges.getDialogsToReset().contains(currentDialog) ||
                    updateChanges.getDialogsChanged().getDeleted().contains(currentDialog)) {
                updateMessage();
            } else if (updateChanges.getPersonsChanged().getChanged().containsKey(currentBuddy)
                    || updateChanges.getSmallPhotosChanged().contains(currentBuddy)
                    || updateChanges.getLargePhotosChanged().contains(currentBuddy)) {
                displayBuddy(targetPerson);
            }

            if (updateChanges.getPersonsChanged().getChanged().containsKey(telegramProxy.getMe())
                    || updateChanges.getSmallPhotosChanged().contains(telegramProxy.getMe())
                    || updateChanges.getLargePhotosChanged().contains(telegramProxy.getMe())) {
                displayMe(telegramProxy.getMe());
            }
        }
    }

    private void switchPhoneToCod(String phoneNumber) {
        try {
            telegramDAO.acceptNumber(phoneNumber.replace("[\\D]+",""));
            telegramDAO.sendCode();
            sms.setNumber(number.getNumberField());
            changeContentPanel(sms);
            sms.transferFocusTo();
        } catch (IOException e) {
            warningMessage("Поле заполнено неверно", "Внимание");
            number.transferFocusTo();
            return;
        }
    }

    private void switchCod(String code) {
        try {
            if (telegramDAO.canSignIn()) {
                telegramDAO.signIn(code);
                switchContacts();
            } else if (telegramDAO.canSignUp()) {
                changeContentPanel(reg);
            }
        } catch (IOException e) {
            warningMessage("Неверный код", "Внимание");
            sms.transferFocusTo();
        }
    }

    private void registration(String code, String lastName, String fastName) {
        try {
            telegramDAO.signUp(code,lastName,fastName);
            switchContacts();
        } catch (IOException e) {
           warningMessage("Поля заполнены неверно", "Внимание");
        }
    }

    private void switchContacts() {
        createTelegramProxy();
        changeContentPanel(mainWindowsManager);
        displayMe(telegramProxy.getMe());
    }

    private void switchToBegin() {
        try {
            destroyTelegramProxy();
            this.sms.clear();
            this.number.clear();
            this.mainForm.clear();
            mainWindowsManager.setIndex(MAIN_WINDOW);
            changeContentPanel(number);
            number.transferFocusTo();
            telegramDAO.logOut();
        } catch (Exception e) {
            errorMessage("Продолжение работы не возможно","Критическая ошибка!");
            abort(e);
        }
    }

    private void searchFor(String text) {
        text = text.trim();
        if (text.isEmpty()) {
            return;
        }
        String[] words = text.toLowerCase().split("\\s+");
        java.util.List<Person> personList = telegramProxy.getPersons();
        Person person = contacts.getSelectedValue();
        person = searchFor(text.toLowerCase(),words,personList,person);
        contacts.setSelectedValue(person);
        if (person == null) {
            warningMessage("Ничего не найдено","Поиск");
        }
    }

    private static Person searchFor(String text, String [] word, List<? extends Person> persons,Person current) {
        int currentIndex = persons.indexOf(current);
        for (int i = 1; i <= persons.size(); i++) {
            int index = (currentIndex + i) % persons.size();
            Person person = persons.get(index);
            if (contains(person.getFirstName().toLowerCase(),word) || contains(person.getLastName().toLowerCase(),word)) {
                return person;
            }
        }
        return null;
    }

    private static boolean contains(String text, String ... words) {
        for (String word : words) {
            if (text.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private void changeContentPanel(Container contentPanel)
    {
        frameWindow.setContentPanel(contentPanel);
    }

    private void createTelegramProxy() {
        telegramProxy = new TelegramProxy(telegramDAO);
        updateTelegramProxy();
    }

    private void destroyTelegramProxy() {
        telegramProxy = null;
        updateTelegramProxy();
    }

    private void updateTelegramProxy() {
        messagesFrozen++;
        try {
            contacts.setTelegramProxy(telegramProxy);
            contacts.setSelectedValue(null);
            createMessagesForm();
            displayDialog(null);
            displayMe(telegramProxy != null ? telegramProxy.getMe() : null);
        } finally {
            messagesFrozen--;
        }

        mainForm.revalidate();
        mainForm.repaint();
    }

    private void updateContacts() {
        messagesFrozen++;
        try {
            Person person = contacts.getSelectedValue();
            contacts.setTelegramProxy(telegramProxy);
            contacts.setSelectedValue(person);
        } finally {
            messagesFrozen--;
        }
    }

    private void updateMessage() {
        displayDialog(contacts.getSelectedValue());
        mainForm.revalidate();
        mainForm.repaint();
    }

    private MessagesForm createMessagesForm() {
        MessagesForm messagesForm = new MessagesForm(telegramProxy);
        mainForm.setMessagePanel(messagesForm);
        mainForm.revalidate();
        mainForm.repaint();
        return messagesForm;
    }

    private MessagesForm getMessagesForm() {
        if (mainForm.getMessagePanel() instanceof MessagesForm) {
            return (MessagesForm) mainForm.getMessagePanel();
        } else {
            return createMessagesForm();
        }
    }

    private void displayDialog(Person person) {
        try {
            MessagesForm messagesForm = getMessagesForm();
            messagesForm.display(person);
            displayBuddy(person);
            revalidate();
            repaint();
        } catch (Exception e) {
            warningMessage("Проблема соединения с сервером","Проблемы в сети");
        }
    }

    private void displayMe(Me me) {
        if (me == null) {
            mainForm.setMePhoto(null);
            mainForm.setMeText(null);
        } else {
            mainForm.setMeText(me.getFirstName() + " " + me.getLastName());
            mainForm.setMePhoto(GuiHelper.getPhoto(telegramProxy,me,true,true));
        }
    }

    private void displayBuddy(Person person) {
        if (person == null) {
            mainForm.setBuddyText(null);
            mainForm.setBuddyPhoto(null);
            mainForm.setBuddyEditEnabled(false);
        } else {
            mainForm.setBuddyText(person.getFirstName() + " " + person.getLastName());
            mainForm.setBuddyPhoto(GuiHelper.getPhoto(telegramProxy,person,true,true));
            mainForm.setBuddyEditEnabled(person instanceof Contact);
        }
    }

    private boolean tryAddContact(ContactInfo info) {
        String phone = info.getClearedPhone();
        if (phone.isEmpty()) {
            warningMessage("Пожалуйста, введите номер телефона","Ошибка");
            return false;
        }
        if (info.getFirstName().trim().isEmpty() && info.getLastName().trim().isEmpty()) {
            warningMessage("Пожалуйста, введите имя и/или фамилию","Ошибка");
            return false;
        }
        for (Person person : telegramProxy.getPersons()) {
            if (person instanceof Contact) {
                if (((Contact)person).getPhoneNumber().replaceAll("\\D+","").equals(phone)) {
                    warningMessage("Контакт с таким номером уже существует","Ошибка");
                    return false;
                }
            }
        }
        if (!telegramProxy.importContact(info.getPhone(),info.getFirstName(),info.getLastName())) {
            errorMessage("Ошибка на сервере при добавлении контакта","Ошибка");
            return false;
        }
        mainWindowsManager.setIndex(MAIN_WINDOW);
        checkForUpdates();
        return true;
    }

    private boolean tryUpdateContact(ContactInfo info) {
        String phone = info.getClearedPhone();
        if (info.getFirstName().trim().isEmpty() && info.getLastName().trim().isEmpty()) {
            warningMessage("Пожалуйста, введите имя и/или фамилию","Ошибка");
            return false;
        }
        if (!telegramProxy.importContact(info.getPhone(),info.getFirstName(),info.getLastName())) {
            errorMessage("Ошибка на сервере при изменении контакта","Ошибка");
            return false;
        }
        mainWindowsManager.setIndex(MAIN_WINDOW);
        checkForUpdates();
        return true;
    }

    private boolean tryDeleteContact(ContactInfo info) {
        int id = info.getId();
        if (!telegramProxy.deleteContact(id)) {
            errorMessage("Ошибка на сервере при удалении контакта","Ошибка");
            return false;
        }
        mainWindowsManager.setIndex(MAIN_WINDOW);
        checkForUpdates();
        return true;
    }

    private void abort(Exception e) {
        if (e != null) {
            e.printStackTrace();
        } else {
            System.err.println("Unknown Error");
            telegramDAO.close();
            System.exit(-1);
        }
    }

    private void exit() {
        telegramDAO.close();
        dispose();
        System.exit(0);
    }

    private void errorMessage(String textInformation, String textTitle)
    {
        JOptionPane.showMessageDialog(frameWindow,
                textInformation,textTitle,
                JOptionPane.ERROR_MESSAGE);
    }

    private void warningMessage(String textInformation, String textTitle)
    {
        JOptionPane.showMessageDialog(frameWindow,
                textInformation,textTitle,
                JOptionPane.WARNING_MESSAGE);
    }

    private void exitMessage(String textInformation, String textTitle)
    {
        int option = JOptionPane.showConfirmDialog(frameWindow,
                textInformation,
                textTitle, JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.YES_OPTION)
        {
            exit();
        }
    }
}
