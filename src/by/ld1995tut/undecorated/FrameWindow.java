package by.ld1995tut.undecorated;

import by.ld1995tut.components.ImageButton;
import resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FrameWindow extends JPanel {
    private JPanel rootPanel;
    private JButton turn;
    private JButton exit;
    private JPanel systemPanel;
    private JPanel inputPanel;

    private ComponentMover componentMover;
    private ComponentResizerAbstract componentResizerExtended;

    public static final int CLOSE_BUTTON = 1, MINIMIZE_BUTTON = 4;
    public static final int BIND_CLOSE_BUTTON = 16, BIND_MINIMIZE_BUTTON = 64;

    public static final int MINIMIZE_CLOSE_BUTTONS = CLOSE_BUTTON | MINIMIZE_BUTTON;
    public static final int NO_BUTTONS = 0;
    public static final int BIND_MINIMIZE_CLOSE_BUTTONS = BIND_CLOSE_BUTTON | BIND_MINIMIZE_BUTTON;

    public static final int WINDOW_DEFAULT_RESIZE_POLICY = ComponentResizerAbstract.KEEP_RATIO_CENTER;
    public static final int FRAME_DEFAULT_RESIZE_POLICY = ComponentResizerAbstract.SIMPLE;
    public static final int DIALOG_DEFAULT_RESIZE_POLICY = -1;

    public static final int WINDOW_DEFAULT_BUTTONS = NO_BUTTONS;
    public static final int FRAME_DEFAULT_BUTTONS= MINIMIZE_CLOSE_BUTTONS | BIND_MINIMIZE_CLOSE_BUTTONS;
    public static final int DIALOG_DEFAULT_BUTTONS = CLOSE_BUTTON | BIND_CLOSE_BUTTON;

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

    }

    private void initFrame(Frame window, int buttons) {
        if ((buttons & MINIMIZE_BUTTON) != 0 && (buttons & BIND_MINIMIZE_BUTTON) != 0) {
            addActionListenerForMinimize(e -> window.setState(Frame.ICONIFIED));
        }
        window.setUndecorated(true);
    }

    private void initDialog(Dialog window) {
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
        turn = new ImageButton(Images.getIconHide(),true,null,true);
        exit = new ImageButton(Images.getIconClose(),true,null,true);
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
}