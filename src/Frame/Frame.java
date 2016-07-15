package Frame;

import Contacts.ContactsList;
import undecorated.*;
import Messages.MessagesForm;
import overlays.*;
import resources.Images;
import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.proxy.TelegramProxy;
import org.javagram.dao.proxy.changes.UpdateChanges;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
    private MainForm mainForm = new MainForm();
    private ContactsList contacts = new ContactsList();

    private ProfileForm profilForm = new ProfileForm();
    private AddContact addContact = new AddContact();
    private EditContact editContact = new EditContact();
    private MyBufferedOverlayDialog mainWindowManager = new MyBufferedOverlayDialog(mainForm,profilForm,addContact, editContact);
    private static final int MAIN_WINDOW = -1, PROFILE_FORM = 0, ADD_CONTACT = 1, EDIT_CONTACT = 2;

    private PlusOverlay plusOverlay = new PlusOverlay();
    private MyLayeredPane contactsLayeredPane = new MyLayeredPane();

    private Timer timer;
    private int messagesFrozen;

    public Frame(TelegramDAO telegramDAO)
    {
        this.telegramDAO = telegramDAO;
    }

    {
        frameWindow = new FrameWindow(this, ComponentResizerAbstract.KEEP_RATIO_CENTER);
        changeContentPanel(number);
        setSize(925+4,390+39);
        setMinimumSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
               if (showQuestionMessage("Уверены, что хотите выйти?","Выйти"))
               {
                   exit();
               }
            }
        });
        number.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String phoneNumber = number.getNumberField();
                if (phoneNumber == null)
                {
                    showErrorMessage("Ввидите корректный номер телефона!","Ошибка!");
                }
                else
                {
                    switchPhoneToCod(phoneNumber);
                }
            }
        });
        sms.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String authCode = sms.getPasswordField();
                switchCod(authCode);
            }
        });
        reg.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                registration();
            }
        });

        mainForm.setList(contactsLayeredPane);
        contactsLayeredPane.add(contacts, new Integer(0));
        contactsLayeredPane.add(plusOverlay, new Integer(1));

        contacts.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (messagesFrozen != 0)
                {
                    return;
                }
                if (telegramProxy == null)
                {
                    displayDialog(null);
                }
                else
                {
                    displayDialog(contacts.getSelectedValue());
                }
            }
        });
        mainForm.addSearchEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFor(mainForm.getSearchText());
            }
        });
        plusOverlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact.setTelegramProxy(telegramProxy);
                mainWindowManager.setIndex(ADD_CONTACT);
            }
        });
        addContact.addActionListenerForLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowManager.setIndex(MAIN_WINDOW);
            }
        });
        addContact.addActionListenerForAddButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInformationMessage("Добавление невозможно","Внимание");
                updateContacts();
            }
        });
        mainForm.addSendMessageListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Person buddy = contacts.getSelectedValue();
                String text = mainForm.getMessageText();
                if (telegramProxy != null && buddy != null && !text.isEmpty())
                {
                    try
                    {
                        telegramProxy.sendMessage(buddy,text);
                        mainForm.setMessageText("");
                        checkForUpdates();
                    }
                    catch (Exception ex)
                    {
                        showErrorMessage("Сообщение не может быть отправлено","Ошибка");
                    }
                }
            }
        });
        mainForm.addGearEventListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                profilForm.setTelegramProxy(telegramProxy);
                mainWindowManager.setIndex(PROFILE_FORM);
            }
        });
        profilForm.addActionListenerForClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBegin();
            }
        });
        profilForm.addActionListenerForLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowManager.setIndex(MAIN_WINDOW);
            }
        });
        profilForm.addActionListenerSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInformationMessage("Сохранить изменения невозможно","Внимание");
            }
        });
        mainForm.addBuddyEditEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainWindowManager.setIndex(EDIT_CONTACT);
                editContact.setBuddyPhoto(mainForm.getBuddyPhoto());
                editContact.setBuddyText(mainForm.getBuddyText());
