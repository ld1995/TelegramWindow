package by.ld1995tut;

import by.ld1995tut.Frame.Frame;
import org.javagram.dao.ApiBridgeTelegramDAO;
import org.javagram.dao.DebugTelegramDAO;
import org.javagram.dao.TelegramDAO;
import javax.swing.*;
import java.awt.event.WindowEvent;

public class Loader
{
    public static void main(String...args) throws Exception
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    TelegramDAO telegramDAO = new
                            //DebugTelegramDAO();
                            ApiBridgeTelegramDAO("149.154.167.50:443",23019,"8b256851da8846c10cdaff738117bc3c");
                    Frame frame = new Frame(telegramDAO);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
}
