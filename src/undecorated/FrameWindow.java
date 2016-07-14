package undecorated;

import resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FrameWindow extends JPanel {
    private JPanel rootPanel;
    private JButton turn;
    private JButton exit;
    private JPanel systemPanel;
    private JPanel inputPanel;

    private ComponentMover componentMover;
    private ComponentResizerAbstract componentResizerExtended;
    private String title;

    public static final int CLOSE_BUTTON = 1, MINIMIZE_BUTTON = 4;
    public static final int BIND_CLOSE_BUTTON = 16, BIND_MINIMIZE_BUTTON = 64;

    public static final int MINIMIZE_CLOSE_BUTTONS = CLOSE_BUTTON | MINIMIZE_BUTTON;
    public static final int NO_BUTTON = 0;
    public static final int BIND_MINIMIZE_CLOSE_BUTTONS = BIND_CLOSE_BUTTON | BIND_MINIMIZE_BUTTON;

    public static final int WINDOW_DEFAULT_RESIZE_POLICY = ComponentResizerAbstract.KEEP_RATIO_CENTER;
    public static final int FRAME_DEFAULT_RESIZE_POLICY = ComponentResizerAbstract.SIMPLE;
    public static final int DIALOG_DEFAULT_RESIZE_POLICY = -1;

    public static final int WINDOW_DEFAULT_BUTTONS = NO_BUTTON;
    public static final int FRAME_DEFAULT_BUTTONS = MINIMIZE_CLOSE_BUTTONS | BIND_MINIMIZE_CLOSE_BUTTONS;
    public static final int DIALOG_DEFAULT_BUTTONS = CLOSE_BUTTON | BIND_MINIMIZE_BUTTON;

    public FrameWindow(Window window, int resizePolicy, int buttons) {
        init(window, resizePolicy, buttons);
        initWindow();
        initContent(window);
        initValidation();
    }

    public FrameWindow(Window window, int resizePolicy) {
        this(window, resizePolicy, WINDOW_DEFAULT_BUTTONS);
    }

    public FrameWindow(Window window) {
        this(window, WINDOW_DEFAULT_RESIZE_POLICY);
    }


    public FrameWindow(Frame window, int resizePolicy, int buttons) {
        init(window, resizePolicy, buttons);
        initFrame(window, buttons);
        initContent(window);
        initValidation();
    }

    public FrameWindow(Frame window, int resizePolicy) {
        this(window, resizePolicy, FRAME_DEFAULT_BUTTONS);
    }

    public FrameWindow(Frame window) {
        this(window, FRAME_DEFAULT_RESIZE_POLICY);
    }

    public FrameWindow(Dialog window, int resizePolicy, int buttons) {
        init(window, resizePolicy, buttons);
        initDialog(window);
        initContent(window);
        initValidation();
    }

    public FrameWindow(Dialog window, int resizePolicy) {
        this(window, resizePolicy, DIALOG_DEFAULT_BUTTONS);
    }

    public FrameWindow(Dialog window) {
        this(window, DIALOG_DEFAULT_RESIZE_POLICY);
    }

    public FrameWindow(JWindow window, int resizePolicy, int buttons) {
        init(window, resizePolicy, buttons);
        initWindow();
        initContentPane(window);
        initValidation();
    }

    public FrameWindow(JWindow window, int resizePolicy) {
        this(window, resizePolicy, WINDOW_DEFAULT_BUTTONS);
    }

    public FrameWindow(JWindow window) {
        this(window, WINDOW_DEFAULT_RESIZE_POLICY);
    }

    public FrameWindow(JFrame window, int resizePolicy, int buttons) {
        init(window, resizePolicy, buttons);
        initFrame(window, buttons);
        initContentPane(window);
        initValidation();
    }

    public FrameWindow(JFrame window, int resizePolicy) {
        this(window, resizePolicy, FRAME_DEFAULT_BUTTONS);
    }

    public FrameWindow(JFrame window) {
        this(window, FRAME_DEFAULT_RESIZE_POLICY);
    }

    public FrameWindow(JDialog window, int resizePolicy, int buttons) {
        init(window, resizePolicy, buttons);
        initDialog(window);
        initContentPane(window);
        initValidation();

    }

    public FrameWindow(JDialog window, int resizePolicy) {
        this(window, resizePolicy, DIALOG_DEFAULT_BUTTONS);
    }

    public FrameWindow(JDialog window) {
        this(window, DIALOG_DEFAULT_RESIZE_POLICY);
    }

    private void init(Window window, int resizePolicy, int buttons) {

        setPreferredSize(null);
        setMinimumSize(null);
        setMaximumSize(null);

        if ((buttons & CLOSE_BUTTON) == 0)
            systemPanel.remove(exit);
        else if ((buttons & BIND_CLOSE_BUTTON) != 0)
            addActionListenerForClose(e -> window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)));

        if ((buttons & MINIMIZE_BUTTON) == 0)
            systemPanel.remove(turn);


        componentMover = new ComponentMover(window, systemPanel);

        if (resizePolicy < 0)
            return;

        componentResizerExtended = new ComponentResizerAbstract(resizePolicy, window) {

            @Override
            protected int getExtraHeight() {
                return rootPanel.getHeight() - inputPanel.getHeight();
            }

            @Override
            protected int getExtraWidth() {
                return rootPanel.getWidth() - inputPanel.getWidth();
            }
        };

    }

    private void initWindow() {
        title = "";

    }

    private void initFrame(Frame window, int buttons) {
        title = window.getTitle();
        if ((buttons & MINIMIZE_BUTTON) != 0 && (buttons & BIND_MINIMIZE_BUTTON) != 0) {
            addActionListenerForMinimize(e -> window.setState(Frame.ICONIFIED));
        }
        window.setUndecorated(true);
    }

    private void initDialog(Dialog window) {
        title = window.getTitle();
        window.setUndecorated(true);
    }

    private void initContent(Window window) {
        window.removeAll();
        window.setLayout(new BorderLayout());
        window.add(this, BorderLayout.CENTER);
    }

    private void initContentPane(RootPaneContainer window) {
        window.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setContentPanel(window.getContentPane());
        window.setContentPane(this);
    }

    private void initValidation() {
        revalidate();
        repaint();
    }

    public void setContentPanel(Component component) {
        this.inputPanel.removeAll();
        this.inputPanel.add(component);
        this.inputPanel.revalidate();
        this.inputPanel.repaint();
    }

    public void addActionListenerForClose(ActionListener listener) {
        exit.addActionListener(listener);
    }

    public void addActionListenerForMinimize(ActionListener listener) {
        turn.addActionListener(listener);
    }

    public void removeActionListenerForClose(ActionListener listener) {
        exit.removeActionListener(listener);
    }

    public void removeActionListenerForMinimize(ActionListener listener) {
        turn.removeActionListener(listener);
    }


    private void createUIComponents() {
        rootPanel = this;
        systemPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Font font = getFont();
                g.setFont(font);

                FontMetrics fontMetrics = g.getFontMetrics();
                int height = fontMetrics.getAscent();
                int pos = (this.getHeight() + height) / 2;

                int start = 4;
                int end = this.getHeight();
                for (Component component : getComponents()) {
                    if (component.getX() < end) {
                        end = component.getX();
                    }
                }
                end -= 4;
                String text = title;

                g.setColor(Color.black);
                g.setFont(g.getFont().deriveFont(Font.BOLD));
                g.drawString(text, start, pos);
            }
        };
        turn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Images.getIconHide(), 0, 0, null);
            }
        };
        exit = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Images.getIconClose(), 0, 0, null);
            }
        };
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getTurn() {
        return turn;
    }

    public JButton getExit() {
        return exit;
    }

    public JPanel getSystemPanel() {
        return systemPanel;
    }

    public static int showDialog(Frame frame, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
        return showDialog(new JDialog(frame, title, true), message, optionType, messageType, icon, options, initialValue);
    }

    public static int showDialog(Dialog dialog, Object message, String title, int optionType, int messageType,
                                 Icon icon, Object[] options, Object initialValue) {
        return showDialog(new JDialog(dialog, title, true), message, optionType, messageType, icon, options, initialValue);
    }

    public static int showDialog(Frame frame, Object message, String title, int optionType, int messageType, Icon icon) {
        return showDialog(new JDialog(frame, title, true), message, optionType, messageType, icon, null, null);
    }

    public static int showDialog(Dialog dialog, Object message, String title, int optionType, int messageType, Icon icon) {
        return showDialog(new JDialog(dialog, title, true), message, optionType, messageType, icon, null, null);
    }

    public static int showDialog(Frame frame, Object message, String title, int optionType, int messageType) {
        return showDialog(new JDialog(frame, title, true), message, optionType, messageType, null, null, null);
    }

    public static int showDialog(Dialog dialog, Object message, String title, int optionType, int messageType) {
        return showDialog(new JDialog(dialog, title, true), message, optionType, messageType, null, null, null);
    }

    private static int showDialog(JDialog dialog, Object message, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
        JOptionPane optionPane = new JOptionPane(message, optionType, messageType, icon, options, initialValue);
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        new FrameWindow(dialog);
        dialog.pack();
        dialog.setLocationRelativeTo(dialog.getParent());
        PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                dialog.setVisible(false);
            }
        };
        optionPane.addPropertyChangeListener("value", propertyChangeListener);
        dialog.setVisible(true);
        optionPane.removePropertyChangeListener("value", propertyChangeListener);
        Object selectedValue = optionPane.getValue();
        if (selectedValue == null)
            return JOptionPane.CLOSED_OPTION;

        if (options == null) {
            if (selectedValue instanceof Integer)
                return ((Integer) selectedValue);
            else
                return JOptionPane.CLOSED_OPTION;
        }
        for (int counter = 0, maxCounter = options.length; counter < maxCounter; counter++) {
            if (options[counter].equals(selectedValue))
                return counter;
        }
        return JOptionPane.CLOSED_OPTION;
    }

}
