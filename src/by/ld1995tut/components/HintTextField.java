package by.ld1995tut.components;

import resources.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class HintTextField extends JTextField
{
    private String hint;

    private Font hintFont;
    private Color hintForeground;
    private boolean hintOnFocus = true;
    private int hintAlignment = JTextField.LEFT;

    public HintTextField(String text, String hint, boolean hintOnFocus)
    {
        super();

        this.hint = hint;
        this.hintOnFocus = hintOnFocus;
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                HintTextField.this.focusGained();
            }

            @Override
            public void focusLost(FocusEvent e) {
                HintTextField.this.focusLost();
            }
        });

        setText(text);
    }

    public HintTextField(String text, String hint)
    {
        this(text,hint,true);
    }

    public HintTextField(String hint)
    {
        this("",hint);
    }

    public HintTextField()
    {
        this("...");
    }

    protected void focusGained()
    {
        if (hintOnFocus)
        {
            repaint();
        }
    }

    protected void focusLost()
    {
        if (hintOnFocus)
        {
            repaint();
        }
    }

    public void setHint(String hint)
    {
        if (!Objects.equals(this.hint,hint))
        {
            this.hint = hint;
            repaint();
        }
    }

    public String getHint() {
        return hint;
    }

    protected Font createHintFont()
    {
        if (getHintFont() != null)
            return getHintFont();
        else
            return getFont().deriveFont(getFont().getStyle() ^ Font.ITALIC);
    }

    protected Color createHintColor()
    {
        if (getHintForeground() != null)
            return getHintForeground();
        else
            return getForeground().brighter().brighter().brighter();
    }

    protected boolean isEmpti(String text)
    {
        return text == null || text.trim().isEmpty();
    }

    public Font getHintFont() {
        return hintFont;
    }

    public void setHintFont(Font hintFont) {
        this.hintFont = hintFont;
        repaint();
    }

    public Color getHintForeground() {
        return hintForeground;
    }

    public void setHintForeground(Color hintForeground) {
        this.hintForeground = hintForeground;
        repaint();
    }

    public boolean isHintOnFocus() {
        return hintOnFocus;
    }

    public void setHintOnFocus(boolean hintOnFocus) {
       if (this.hintOnFocus != hintOnFocus)
       {
           this.hintOnFocus = hintOnFocus;
           repaint();
       }
    }

    public int getHintAlignment() {
        return hintAlignment;
    }

    public void setHintAlignment(int hintAlignment) {
        if (this.hintAlignment != hintAlignment)
        {
            this.hintAlignment = hintAlignment;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isEmpti(getText()) && !(isFocusOwner() && hintOnFocus)) {
            Insets insets = getInsets();
            Insets margins = getMargin();
            int left = insets.left + margins.left;
            int right = insets.right + margins.right;
            int maxWidth = getWidth() - (left + right);
            g.setFont(createHintFont());
            g.setColor(createHintColor());
            FontMetrics fontMetrics = g.getFontMetrics();
            String hint = this.hint;
            while(fontMetrics.stringWidth(hint)>maxWidth) {
                int len = hint.length() - 4;
                if (len < 0)
                    break;
                else
                    hint = hint.substring(0,left) + "...";
            }
            int x = left;
            if (hintAlignment == JTextField.RIGHT) {
                x += maxWidth - fontMetrics.stringWidth(hint);
            } else if (hintAlignment == JTextField.CENTER) {
                x += (maxWidth - fontMetrics.stringWidth(hint)) / 2;
            }
            int y = getBaseline(getWidth(),getHeight());
            g.drawString(hint,x,y);
        }
    }

    public static HintTextField printHint(String text)
    {
        HintTextField hintTextField = new HintTextField("",text,false);
        hintTextField.setHintFont(Fonts.getOpenSansLight().deriveFont(0,28));
        hintTextField.setHintForeground(new Color(255,255,255));
        return hintTextField;
    }
}
