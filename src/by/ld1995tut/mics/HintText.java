package by.ld1995tut.mics;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintText
{
    public HintText(final JTextComponent textField, final String text, final Color color)
    {
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setText(text);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setForeground(color);
                if (textField.getText().equals(text)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.LIGHT_GRAY);
                    textField.setText(text);
                }
            }
        });
    }
}
