package by.ld1995tut.Messages;

import javax.swing.*;
import java.awt.*;

public class MessageForm extends JPanel
{
    private JEditorPane textPanel = new JEditorPane();
    private JLabel dateLabel = new JLabel();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);

    private Color color;

    private final int MARGIN = 5;
    private final int RADIUS = 25;

    public MessageForm(String text, String date, int width, Color color)
    {
        setLayout(boxLayout);

        textPanel.setAlignmentX(0.05f);
        add(textPanel);

        dateLabel.setAlignmentX(0.05f);
        add(dateLabel);

        textPanel.setSize(width, Short.MAX_VALUE);
        textPanel.setText(text);
        textPanel.setOpaque(false);
        textPanel.setEditable(false);
        textPanel.setMargin( new Insets(MARGIN,MARGIN,MARGIN,MARGIN));

        dateLabel.setText(date);
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRoundRect(textPanel.getX(), textPanel.getY(), textPanel.getWidth(), textPanel.getHeight(),RADIUS,MARGIN);
    }
}
