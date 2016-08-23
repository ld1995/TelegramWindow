package resources;

import java.awt.*;
import java.io.InputStream;

public class Fonts
{
    private static Font openSansLight;
    private static Font openSansRegular;
    private static Font openSansSemiBold;

    public static Font getOpenSansLight() {
        if (openSansLight == null) {
            openSansLight = loadFont("OpenSansLight.ttf");
        }
        return openSansLight;
    }

    public static Font getOpenSansRegular() {
        if (openSansRegular == null) {
            openSansRegular = loadFont("OpenSansRegular.ttf");
        }
        return openSansRegular;
    }

    public static Font getOpenSansSemiBold() {
        if (openSansSemiBold == null) {
            openSansSemiBold = loadFont("OpenSansSemiBold.ttf");
        }
        return openSansSemiBold;
    }

    public static Font loadFont(String name)
    {
        try(InputStream inputStream = Fonts.class.getResourceAsStream("fonts/" + name)) {
            return Font.createFont(Font.TRUETYPE_FONT,inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("serif",Font.PLAIN,20);
        }
    }
}
