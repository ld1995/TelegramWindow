package overlays;

import components.GuiHelper;
import components.HintTextField;
import components.ImagePanel;
import components.TextEntry;
import org.javagram.dao.proxy.TelegramProxy;
import resources.Fonts;
import resources.Images;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AddContact extends JPanel {
    private JButton logout;
    private JButton addButton;
    private JTextField fast;
    private JTextField last;
    private JPanel rootPanel;
    private JFormattedTextField numberField;
    private JTextPane textHelp;
    private JTextPane text;
    private JPanel numberPanel;
    private JPanel phonePanel;

    private TelegramProxy telegramProxy;

    public AddContact() {
        GuiHelper.adjustTextPane(textHelp);
        numberField.setBorder(BorderFactory.createEmptyBorder());
        last.setBorder(BorderFactory.createEmptyBorder());
        fast.setBorder(BorderFactory.createEmptyBorder());
        if (last.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) last.getDocument()).setDocumentFilter(new TextEntry());
        if (fast.getDocument() instanceof AbstractDocument)
            ((AbstractDocument) fast.getDocument()).setDocumentFilter(new TextEntry());
//        HintTextField lastName = new HintTextField(this.last, "Имя", this.last.getCaretColor());
//        HintTextField fastName = new HintTextField(this.fast, "Фамилия", this.fast.getCaretColor());
        try {
            MaskFormatter maskFormatter = new MaskFormatter("+### (##) ###-##-##");
            maskFormatter.setPlaceholder(null);
            maskFormatter.setPlaceholderCharacter('.');
            numberField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = Color.black;
        g.setColor(GuiHelper.makeTransparent(color, 0.9f));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void createUIComponents() {
        rootPanel = this;
        phonePanel = new ImagePanel(Images.getPhone(), false, true, 0);
        HintTextField lastHint = new HintTextField("","Имя",false)
        {
            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        lastHint.setFont(Fonts.getOpenSansLight().deriveFont(0,28));
        last = lastHint;
        HintTextField fastHint = new HintTextField("","Фамилия", false)
        {
            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        fastHint.setFont(Fonts.getOpenSansLight().deriveFont(0,28));
        fast = fastHint;
    }

    public TelegramProxy getTelegramProxy() {
        return telegramProxy;
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
        if (telegramProxy != null) {
//            telegramProxy.
        }
    }

    public void addActionListenerForLogout(ActionListener listener) {
        logout.addActionListener(listener);
    }

    public void removeActionListenerForLogout(ActionListener listener) {
        logout.removeActionListener(listener);
    }

    public void addActionListenerForAddButton(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void removeActionListenerForAddButton(ActionListener listener) {
        addButton.removeActionListener(listener);
    }

}
