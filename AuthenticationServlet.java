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

public class AuthenticationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Properties props = new Properties();
        String propPath = getServletContext()
                .getRealPath("/WEB-INF/conf/systemapp.properties");

        Connection conn = null;
        try {
            props.load(new FileInputStream(propPath));
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));

            String sql = "SELECT * FROM usercredentials WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Credentials matched – redirect based on who logged in
                if (username.equals("root")) {
                    response.sendRedirect("Front-End-Pages/rootHome.jsp");
                } else if (username.equals("client")) {
                    response.sendRedirect("Front-End-Pages/clientHome.jsp");
                } else if (username.equals("dataentry")) {
                    response.sendRedirect("Front-End-Pages/dataEntryHome.jsp");
                } else if (username.equals("theaccountant")) {
                    response.sendRedirect("Front-End-Pages/accountantHome.jsp");
                } else {
                    response.sendRedirect("Front-End-Pages/errorpage.html");
                }
            } else {
                response.sendRedirect("Front-End-Pages/errorpage.html");
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Front-End-Pages/errorpage.html");
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }
    }
}
