import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilDB;

@WebServlet("/SimpleDeleteHB")
public class SimpleDeleteHB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SimpleDeleteHB() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clear = request.getParameter("clear");

        if ("true".equals(clear)) {
            UtilDB.clearEmployees();
            UtilDB.resetEmployeeIdCounter();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Clear All</title></head><body>");
            out.println("<h1>All Employees Cleared Successfully!</h1>");
            out.println("<a href='/webproject/simpleDeleteHB.html'>Back to Delete Page</a>");
            out.println("</body></html>");
        } else {
            // Existing delete by ID logic here...
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                UtilDB.deleteEmployeeById(id);
                out.println("<h2>Employee with ID " + id + " deleted successfully.</h2>");
            } catch (NumberFormatException e) {
                out.println("<h2>Invalid ID format. Please enter a valid integer ID.</h2>");
            }
        } else {
            out.println("<h2>Employee ID is required.</h2>");
        }

        out.println("<br><a href=\"/webproject/simpleSearchHB.html\">Back to Search</a>");
    }

    
}
