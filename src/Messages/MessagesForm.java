package Messages;

import org.javagram.dao.Me;
import org.javagram.dao.Message;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MessagesForm extends JPanel {
    private JPanel rootPanel;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;

    private final int width = 150;
    private final int messagesCount = 100;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private TelegramProxy telegramProxy;
    private Person person;

    {
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
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

        if (person == null) {
            return;
        }

        List<Message> messageList = telegramProxy.getMessages(person, messagesCount);

        for (int i = messageList.size() - 1; i >= 0; i--) {
            JPanel panel = new JPanel() {
                @Override
                public Dimension getMaximumSize() {
                    Dimension maxSize = super.getMaximumSize();
                    Dimension prefSize = super.getPreferredSize();
                    return new Dimension(maxSize.width, prefSize.height);
                }
            };
            Message message = messageList.get(i);
            int aligment;
            Color color;
            if (message.getReceiver() instanceof Me) {
                aligment = FlowLayout.LEFT;
                color = Color.blue;
            } else if (message.getReceiver() instanceof Me) {
                aligment = FlowLayout.RIGHT;
                color = Color.cyan;
            } else {
                aligment = FlowLayout.CENTER;
                color = Color.red;
            }
            panel.setLayout(new FlowLayout(aligment));
            panel.add(new MessageForm(message.getText(), dateFormat.format(message.getDate()), width, color));
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