//                editContact.setNumber();
            }
        });
        editContact.addActionListenerForLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainWindowManager.setIndex(MAIN_WINDOW);
            }
        });
        editContact.addActionListenerForSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInformationMessage("Сохранить изменения невозможно", "Внимание");
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

            if (!Objects.equals(targetPerson, currentBuddy) ||
                    updateChanges.getDialogsToReset().contains(currentDialog) ||
                    updateChanges.getDialogsChanged().getDeleted().contains(currentDialog)) {
                updateMessages();
            } else if(updateChanges.getPersonsChanged().getChanged().containsKey(currentBuddy)
                    || updateChanges.getSmallPhotosChanged().contains(currentBuddy)
                    || updateChanges.getLargePhotosChanged().contains(currentBuddy)) {
                displayBuddy(targetPerson);
            }

            if(updateChanges.getPersonsChanged().getChanged().containsKey(telegramProxy.getMe())
                    || updateChanges.getSmallPhotosChanged().contains(telegramProxy.getMe())
                    || updateChanges.getLargePhotosChanged().contains(telegramProxy.getMe())) {
                displayMe(telegramProxy.getMe());
            }
        }
    }

    private void switchPhoneToCod(String phoneNumber)
    {
        try
        {
            telegramDAO.acceptNumber(phoneNumber.replace("[\\D]+",""));
            telegramDAO.sendCode();
            sms.setNamber(number.getNumberField());
            changeContentPanel(sms);
        }
        catch (IOException e)
        {
            showErrorMessage("Номер телефона введён неверно!", "Ошибка!");
            changeContentPanel(number);
            return;
        }
    }

    private void switchCod(String code)
    {
        try
        {
            if (telegramDAO.canSignIn())
            {
                telegramDAO.signIn(code);
                switchContacts();
            }
            else if (telegramDAO.canSignUp())
            {
                changeContentPanel(reg.getRegistrationPanel());
            }
        }
        catch (IOException e)
        {
            showErrorMessage("Неверный код","Внимание!");
        }
    }

    private void registration()
    {
        try
        {
            telegramDAO.signUp(sms.getPasswordField(),
                    reg.getLastName(),
                    reg.getFastName());
            switchContacts();
        }
        catch (IOException e)
        {
            showErrorMessage("Неверно заполнены поля регистрации","Внимание");
        }
    }

    private void switchContacts() {
        changeContentPanel(mainWindowManager);
        createTelergramProxy();
    }

    private void switchToBegin()
    {
        try
        {
            destroyTelegramProxy();
            this.sms.clear();
            this.number.clear();
            mainWindowManager.setIndex(MAIN_WINDOW);
            changeContentPanel(number);
            telegramDAO.logOut();
        }
        catch (Exception e)
        {
            showErrorMessage("Продолжение работы невозможно", "Критическая ошибка!");
            abort(e);
        }
    }

    private void searchFor(String searchText)
    {
        searchText = searchText.trim();
        if (searchText.isEmpty())
        {
            return;
        }
        String[] words = searchText.toLowerCase().split("\\s+");
        List<Person> persons = telegramProxy.getPersons();
        Person person = contacts.getSelectedValue();
        person = searchFor(searchText.toLowerCase(),words,persons,person);
        contacts.setSelectedValue(person);
        if (person == null)
        {
            showInformationMessage("Ничего не найдено", "Поиск");
        }
    }

    private static Person searchFor(String text, String[] words, List<? extends Person> persons, Person current)
    {
        int currentIndex = persons.indexOf(current);

        for (int i =1; i <= persons.size(); i++)
        {
            int index = (currentIndex + i)% persons.size();
            Person person = persons.get(index);
            if (contains(person.getFirstName().toLowerCase(),words) || contains(person.getLastName().toLowerCase(),words))
            {
                return person;
            }
        }
        return null;
    }

    private static boolean contains (String text, String ... words)
    {
        for (String word : words)
        {
            if (text.contains(word))
            {
                return true;
            }
        }
        return false;
    }

    private void changeContentPanel(Container contentPanel)
    {
        frameWindow.setContentPanel(contentPanel);
    }

    private void createTelergramProxy()
    {
        telegramProxy = new TelegramProxy(telegramDAO);
        updateTelegramProxy();
    }

    private void destroyTelegramProxy()
    {
        telegramProxy = null;
        updateTelegramProxy();
    }

    private void updateTelegramProxy()
    {
        messagesFrozen++;
        try
        {
            contacts.setTelegramProxy(telegramProxy);
            contacts.setSelectedValue(null);
            createMessagesForm();
            displayDialog(null);
            displayMe(telegramProxy != null ? telegramProxy.getMe() : null);
        }
        finally
        {
            messagesFrozen--;
        }

        mainForm.revalidate();
        mainForm.repaint();
    }

    private void updateContacts()
    {
        messagesFrozen++;
        try
        {
            Person person = contacts.getSelectedValue();
            contacts.setTelegramProxy(telegramProxy);
            contacts.setSelectedValue(person);
        } finally {
            messagesFrozen--;
        }
    }

    private void updateMessages()
    {
        displayDialog(contacts.getSelectedValue());
        mainForm.revalidate();
        mainForm.repaint();
    }

    private MessagesForm createMessagesForm()
    {
        MessagesForm messagesForm = new MessagesForm(telegramProxy);
        mainForm.setMessages(messagesForm);
        mainForm.revalidate();
        mainForm.repaint();
        return messagesForm;
    }

    private MessagesForm getMessagesForm()
    {
        if (mainForm.getMessages() instanceof MessagesForm)
        {
            return (MessagesForm) mainForm.getMessages();
        }
        else
        {
            return createMessagesForm();
        }
    }

    private void displayDialog(Person person)
    {
        try {
            MessagesForm messagesForm = getMessagesForm();
            messagesForm.display(person);
            displayBuddy(person);
            revalidate();
            repaint();
        }catch (Exception e)
        {
            showErrorMessage("Проблема соединения с сервером","Проблнмы в сети");
        }
    }

    private void displayMe(Me me)
    {
        if (me == null)
        {
            mainForm.setMePhoto(null);
            mainForm.setMeText(null);
        }
        else
        {
            mainForm.setMeText(me.getFirstName() + " " + me.getLastName());
            try {
                BufferedImage image = telegramProxy.getPhoto(me,true);
                if (image == null)
                {
                    mainForm.setMePhoto(Images.getDefaultUserPhoto());
                }
                else
                    mainForm.setMePhoto(image);
            } catch (IOException e) {
                mainForm.setMePhoto(Images.getDefaultUserPhoto());
                e.printStackTrace();
            }
        }
    }

    private void displayBuddy(Person person)
    {
        if (person == null)
        {
            mainForm.setBuddyPhoto(null);
            mainForm.setBuddyText(null);
        }
        else
        {
            mainForm.setBuddyText(person.getFirstName() + " " + person.getLastName());
            try {
                BufferedImage image = telegramProxy.getPhoto(person, true);
                if (image == null)
                {
                    mainForm.setBuddyPhoto(Images.getDefaultUserPhoto());
                }
                else
                {
                    mainForm.setBuddyPhoto(image);
                }
            } catch (IOException e) {
                mainForm.setBuddyPhoto(Images.getDefaultUserPhoto());
                e.printStackTrace();
            }
        }
    }

    private void abort(Exception e)
    {
        if (e != null)
        {
            e.printStackTrace();
        }
        else
        {
            System.err.println("Unknown Error");
            telegramDAO.close();
            System.exit(-1);
        }
    }

    private void exit()
    {
        telegramDAO.close();
        dispose();
        System.exit(0);
    }

    private void showErrorMessage(String text, String title)
    {
        FrameWindow.showDialog(this, text, title, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
    }

    private void showWarningMessage(String  text, String title)
    {
        FrameWindow.showDialog(this, text, title,JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
    }

    private void showInformationMessage(String text, String title)
    {
        FrameWindow.showDialog(this, text,title,JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
    }

    private boolean showQuestionMessage(String text, String title)
    {
        return FrameWindow.showDialog(this, text,title,JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
