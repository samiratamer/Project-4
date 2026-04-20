/* Name: Samira Tamer 
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

public class AddSupplierServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String snum   = request.getParameter("snum");
        String sname  = request.getParameter("sname");
        String status = request.getParameter("status");
        String city   = request.getParameter("city");
        String resultHTML;

        Properties props = new Properties();
        String propPath = getServletContext().getRealPath("/WEB-INF/conf/dataentry.properties");
        props.load(new FileInputStream(propPath));

        Connection conn = null;
        try {
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
            );

            String sql = "INSERT INTO suppliers VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, snum);
            ps.setString(2, sname);
            ps.setInt(3, Integer.parseInt(status));
            ps.setString(4, city);
            ps.executeUpdate();
            ps.close();

            resultHTML = "<div style='background-color:blue; color:yellow; padding:5px;'>" +
                "New suppliers record: (" + snum + ", " + sname + ", " + status + ", " + city +
                ") - successfully entered into database." +
                "</div>";

        } catch (Exception e) {
            resultHTML = "<div style='background-color:red; color:yellow; padding:5px;'>" +
                "Error: " + e.getMessage() + "</div>";
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }

        request.setAttribute("results", resultHTML);
        RequestDispatcher rd = request.getRequestDispatcher("/Front-End-Pages/dataEntryHome.jsp");
        rd.forward(request, response);
    }
}
