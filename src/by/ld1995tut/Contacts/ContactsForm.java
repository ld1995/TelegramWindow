package by.ld1995tut.Contacts;

import by.ld1995tut.components.DateDisplay;
import by.ld1995tut.components.GuiHelper;
import by.ld1995tut.components.PhotoPanel;
import org.javagram.dao.Dialog;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;

public class ContactsForm extends JPanel implements ListCellRenderer<Person> {
    private JTextPane lastMessage;
    private JLabel nameLabel;
    private JPanel photoPanel;
    private JPanel rootPanel;
    private JLabel date;
    private TelegramProxy telegramProxy;


    private boolean select;
    private int indicatorSize = 3;

    public ContactsForm(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        this.lastMessage.setBorder(BorderFactory.createEmptyBorder());
    }

    private void createUIComponents() {
        rootPanel = this;
        photoPanel = new PhotoPanel(null,true,false,0,false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200,200,200));
        g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
        if (select) {
            g.setColor(new Color(0,173,233));
            g.fillRect((this.getWidth()-indicatorSize), 0, this.getWidth(), this.getHeight());
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> jList,
                                                  Person person, int index,
                                                  boolean selected, boolean hasFocus) {
        Dialog dialog = telegramProxy.getDialog(person);
        this.nameLabel.setText(person.getFirstName() + " " + person.getLastName());
        if (dialog != null) {
            this.lastMessage.setText(dialog.getLastMessage().getText());//лишний текст
            DateDisplay dateDisplay = new DateDisplay();
            this.date.setText(dateDisplay.display(dialog.getLastMessage().getDate()));
        } else {
            this.lastMessage.setText("");
        }
        if (selected) {
            this.rootPanel.setBackground(Color.white);
        } else {
            this.rootPanel.setBackground(new Color(230,230,230));
        }
        this.select = selected;
        ((PhotoPanel)photoPanel).setImage(GuiHelper.getPhoto(telegramProxy, person, true, true));
        ((PhotoPanel)photoPanel).setOnline(telegramProxy.isOnline(person));
        return this;
    }
}