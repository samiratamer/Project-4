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

public class AddShipmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String snum     = request.getParameter("snum");
        String pnum     = request.getParameter("pnum");
        String jnum     = request.getParameter("jnum");
        String quantity = request.getParameter("quantity");
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

            int qty = Integer.parseInt(quantity.trim());

            // MUST use PreparedStatement – no exceptions
            String sql = "INSERT INTO shipments (snum, pnum, jnum, quantity) "
                       + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, snum);
            ps.setString(2, pnum);
            ps.setString(3, jnum);
            ps.setInt(4, qty);
            ps.executeUpdate();
            ps.close();

            // Business logic: if quantity >= 100, update supplier statuses
            String blMsg = "";
            if (qty >= 100) {
                String updateSQL =
                    "UPDATE suppliers SET status = status + 5 " +
                    "WHERE snum IN " +
                    "  (SELECT DISTINCT snum FROM shipments WHERE quantity >= 100)";
                Statement stmt2 = conn.createStatement();
                stmt2.executeUpdate(updateSQL);
                stmt2.close();
                blMsg = " Business logic triggered.";
            }

            resultHTML = "<div style='background-color:blue; color:yellow; padding:8px; "
                    + "display:inline-block; font-family:Arial;'>"
                    + "New shipments record: (" + snum + ", " + pnum + ", "
                    + jnum + ", " + quantity
                    + ") - successfully entered into database." + blMsg
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
