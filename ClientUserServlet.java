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

public class ClientUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sqlCommand = request.getParameter("sqlCommand");
        StringBuilder resultHTML = new StringBuilder();

        Properties props = new Properties();
        String propPath = getServletContext()
                .getRealPath("/WEB-INF/conf/client.properties");

        Connection conn = null;
        try {
            props.load(new FileInputStream(propPath));
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));

            String cmdLower = sqlCommand.trim().toLowerCase();

            if (cmdLower.startsWith("select")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlCommand);
                resultHTML.append(buildHTMLTable(rs));
                rs.close();
                stmt.close();
            } else {
                // Client can attempt, but DB will reject if no privilege
                Statement stmt = conn.createStatement();
                int rows = stmt.executeUpdate(sqlCommand);
                stmt.close();
                resultHTML.append(
                    "<div style='background-color:green; color:red; padding:8px; "
                    + "display:inline-block; margin:4px;'>"
                    + "The statement executed successfully.<br>"
                    + rows + " row(s) affected.</div>");
                // No business logic for client-level users
            }

        } catch (Exception e) {
            resultHTML.append(
                "<div style='background-color:red; color:yellow; padding:8px; "
                + "display:inline-block; margin:4px;'>"
                + "Error executing the SQL statement:<br>"
                + e.getMessage() + "</div>");
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }

        request.setAttribute("results", resultHTML.toString());
        request.setAttribute("sqlCommand", sqlCommand);
        RequestDispatcher rd =
                request.getRequestDispatcher("/Front-End-Pages/clientHome.jsp");
        rd.forward(request, response);
    }

    private String buildHTMLTable(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' style='border-collapse:collapse; "
                + "margin:8px auto;'><tr>");
        for (int i = 1; i <= cols; i++) {
            sb.append("<th style='background-color:red; color:yellow; "
                    + "padding:6px 10px; font-family:Arial;'>")
              .append(meta.getColumnName(i)).append("</th>");
        }
        sb.append("</tr>");
        while (rs.next()) {
            sb.append("<tr>");
            for (int i = 1; i <= cols; i++) {
                sb.append("<td style='padding:5px 10px; text-align:center; "
                        + "font-family:Arial; background-color:white; color:black;'>")
                  .append(rs.getString(i)).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
