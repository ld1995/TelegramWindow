package by.ld1995tut.mics;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class MyScrolldarUI extends BasicScrollBarUI
{
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        super.paintThumb(g, c, thumbBounds);
        g.setColor(new Color(35, 182, 228));
        g.fillRect(thumbBounds.x,thumbBounds.y,thumbBounds.width,thumbBounds.height);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        super.paintTrack(g, c, trackBounds);
        g.setColor(new Color(255,255,255));
        g.fillRect(trackBounds.x,trackBounds.y,trackBounds.width,trackBounds.height);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }


    private JButton createZeroButton()
    {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0,0));
        button.setMinimumSize(new Dimension(0,0));
        button.setMaximumSize(new Dimension(0,0));
        return button;
    }
}
