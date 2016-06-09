package by.ld1995tut.Frame;

import by.ld1995tut.Contacts.ContactsList;
import by.ld1995tut.FrameWindow;
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

    private FrameWindow frameWindow = new FrameWindow();
    private FrameNumber number = new FrameNumber();
    private FrameSms sms = new FrameSms();
    private Registration reg = new Registration();
    private ContactsList contacts = new ContactsList();
    private MainForm mainForm = new MainForm();

    private JPanel panel = frameWindow.getRootPanel();

    public Frame(TelegramDAO telegramDAO) throws Exception
    {
        setSize(800,600);
        setUndecorated(true);
        setContentPane(panel);
        changeContentPanel(number.getNumberPanel());
        this.telegramDAO = telegramDAO;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                exitMessage(panel);
            }
        });
        number.addActionListenerForConfirm(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (number.getPerson().hasControlNumber())
                {
                    switchPhonToCod();
                }
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
        mainForm.setListContacts(contacts.getContactsPanel());
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
                if (reg.getPerson().hasControlReg())
                {
                    registration();
                }
                else
                    errorMessage(panel);
            }
        });
        frameWindow.addActionListenerForClose(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                exitMessage(panel);
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
            telegramDAO.acceptNumber(number.getPerson().getNamber().trim().replaceAll("[\\D()-]+",""));
            telegramDAO.sendCode();
            sms.setPerson(number.getPerson());
            changeContentPanel(sms.getSmsPanel());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            changeContentPanel(number.getNumberPanel());
            errorMessage(panel);
            return;
        }
    }

    private void switchCod()
    {
        try
        {
            if (telegramDAO.canSignIn())
            {
                telegramDAO.signIn(smsCod());
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
            errorMessage(panel);
        }
    }

    private void registration()
    {
        try
        {
            telegramDAO.signUp(smsCod(),
                    reg.getPerson().getLastName().trim().replaceAll("[^A-ZА-ЯЁa-zа-яё]",""),
                    reg.getPerson().getFastName().trim().replaceAll("[^A-ZА-ЯЁa-zа-яё]",""));
            switchContacts();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            errorMessage(panel);
        }
    }

    private String smsCod()
    {
       return new String(sms.getPerson().getCode());
    }

    private void switchContacts()
    {
        changeContentPanel(mainForm.getRootPanel());
        contacts.getContacts();
    }

    private void changeContentPanel(Container contentPanel)
    {
        frameWindow.setInputPanel(contentPanel);
    }

    private void errorMessage(JPanel panel)
    {
        JOptionPane.showMessageDialog(panel,
                "Поле заполнено неверно",
                "Внимание",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitMessage(JPanel panel)
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
