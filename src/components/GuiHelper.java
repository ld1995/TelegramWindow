package components;

import mics.MyScrollbarUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;

public class GuiHelper
{
    private GuiHelper()
    {}

    public static void decorateScrollPane(JScrollPane scrollPane)
    {
        int width = 3;

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new MyScrollbarUI());
        verticalScrollBar.setPreferredSize(new Dimension(width,Integer.MAX_VALUE));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new MyScrollbarUI());
        horizontalScrollBar.setPreferredSize(new Dimension(Integer.MAX_VALUE,width));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        for (String corner : new String[]{ScrollPaneConstants.LOWER_RIGHT_CORNER,ScrollPaneConstants.LOWER_LEFT_CORNER,
        ScrollPaneConstants.UPPER_LEFT_CORNER,ScrollPaneConstants.UPPER_RIGHT_CORNER})
        {
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            scrollPane.setCorner(corner,panel);
        }
    }

    public static Rectangle drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height)
    {
        Rectangle rectengle = getAreaFor(new Rectangle(x,y,width,height), new Dimension(image.getWidth(),image.getHeight()));
        g.drawImage(image,rectengle.x,rectengle.y,rectengle.width, rectengle.height, null);
        return rectengle;
    }

    public static int drowImage(Graphics g, BufferedImage image, int x, int y, int width, int height,int inset,boolean right)
    {
        int photoHeight = height - inset *2;
        int photoWidth = width - inset * 2;

        x+=inset;
        y+=inset;

        Rectangle rectangle = getAreaFor(new Rectangle(x,y,photoWidth,photoHeight),new Dimension(image.getWidth(),image.getHeight()));

        if (right)
        {
            x += width - rectangle.width;
        }

        g.drawImage(image,x,rectangle.y,rectangle.width,rectangle.height,null);

        if (right)
        {
            return x -inset;
        }
        else
        {
            return x + rectangle.width + inset;
        }
    }

    public static Rectangle getAreaFor(Rectangle area, Dimension size)
    {
        int x = area.x;
        int y = area.y;
        double dx = area.width /size.getWidth();
        double dy = area.height / size.getHeight();
        double d = Math.min(dy,dx);
        int newWidth = (int)Math.round(d * size.getWidth());
        int newHidht = (int)Math.round(d * size.getHeight());
        x+=(area.width - newWidth)/2;
        y+=(area.height - newHidht)/2;
        return  new Rectangle(x,y,newWidth,newHidht);
    }

    public static int drowText(Graphics g, String text, Color color, Font front, int x, int y, int width, int hight, int inset, boolean right)
    {
        String line = text;

        x+=inset;
        int maxWidth = width - inset * 2;
        FontMetrics fontMetrics = g.getFontMetrics(front);

        while (fontMetrics.stringWidth(line)>maxWidth)
        {
            if (line.length() > 3)
            {
                line = line.substring(0,line.length() - 4) + " ";
            }
            else if (right)
            {
                return x + maxWidth - inset;
            }
            else
                return x + inset;
        }

        LineMetrics lineMetrics = fontMetrics.getLineMetrics(line,g);
        y+=(int) Math.round(hight - lineMetrics.getHeight()/2.0 + fontMetrics.getAscent());

        if (right)
            x+= maxWidth - fontMetrics.stringWidth(line);

        g.setColor(color);
        g.setFont(front);
        g.drawString(line,x,y);

        if (right)
        {
            return x - inset;
        }
        else
            return  x + fontMetrics.stringWidth(line) + inset;
    }

    public static Color makeTranspatent(Color color, float transparency)
    {
        if (transparency < 0.0f || transparency > 1.0f)
        {
            throw new IllegalComponentStateException();
        }
        return new Color(color.getRed(),color.getGreen(),color.getBlue(), (int)Math.round(color.getAlpha()* transparency));
    }

    public static void adjustTextPane(JTextPane textPane)
    {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
}
