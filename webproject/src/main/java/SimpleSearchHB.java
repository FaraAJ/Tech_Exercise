import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Employee;
import util.Info;
import util.UtilDB;

@WebServlet("/SimpleSearchHB")
public class SimpleSearchHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearchHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("nameKeyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<table border=\"1\">");
      out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Address</th></tr>");

      List<Employee> listEmployees = null;
      if (keyword != null && !keyword.isEmpty()) {
         listEmployees = UtilDB.listEmployees(keyword);
      } else {
         listEmployees = UtilDB.listEmployees();
      }
      display(listEmployees, out);
      out.println("</table>");
      out.println("<br><a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Employee> listEmployees, PrintWriter out) {
      for (Employee employee : listEmployees) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getEmail() + ", "
               + employee.getPhone() + ", "
               + employee.getAddress());

         out.println("<tr><td>" + employee.getId() + "</td>" +
                 "<td>" + employee.getName() + "</td>" +
                 "<td>" + employee.getEmail() + "</td>" +
                 "<td>" + employee.getPhone() + "</td>" +
                 "<td>" + employee.getAddress() + "</td></tr>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
