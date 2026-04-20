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

public class RootUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sqlCommand = request.getParameter("sqlCommand");
        StringBuilder resultHTML = new StringBuilder();

        Properties props = new Properties();
        String propPath = getServletContext()
                .getRealPath("/WEB-INF/conf/root.properties");

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
                // ── QUERY ──────────────────────────────────────────────────
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlCommand);
                resultHTML.append(buildHTMLTable(rs));
                rs.close();
                stmt.close();

            } else {
                // ── UPDATE / INSERT / DELETE / REPLACE ──────────────────────
                Statement stmt = conn.createStatement();
                int rows = stmt.executeUpdate(sqlCommand);
                stmt.close();

                resultHTML.append(
                    "<div style='background-color:green; color:red; padding:8px; "
                    + "display:inline-block; margin:4px;'>"
                    + "The statement executed successfully.<br>"
                    + rows + " row(s) affected.</div><br>");

                // ── BUSINESS LOGIC CHECK ────────────────────────────────────
                // Only triggered when the command touches the shipments table
                if (cmdLower.contains("shipment")) {
                    int updated = applyBusinessLogic(conn);
                    resultHTML.append(
                        "<div style='background-color:green; color:blue; padding:8px; "
                        + "display:inline-block; margin:4px; font-weight:bold;'>"
                        + "Business Logic Detected! – Updating Supplier Status<br>"
                        + "Business Logic updated " + updated
                        + " supplier status marks.</div>");
                } else {
                    resultHTML.append(
                        "<div style='background-color:green; color:blue; padding:8px; "
                        + "display:inline-block; margin:4px; font-weight:bold;'>"
                        + "Business Logic Not Triggered!</div>");
                }
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
                request.getRequestDispatcher("/Front-End-Pages/rootHome.jsp");
        rd.forward(request, response);
    }

    /**
     * NON-BONUS business logic:
     * Increments status by 5 for ALL suppliers who have ANY shipment
     * with quantity >= 100 (not just the ones directly in the command).
     */
    private int applyBusinessLogic(Connection conn) throws SQLException {
        String sql =
            "UPDATE suppliers SET status = status + 5 " +
            "WHERE snum IN " +
            "  (SELECT DISTINCT snum FROM shipments WHERE quantity >= 100)";
        Statement stmt = conn.createStatement();
        int count = stmt.executeUpdate(sql);
        stmt.close();
        return count;
    }

    /** Builds an HTML table from a ResultSet. */
    private String buildHTMLTable(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' style='border-collapse:collapse; "
                + "margin:8px auto;'><tr>");

        for (int i = 1; i <= cols; i++) {
            sb.append("<th style='background-color:red; color:yellow; "
                    + "padding:6px 10px; font-family:Arial;'>")
              .append(meta.getColumnName(i))
              .append("</th>");
        }
        sb.append("</tr>");

        while (rs.next()) {
            sb.append("<tr>");
            for (int i = 1; i <= cols; i++) {
                sb.append("<td style='padding:5px 10px; text-align:center; "
                        + "font-family:Arial; background-color:white; color:black;'>")
                  .append(rs.getString(i))
                  .append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
