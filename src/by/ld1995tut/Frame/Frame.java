package by.ld1995tut.Frame;

import by.ld1995tut.Contacts.ContactsList;
import by.ld1995tut.FrameWindow;
import by.ld1995tut.resurces.Images;
import org.javagram.dao.*;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Frame extends JFrame
{
    private TelegramDAO telegramDAO;
    private TelegramProxy telegramProxy;

    private FrameWindow frameWindow = new FrameWindow();
    private FrameNumber number = new FrameNumber();
    private FrameSms sms = new FrameSms();
    private Registration reg = new Registration();
    private ContactsList contacts = new ContactsList();
    private MainForm mainForm = new MainForm();

    private JPanel panel = frameWindow.getRootPanel();
    private JPanel numberPanel = number.getRootPanel();

    public Frame(TelegramDAO telegramDAO) throws Exception
    {
        setSize(800,600);
        setUndecorated(true);
        setContentPane(panel);
        changeContentPanel(numberPanel);
        this.telegramDAO = telegramDAO;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                exitMessage();
            }
        });
        number.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switchPhonToCod();
            }
        });
        sms.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switchCod();
            }
        });

        number.setMainImage(Images.getBackground());
        number.setLogoImage(Images.getLogo());
        number.setPhoneImage(Images.getPhone());

        sms.setMainImage(Images.getBackground());
        sms.setLogoImage(Images.getLogoMini());
        sms.setLockImage(Images.getLock());

        reg.setMainImage(Images.getBackground());
        reg.setLogoImage(Images.getLogoMini());

        mainForm.setList(contacts.getContactsPanel());
        mainForm.setSearch(Images.getSearch());
        mainForm.setLogo(Images.getLogoMicro());

        contacts.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
            }
        });
        reg.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                registration();
            }
        });
        frameWindow.addActionListenerForClose(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                exitMessage();
            }
        });
        frameWindow.addActionListenerForMinimize(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setState(JFrame.ICONIFIED);
            }
        });
    }

    private void switchPhonToCod()
    {
        try
        {
            telegramDAO.acceptNumber(number.getNumberField().replace("[\\D]+",""));
            telegramDAO.sendCode();
            sms.setNamber(number.getNumberField());
            changeContentPanel(sms.getSmsPanel());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            changeContentPanel(numberPanel);
            errorMessage();
            return;
        }
    }

    private void switchCod()
    {
        try
        {
            if (telegramDAO.canSignIn())
            {
                telegramDAO.signIn(sms.getPasswordField());
                switchContacts();
            }
            else if (telegramDAO.canSignUp())
            {
                changeContentPanel(reg.getRegistrationPanel());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            errorMessage();
        }
    }

    private void registration()
    {
        try
        {
            telegramDAO.signUp(sms.getPasswordField(),
                    reg.getPerson().getLastName(),
                    reg.getPerson().getFastName());
            switchContacts();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            errorMessage();
        }
    }

    private void switchContacts()
    {
        telegramProxy = new TelegramProxy(telegramDAO);
        contacts.getContacts(telegramProxy);
        changeContentPanel(mainForm.getRootPanel());
        mainForm.setUserInfo(telegramProxy);
    }

    private void changeContentPanel(Container contentPanel)
    {
        frameWindow.setInputPanel(contentPanel);
    }

    private void errorMessage()
    {
        JOptionPane.showMessageDialog(panel,
                "Поле заполнено неверно",
                "Внимание",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitMessage()
    {
        int option = JOptionPane.showConfirmDialog(panel,
                "Вы точно хотети выйти?",
                "Выход", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION)
        {
            exit();
        }
    }

    private void exit()
    {
        telegramDAO.close();
        dispose();
        System.exit(0);
    }
}
