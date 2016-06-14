package by.ld1995tut.mics;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Reg extends DocumentFilter
{
    public Reg()
    {

    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {
        string.trim().replaceAll("[^A-ZА-ЯЁa-zа-яё]","");
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text.trim().replaceAll("[^A-ZА-ЯЁa-zа-яё]","");
        super.replace(fb, offset, length, text, attrs);
    }
}
