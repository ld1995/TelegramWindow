package by.ld1995tut;

import java.util.ArrayList;

public class Person
{
    private String number;
    private char[] code;
    private String fastName;
    private String lastName;
    private ArrayList<Person> contactsList;

    public Person()
    {

    }

    public Person(String number) {
        this.number = number;
    }

    public Person(char[] code)
    {
        this.code = code;
    }

    public Person(String fastName, String lastName)
    {
        this.fastName = fastName;
        this.lastName = lastName;
    }


    //=============================================================

    public String getFastName() {
        return fastName;
    }

    public void setFastName(String fastName) {
        this.fastName = fastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNamber() {
        return number;
    }

    public void setNamber(String number) {
        this.number = number;
    }

    public char[] getCode() {
        return code;
    }

    public void setCode(char[] code) {
        this.code = code;
    }

    public ArrayList<Person> getContactsList() {
        return contactsList;
    }

    public void setContactsList(ArrayList<Person> contactsList)
    {
        this.contactsList.addAll(contactsList);
    }

    //=============================================================

    public boolean hasControlNumber()
    {
        return !number.trim().isEmpty();
    }

    public boolean hasControlCod()
    {
        return code.length>3;
    }

    public boolean hasControlReg()
    {
        return (!getFastName().trim().isEmpty() && !getLastName().trim().isEmpty());
    }
}
