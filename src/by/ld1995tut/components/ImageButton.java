package by.ld1995tut.components;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton
{
    private Image image;
    private boolean keepRatio;
    private Image disabledImage;
    private boolean keepDisabledRatio;

    {
        setOpaque(false);
    }

    public ImageButton(Image image) {
        this(image,true,null,true);
    }

    public ImageButton(Image image, boolean keepRatio, Image disabledImage, boolean keepDisabledRatio) {
        this.image = image;
        this.keepRatio = keepRatio;
        this.disabledImage = disabledImage;
        this.keepDisabledRatio = keepDisabledRatio;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null)
        {
            super.paintComponent(g);
        }
        else
        {
            if (isOpaque())
            {
                g.setColor(getBackground());
                g.fillRect(0,0, getWidth(), getHeight());
            }
            if (isEnabled())
            {
                if (keepRatio)
                    GuiHelper.drawImage(g,image,0,0, this.getWidth(), this.getHeight());
                else
                    g.drawImage(image,0,0,this.getWidth(), this.getHeight(), null);
            }
            else if (disabledImage != null)
            {
                if (keepDisabledRatio)
                    GuiHelper.drawImage(g,disabledImage,0,0, this.getWidth(), this.getHeight());
                else
                    g.drawImage(disabledImage,0,0,this.getWidth(), this.getHeight(), null);
            }
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (image == null)
        {
            super.paintBorder(g);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isKeepRatio() {
        return keepRatio;
    }

    public void setKeepRatio(boolean keepRatio) {
        this.keepRatio = keepRatio;
    }

    public Image getDisabledImage() {
        return disabledImage;
    }

    public void setDisabledImage(Image disabledImage) {
        this.disabledImage = disabledImage;
    }

    public boolean isKeepDisabledRatio() {
        return keepDisabledRatio;
    }

    public void setKeepDisabledRatio(boolean keepDisabledRatio) {
        this.keepDisabledRatio = keepDisabledRatio;
    }
}
