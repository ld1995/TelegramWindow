package resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    private Images() {

    }

    private static BufferedImage background;
    private static BufferedImage iconHide;
    private static BufferedImage iconClose;
    private static BufferedImage logo;
    private static BufferedImage iconPhone;
    private static BufferedImage iconEdit;
    private static BufferedImage iconLock;
    private static BufferedImage logoMini;
    private static BufferedImage logoMicro;
    private static BufferedImage iconSearch;
    private static BufferedImage iconApplication;
    private static BufferedImage defaultUserPhoto;

    public synchronized static BufferedImage getBackground() {
        if (background == null)
        {
            background = loadImage("background.png");
        }
        return background;
    }

    public synchronized static BufferedImage getIconHide() {
        if (iconHide == null)
        {
            iconHide = loadImage("icon-hide.png");
        }
        return iconHide;
    }

    public synchronized static BufferedImage getIconClose() {
        if (iconClose == null)
        {
            iconClose = loadImage("icon-close.png");
        }
        return iconClose;
    }

    public synchronized static BufferedImage getLogo() {
        if (logo == null)
        {
            logo = loadImage("logo.png");
        }
        return logo;
    }

    public synchronized static BufferedImage getIconLock() {
        if (iconLock == null)
        {
            iconLock = loadImage("icon-lock.png");
        }
        return iconLock;
    }

    public synchronized static BufferedImage getLogoMini() {
        if (logoMini == null)
        {
            logoMini = loadImage("logo-mini.png");
        }
        return logoMini;
    }

    public synchronized static BufferedImage getIconPhone() {
        if (iconPhone == null)
        {
            iconPhone = loadImage("icon-phone.png");
        }
        return iconPhone;
    }

    public synchronized static BufferedImage getLogoMicro() {
        if (logoMicro == null)
        {
            logoMicro = loadImage("logo-micro.png");
        }
        return logoMicro;
    }

    public synchronized static BufferedImage getIconSearch() {
        if (iconSearch == null)
        {
            iconSearch = loadImage("icon-search.png");
        }
        return iconSearch;
    }

    public synchronized static BufferedImage getIconApplication() {
        if (iconApplication == null)
        {
            iconApplication = loadImage("телеграмма-2.png");
        }
        return iconApplication;
    }

    public synchronized static BufferedImage getDefaultUserPhoto() {
        if (defaultUserPhoto == null)
        {
            defaultUserPhoto = loadImage("default-user-avatar.png");
        }
        return defaultUserPhoto;
    }

    public synchronized static BufferedImage getIconEdit() {
        if (iconEdit == null)
        {
            iconEdit = loadImage("icon-edit.png");
        }
        return iconEdit;
    }

    private static BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(Images.class.getResource("images/" + name));
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(1,1, BufferedImage.TYPE_4BYTE_ABGR);
        }
    }
}
