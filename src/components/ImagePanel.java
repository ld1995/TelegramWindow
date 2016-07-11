package components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel
{
    private BufferedImage image;
    private boolean keepRatio;
    private int insetX, insetY;

    public ImagePanel(BufferedImage image,boolean opaque, boolean keepRatio, int insetX, int insetY) {
        this.image = image;
        setOpaque(opaque);
        this.keepRatio = keepRatio;
        this.insetX = insetX;
        this.insetY = insetY;
    }

    public ImagePanel(BufferedImage image, boolean opaque, boolean keepRatio, int inset)
    {
        this(image,opaque,keepRatio,inset, inset);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth() - insetX * 2;
        int height = this.getHeight() - insetY * 2;

        if (image == null)
        {
            return;
        }
        if (keepRatio)
        {
            GuiHelper.drawImage(g,image,insetX,insetY, width, height);
        }
        else
        {
            g.drawImage(image,insetX,insetX,width,height,null);
        }

    }
}
