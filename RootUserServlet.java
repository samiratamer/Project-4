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

public class RootUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sqlCommand = request.getParameter("sqlCommand");
        StringBuilder resultHTML = new StringBuilder();

        Properties props = new Properties();
        String propPath = getServletContext().getRealPath("/WEB-INF/conf/root.properties");
        props.load(new FileInputStream(propPath));

        Connection conn = null;
        try {
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
            );

            String cmdLower = sqlCommand.trim().toLowerCase();

            if (cmdLower.startsWith("select")) {
                // Execute query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlCommand);
                resultHTML.append(buildTable(rs));
                rs.close(); stmt.close();

            } else {
                // It's an update/insert/delete/replace
                Statement stmt = conn.createStatement();
                int rows = stmt.executeUpdate(sqlCommand);
                resultHTML.append("<div style='background-color:green; color:red; padding:5px;'>");
                resultHTML.append("The statement executed successfully.<br>" + rows + " row(s) affected.");
                resultHTML.append("</div>");
                stmt.close();

                // Check if this command touches the shipments table
                // Business logic: if INSERT/UPDATE on shipments with quantity >= 100,
                // increment status by 5 for ALL suppliers who have any shipment with qty >= 100
                if (cmdLower.contains("shipment")) {
                    int updatedCount = applyBusinessLogic(conn);
                    if (updatedCount > 0) {
                        resultHTML.append("<div style='background-color:green; color:blue; padding:5px;'>");
                        resultHTML.append("Business Logic Detected! – Updating Supplier Status<br>");
                        resultHTML.append("Business Logic updated " + updatedCount + " supplier status marks.");
                        resultHTML.append("</div>");
                    } else {
                        resultHTML.append("<div style='background-color:green; color:blue; padding:5px;'>");
                        resultHTML.append("Business Logic Detected! – Updating Supplier Status<br>");
                        resultHTML.append("Business Logic updated 0 supplier status marks.");
                        resultHTML.append("</div>");
                    }
                } else {
                    resultHTML.append("<div style='background-color:green; color:blue; padding:5px;'>");
                    resultHTML.append("Business Logic Not Triggered!");
                    resultHTML.append("</div>");
                }
            }

        } catch (Exception e) {
            resultHTML.append("<div style='background-color:red; color:yellow; padding:5px;'>");
            resultHTML.append("Error executing the SQL statement:<br>" + e.getMessage());
            resultHTML.append("</div>");
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }

        request.setAttribute("results", resultHTML.toString());
        request.setAttribute("sqlCommand", sqlCommand);
        RequestDispatcher dispatcher =
            request.getRequestDispatcher("/Front-End-Pages/rootHome.jsp");
        dispatcher.forward(request, response);
    }

    // Non-bonus business logic: increment status by 5 for ALL suppliers
    // who have ANY shipment with quantity >= 100
    private int applyBusinessLogic(Connection conn) throws SQLException {
        String updateSQL =
            "UPDATE suppliers SET status = status + 5 " +
            "WHERE snum IN (SELECT DISTINCT snum FROM shipments WHERE quantity >= 100)";
        Statement stmt = conn.createStatement();
        int count = stmt.executeUpdate(updateSQL);
        stmt.close();
        return count;
    }

    // Helper: build an HTML table from a ResultSet
    private String buildTable(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        sb.append("<table border='1' style='border-collapse:collapse;'><tr>");
        for (int i = 1; i <= cols; i++) {
            sb.append("<th style='background-color:red; color:yellow; padding:5px;'>")
              .append(meta.getColumnName(i)).append("</th>");
        }
        sb.append("</tr>");

        while (rs.next()) {
            sb.append("<tr>");
            for (int i = 1; i <= cols; i++) {
                sb.append("<td style='padding:5px; text-align:center;'>")
                  .append(rs.getString(i)).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
