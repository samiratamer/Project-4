/* Name:Samira Tamer
   Course: CNT 4714 – Spring 2026 – Project Four
   Assignment title: A Three-Tier Distributed Web-Based Application
   Date: April 27, 2026
*/
package project4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class AddPartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pnum   = request.getParameter("pnum");
        String pname  = request.getParameter("pname");
        String color  = request.getParameter("color");
        String weight = request.getParameter("weight");
        String city   = request.getParameter("city");
        String resultHTML;

        Properties props = new Properties();
        String propPath = getServletContext()
                .getRealPath("/WEB-INF/conf/dataentry.properties");

        Connection conn = null;
        try {
            props.load(new FileInputStream(propPath));
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));

            // MUST use PreparedStatement – no exceptions
            String sql = "INSERT INTO parts (pnum, pname, color, weight, city) "
                       + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pnum);
            ps.setString(2, pname);
            ps.setString(3, color);
            ps.setDouble(4, Double.parseDouble(weight.trim()));
            ps.setString(5, city);
            ps.executeUpdate();
            ps.close();

            resultHTML = "<div style='background-color:blue; color:yellow; padding:8px; "
                    + "display:inline-block; font-family:Arial;'>"
                    + "New parts record: (" + pnum + ", " + pname + ", " + color
                    + ", " + weight + ", " + city
                    + ") - successfully entered into database."
                    + "</div>";

        } catch (Exception e) {
            resultHTML = "<div style='background-color:red; color:yellow; padding:8px; "
                    + "display:inline-block; font-family:Arial;'>"
                    + "Error: " + e.getMessage() + "</div>";
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }

        request.setAttribute("results", resultHTML);
        RequestDispatcher rd =
                request.getRequestDispatcher("/Front-End-Pages/dataEntryHome.jsp");
        rd.forward(request, response);
    }
}
