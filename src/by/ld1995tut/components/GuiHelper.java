package by.ld1995tut.components;

import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;
import resources.Images;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GuiHelper
{
    public GuiHelper() {
    }

    public static void decorateScrollPane(JScrollPane scrollPane) {
        int width = 3;

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new MyScrollbarUI());
        verticalScrollBar.setPreferredSize(new Dimension(width,Integer.MAX_VALUE));

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new MyScrollbarUI());
        horizontalScrollBar.setPreferredSize(new Dimension(Integer.MAX_VALUE,width));

        for (String corner : new String[]{ScrollPaneConstants.LOWER_RIGHT_CORNER,ScrollPaneConstants.LOWER_LEFT_CORNER,
            ScrollPaneConstants.UPPER_LEFT_CORNER, ScrollPaneConstants.UPPER_RIGHT_CORNER}) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.white);
            scrollPane.setCorner(corner,panel);
        }
    }

    public static Rectangle drawImage(Graphics graphics,Image image, int x, int y, int width, int height) {
        Rectangle rectangle = getAreaFor(new Rectangle(x,y,width,height),new Dimension(image.getWidth(null),image.getHeight(null)));
        graphics.drawImage(image,rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
        return rectangle;
    }

    public static int drawImage(Graphics graphics, BufferedImage image, int x, int y, int width, int height,int inset, boolean right) {
        int photoHeight = height - inset * 2;
        int photoWidth = width - inset * 2;

        x += inset;
        y += inset;

        Rectangle area = getAreaFor(new Rectangle(x,y,photoWidth,photoHeight), new Dimension(image.getWidth(), image.getHeight()));

        if (right)
            x += width - area.width;

        graphics.drawImage(image, x, area.y, area.width, area.height, null);

        if (right)
            return x - inset;
        else
            return x + area.width + inset;
    }

    public static Rectangle getAreaFor(Rectangle area, Dimension size) {
        int x = area.x;
        int y = area.y;
        double dx = area.width / size.getWidth();
        double dy = area.height / size.getHeight();
        double d = Math.min(dy,dx);
        int newWidth = (int) Math.round(d*size.getWidth());
        int newHeight = (int) Math.round(d*size.getHeight());
        x += (area.width - newWidth) / 2;
        y += (area.height - newHeight) / 2;
        return new Rectangle(x,y,newWidth,newHeight);
    }

    public static int drawText(Graphics g, String text, Color color, Font font, int x, int y, int width, int height, int inset, boolean right) {
        String line = text;

        x += inset;
        int maxWidth = width - inset * 2;
        FontMetrics fontMetrics = g.getFontMetrics(font);

        while (fontMetrics.stringWidth(line) > width) {
            if (line.length() > 3)
                line = line.substring(0,line.length() - 4) + "...";
            else if (right)
                return x + maxWidth - inset;
            else
                return x + inset;
        }

        LineMetrics lineMetrics = fontMetrics.getLineMetrics(line,g);
        y += (int) Math.round((height-lineMetrics.getHeight()) / 2.0 + fontMetrics.getAscent());

        if (right)
            x+= maxWidth - fontMetrics.stringWidth(line);

        g.setColor(color);
        g.setFont(font);
        g.drawString(line,x,y);

        if (right)
            return x - inset;
        else
            return fontMetrics.stringWidth(line) + inset;
    }

    public static Color makeTransparent(Color color, float transparency) {
        if (transparency < 0.0f || transparency > 1.0f)
            throw new IllegalArgumentException();
        return new Color(color.getRed(), color.getGreen(),color.getBlue(),(int) Math.round(color.getAlpha() * transparency));
    }

    public static void adjustTextPane(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public static BufferedImage getPhoto(TelegramProxy telegramProxy, Person person, boolean small) {
        BufferedImage image;
        try {
            image = telegramProxy.getPhoto(person, small);
        } catch (IOException e) {
            e.printStackTrace();
            image = null;
        }
        if (image == null)
            image = Images.getDefaultUserPhoto();
        return image;
    }

    public static BufferedImage getPhoto(TelegramProxy telegramProxy, Person person , boolean small, boolean circle) {
        BufferedImage photo = getPhoto(telegramProxy,person,small);
        if (circle)
            photo = makeCircle(photo);
        return photo;
    }

    public static BufferedImage makeCircle(Image image) {
        BufferedImage circle = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = circle.createGraphics();
        try {
            g2d.clip(new Ellipse2D.Double(0,0,circle.getWidth(),circle.getHeight()));
            g2d.drawImage(image,0,0, null);
        } finally {
            g2d.dispose();
        }
        return circle;
    }
}