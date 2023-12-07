package servlets;

import java.time.LocalDate;

public class Person
{
    public Person(final String name, final LocalDate birthDate, final String address, final String phoneNumber)
    {
        setName(name);
        setBirthDate(birthDate);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    public String getName()
    {
        return name_;
    }
    public void setName(final String name)
    {
        name_ = name;
    }

    public LocalDate getBirthDate()
    {
        return birthDate_;
    }

    public void setBirthDate(final LocalDate birthDate)
    {
        birthDate_ = birthDate;
    }

    public String getAddress()
    {
        return address_;
    }

    public void setAddress(final String address)
    {
        address_ = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber_;
    }

    public void setPhoneNumber(final String phoneNumber)
    {
        phoneNumber_ = phoneNumber;
    }

    @Override
    public String toString()
    {
        return "<tr>" +
                "<td>" + name_ + "</td>" +
                "<td>" + birthDate_ + "</td>" +
                "<td>" + address_ + "</td>" +
                "<td>" + phoneNumber_ + "</td>" +
                "</tr>";
    }

    private String name_;
    private LocalDate birthDate_;
    private String address_;
    private String phoneNumber_;
}