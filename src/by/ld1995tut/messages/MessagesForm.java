package by.ld1995tut.messages;

import by.ld1995tut.components.GuiHelper;
import org.javagram.dao.Me;
import org.javagram.dao.Message;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MessagesForm extends JPanel{
    private JPanel rootPanel;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;

    private final int width = 150;
    private final int messagesCount = 100;
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd MMM yyyy");

    private TelegramProxy telegramProxy;
    private Person person;

    {
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        GuiHelper.decorateScrollPane(scrollPane);
    }

    public MessagesForm(TelegramProxy telegramProxy) {
        this(telegramProxy, null);
    }

    public MessagesForm(TelegramProxy telegramProxy, Person person) {
        this.telegramProxy = telegramProxy;
        display(person);
    }

    public void display(Person person) {

        scrollPanel.removeAll();
        this.person = person;

        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.add(Box.createGlue());

        if(person == null)
            return;

        List<Message> messages = telegramProxy.getMessages(person, messagesCount);

        for(int i = messages.size() - 1; i >= 0 ; i--) {
            JPanel panel = new JPanel() {
                @Override
                public Dimension getMaximumSize() {
                    Dimension maxSize = super.getMaximumSize();
                    Dimension prefSize = super.getPreferredSize();
                    return new Dimension(maxSize.width, prefSize.height);
                }

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.white);
                    g.fillRect(0,0,this.getWidth(),this.getHeight());
                }
            };
            Message message = messages.get(i);
            int alignment;
            Color color;
            String fontColor;
            if(message.getReceiver() instanceof Me) {
                alignment = FlowLayout.LEFT;
                color = new Color(0,179,233);
                fontColor = "white";
            } else if(message.getSender() instanceof Me) {
                alignment = FlowLayout.RIGHT;
                color = new Color(74,68,168);
                fontColor = "white";
            } else {
                alignment = FlowLayout.CENTER;
                color = Color.red;
                fontColor = "green";
            }
            panel.setLayout(new FlowLayout(alignment));
            panel.add(new MessageForm(message.getText(), dateFormat.format(message.getDate()), width, color, fontColor));
            scrollPanel.add(panel);
        }

        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }


    private void createUIComponents() {
    // TODO: place custom component creation code here
        rootPanel = this;
    }

    public TelegramProxy getTelegramProxy() {
        return telegramProxy;
    }

    public Person getPerson() {
        return person;
    }
}
