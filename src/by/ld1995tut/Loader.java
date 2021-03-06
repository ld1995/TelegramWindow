package by.ld1995tut;

import by.ld1995tut.Frame.Frame;
import org.javagram.dao.ApiBridgeTelegramDAO;
import org.javagram.dao.TelegramDAO;
import resources.Config;

import javax.swing.*;

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
                            ApiBridgeTelegramDAO(Config.SERVER,Config.APP_ID, Config.APP_HASH);
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
