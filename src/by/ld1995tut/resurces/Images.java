package by.ld1995tut.resurces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images
{
    private Images()
    {

    }
    private static BufferedImage background;
    private static BufferedImage logo;
    private static BufferedImage smailUserImage;
    private static BufferedImage largeUserImage;

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


}
