import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateStatus
 */
@WebServlet("/UpdateStatusServlet")
public class UpdateStatus extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestIdStr = request.getParameter("requestId");
        String newStatus = request.getParameter("newStatus");

        int requestId = Integer.parseInt(requestIdStr);

        String updateSQL = "UPDATE MaintenanceRequest SET status = ? WHERE requestId = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ApartmentDB", "root", "");

            PreparedStatement stmt = conn.prepareStatement(updateSQL);
            stmt.setString(1, newStatus);
            stmt.setInt(2, requestId);

            int rowsUpdated = stmt.executeUpdate();

            stmt.close();
            conn.close();

            response.sendRedirect("BrowseRequests");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}