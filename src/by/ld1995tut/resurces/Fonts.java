package by.ld1995tut.resurces;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Fonts
{
    private static Font nameFont;

    public static Font getNameFont()
    {
        if (nameFont == null)
        {
            nameFont = loadFont("OpenSansRegular.ttf");
        }
        return nameFont;
    }

    private static Font loadFont(String name)
    {
        try
        {
        InputStream inputStream = Fonts.class.getResourceAsStream("fonts/" + name);
            return Font.createFont(Font.TRUETYPE_FONT,inputStream);

        }
        catch (FontFormatException | IOException e)
        {
            e.printStackTrace();
            return new Font("serif",Font.PLAIN,14);
        }
    }
}
