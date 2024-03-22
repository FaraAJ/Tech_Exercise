import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDB;

@WebServlet("/SimpleInsertHB")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String address = request.getParameter("address");

      name = (name != null) ? name.trim() : "";
      email = (email != null) ? email.trim() : "";
      phone = (phone != null) ? phone.trim() : "";
      address = (address != null) ? address.trim() : "";

      UtilDB.createEmployees(name, email, phone, address);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
      out.println(docType + "<html>\n<head><title>" + title + "</title></head>\n<body bgcolor=\"#f0f0f0\">\n<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> Name: " + escapeHtml(name));
      out.println("<li> Email: " + escapeHtml(email));
      out.println("<li> Phone: " + escapeHtml(phone));
      out.println("<li> Address: " + escapeHtml(address));
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
   
   protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String idParam = request.getParameter("id");

	      if (idParam == null || idParam.isEmpty()) {
	         response.getWriter().println("ID parameter is required for deletion.");
	         return;
	      }

	      try {
	         int id = Integer.parseInt(idParam);
	         UtilDB.deleteEmployeeById(id);
	         response.getWriter().println("Employee with ID " + id + " has been deleted.");
	      } catch (NumberFormatException e) {
	         response.getWriter().println("Invalid ID format.");
	      }
	   }

   // HTML escape method
   private String escapeHtml(String input) {
      return input.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
   }
}
