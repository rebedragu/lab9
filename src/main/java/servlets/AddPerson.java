package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/AddPerson")
public class AddPerson extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public AddPerson()
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String monthFilter = request.getParameter("monthFilter");
        if (monthFilter.isBlank())
        {
            generatePersonsTable(response, personSet_);
            return;
        }
        int selectedMonth = Integer.parseInt(monthFilter);
        Set<Person> filteredPersons = personSet_.stream()
                .filter(person -> person.getBirthDate().getMonthValue() == selectedMonth)
                .collect(Collectors.toSet());
        generatePersonsTable(response, filteredPersons.isEmpty() ? personSet_ : filteredPersons);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (request.getParameter(SUBMIT) == null)
        {
            return;
        }
        addPersonToSet(request);
        generatePersonsTable(response, personSet_);
    }

    private void addPersonToSet(final HttpServletRequest request)
    {
        String name = request.getParameter(NAME);
        LocalDate birthDate = LocalDate.of(Integer.parseInt(request.getParameter(YEAR)),
                Integer.parseInt(request.getParameter(MONTH)), Integer.parseInt(request.getParameter(DAY)));
        String address = request.getParameter(ADDRESS);
        String phoneNumber = request.getParameter(PHONE_NUMBER);
        personSet_.add(new Person(name, birthDate, address, phoneNumber));
    }

    private void generatePersonsTable(final HttpServletResponse response, final Set<Person> persons)
    {
        try
        {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(PAGE_START);
            for (Person person : persons)
            {
                printWriter.println(person);
            }
            printWriter.println(PAGE_END);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Set<Person> personSet_ = new HashSet<>();
    private final static String SUBMIT = "submit";
    private final static String NAME = "name";
    private final static String DAY = "day";
    private final static String MONTH = "month";
    private final static String YEAR = "year";
    private final static String ADDRESS = "address";
    private final static String PHONE_NUMBER = "phone";

    private final static String PAGE_START = "<!DOCTYPE html>\n" +
            "<html lang='en'>\n" +
            "<head>\n" +
            "<meta charset='UTF-8'>\n" +
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
            "<title>Persons</title>\n" +
            "<style>\n" +
            "body {\n" +
            "    font-family: Arial, sans-serif;\n" +
            "    background-color: #f4f4f4;\n" +
            "    margin: 0;\n" +
            "    padding: 0;\n" +
            "}\n" +
            "table {\n" +
            "    width: 50%;\n" +
            "    margin: 20px auto;\n" +
            "    border-collapse: collapse;\n" +
            "    background-color: #fff;\n" +
            "}\n" +
            "th, td {\n" +
            "    border: 1px solid #dddddd;\n" +
            "    text-align: left;\n" +
            "    padding: 12px;\n" +
            "}\n" +
            "th {\n" +
            "    background-color: #4CAF50;\n" +
            "    color: white;\n" +
            "}\n" +
            "tr:nth-child(even) {\n" +
            "    background-color: #f9f9f9;\n" +
            "}\n" +
            ".filter-container {\n" +
            "    text-align: center;\n" +
            "    margin-bottom: 20px;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class='filter-container'>\n" +
            "    <form method='GET'>\n" +
            "        <label for='monthFilter'>Filter by Month:</label>\n" +
            "        <select id='monthFilter' name='monthFilter'>\n" +
            "            <option value=''>-- Select Month --</option>\n" +
            "            <option value='1'>January</option>\n" +
            "            <option value='2'>February</option>\n" +
            "            <option value='3'>March</option>\n" +
            "            <option value='4'>April</option>\n" +
            "            <option value='5'>May</option>\n" +
            "            <option value='6'>June</option>\n" +
            "            <option value='7'>July</option>\n" +
            "            <option value='8'>August</option>\n" +
            "            <option value='9'>September</option>\n" +
            "            <option value='10'>October</option>\n" +
            "            <option value='11'>November</option>\n" +
            "            <option value='12'>December</option>\n" +
            "        </select>\n" +
            "        <button type='submit' name='filter'>Filter</button>\n" +
            "    </form>\n" +
            "</div>\n" +
            "<table>\n" +
            "<tr>\n" +
            "<th>Name</th>\n" +
            "<th>Birth Date</th>\n" +
            "<th>Address</th>\n" +
            "<th>Phone Number</th>\n" +
            "</tr>\n";
    private final static String PAGE_END = "</table>\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";
}