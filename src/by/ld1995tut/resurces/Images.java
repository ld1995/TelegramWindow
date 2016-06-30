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

    public synchronized static BufferedImage getBackground()
    {
        if (background == null)
        {
            try {
                background = ImageIO.read(Images.class.getResource("/images/background.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return background;
    }

    public synchronized static BufferedImage getLogo()
    {
        if (logo == null)
        {
            try {
                logo = ImageIO.read(Images.class.getResource("/images/logo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logo;
    }

    public synchronized static BufferedImage getLogoMini()
    {
        if (logoMini == null)
        {
            try {
                logoMini = ImageIO.read(Images.class.getResource("/images/logo-mini.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logoMini;
    }

    public synchronized static BufferedImage getPhone()
    {
        if (phone == null)
        {
            try {
                phone = ImageIO.read(new File("resources/images/icon-phone.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return phone;
    }

    public synchronized static BufferedImage getLock()
    {
        if (lock == null)
        {
            try {
                lock = ImageIO.read(new File("resources/images/icon-lock.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lock;
    }

    public synchronized static BufferedImage getLogoMicro()
    {
        if (logoMicro == null)
        {
            try {
                logoMicro = ImageIO.read(new File("resources/images/logo-micro.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logoMicro;
    }

    public synchronized static BufferedImage getSearch()
    {
        if (search == null)
        {
            try {
                search = ImageIO.read(new File("resources/images/icon-search.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return search;
    }

    public synchronized static BufferedImage getUserFotoMask()
    {
        if (userFotoMask == null)
        {
            try {
                userFotoMask = ImageIO.read(new File("resources/images/mask-blue-mini.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userFotoMask;
    }

    public synchronized static BufferedImage getUserFotoMaskWhite()
    {
        if (userFotoMaskWhite == null)
        {
            try {
                userFotoMaskWhite = ImageIO.read(new File("resources/images/mask-white-mini.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userFotoMaskWhite;
    }

    public synchronized static BufferedImage getDefaultUserFoto()
    {
        if (defaultUserFoto == null)
        {
            try {
                defaultUserFoto = ImageIO.read(new File("resources/images/default-user-avatar.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultUserFoto;
    }

    public synchronized static BufferedImage getFotoMaskGray()
    {
        if (maskGray == null)
        {
            try {
                maskGray = ImageIO.read(new File("resources/images/mask-gray.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maskGray;
    }

    public synchronized static BufferedImage getFotoMaskWhite()
    {
        if (maskWhite == null)
        {
            try {
                maskWhite = ImageIO.read(new File("resources/images/mask-white.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maskWhite;
    }

    public synchronized static BufferedImage getFotoMaskOnlineGray()
    {
        if (maskOnlineGray == null)
        {
            try {
                maskOnlineGray = ImageIO.read(new File("resources/images/mask-gray-online.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maskOnlineGray;
    }

    public synchronized static BufferedImage getFotoMaskOnlineWhite()
    {
        if (maskOnlineWhite == null)
        {
            try {
                maskOnlineWhite = ImageIO.read(new File("resources/images/mask-white-online.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maskOnlineWhite;
    }
}