package by.ld1995tut.resurces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images
{
    private Images()
    {

    }

    private static BufferedImage background;
    private static BufferedImage logo;
    private static BufferedImage logoMini;
    private static BufferedImage phone;
    private static BufferedImage lock;
    private static BufferedImage logoMicro;
    private static BufferedImage search;
    private static BufferedImage userFotoMask;
    private static BufferedImage userFotoMaskWhite;
    private static BufferedImage defaultUserFoto;
    private static BufferedImage maskGray;
    private static BufferedImage maskWhite;
    private static BufferedImage maskOnlineGray;
    private static BufferedImage maskOnlineWhite;
    private static BufferedImage iconHide;
    private static BufferedImage iconClose;

    public synchronized static BufferedImage getBackground()
    {
        if (background == null)
        {
            background = loadImage("background.png");
        }
        return background;
    }

    public synchronized static BufferedImage getLogo()
    {
        if (logo == null)
        {
            logo = loadImage("logo.png");
        }
        return logo;
    }

    public synchronized static BufferedImage getLogoMini()
    {
        if (logoMini == null)
        {
            logoMini = loadImage("logo-mini.png");
        }
        return logoMini;
    }

    public synchronized static BufferedImage getPhone()
    {
        if (phone == null)
        {
            phone = loadImage("icon-phone.png");
        }
        return phone;
    }

    public synchronized static BufferedImage getLock()
    {
        if (lock == null)
        {
            lock = loadImage("icon-lock.png");
        }
        return lock;
    }

    public synchronized static BufferedImage getLogoMicro()
    {
        if (logoMicro == null)
        {
            logoMicro = loadImage("logo-micro.png");
        }
        return logoMicro;
    }

    public synchronized static BufferedImage getSearch()
    {
        if (search == null)
        {
            search = loadImage("icon-search.png");
        }
        return search;
    }

    public synchronized static BufferedImage getUserPhotoMask()
    {
        if (userFotoMask == null)
        {
            userFotoMask = loadImage("mask-blue-mini.png");
        }
        return userFotoMask;
    }

    public synchronized static BufferedImage getUserPhotoMaskWhite()
    {
        if (userFotoMaskWhite == null)
        {
            userFotoMaskWhite = loadImage("mask-white-mini.png");
        }
        return userFotoMaskWhite;
    }

    public synchronized static BufferedImage getDefaultUserPhoto()
    {
        if (defaultUserFoto == null)
        {
            defaultUserFoto = loadImage("default-user-avatar.png");
        }
        return defaultUserFoto;
    }

    public synchronized static BufferedImage getPhotoMaskGray()
    {
        if (maskGray == null)
        {
            maskGray = loadImage("mask-gray.png");
        }
        return maskGray;
    }

    public synchronized static BufferedImage getPhotoMaskWhite()
    {
        if (maskWhite == null)
        {
            maskWhite = loadImage("mask-white.png");
        }
        return maskWhite;
    }

    public synchronized static BufferedImage getPhotoMaskOnlineGray()
    {
        if (maskOnlineGray == null)
        {
            maskOnlineGray = loadImage("mask-gray-online.png");
        }
        return maskOnlineGray;
    }

    public synchronized static BufferedImage getPhotoMaskOnlineWhite()
    {
        if (maskOnlineWhite == null)
        {
            maskOnlineWhite = loadImage("mask-white-online.png");
        }
        return maskOnlineWhite;
    }

    public synchronized static BufferedImage getIconHide()
    {
        if (iconHide == null)
        {
            iconHide = loadImage("icon-hide.png");
        }
        return iconHide;
    }

    public synchronized static BufferedImage getIconClose()
    {
        if (iconClose == null)
        {
            iconClose = loadImage("icon-close.png");
        }
        return iconClose;
    }

    private static BufferedImage loadImage(String name)
    {
        try {
            return ImageIO.read(new File("resources/images/" + name));
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR);
        }
    }
}