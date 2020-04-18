package View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Model.Admin;
import Model.Driver;
import Model.Passenger;
import Model.User;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

        try {
        	//get parameters from form
	        String username = request.getParameter("userName").toString();
	        String password = request.getParameter("psw").toString();
	        Controller conn = new Controller();
	        		//validation- check password and type
					if(conn.checkPassword(username, password)) {  // for valid password
						User user = conn.getUser(username);
						session.setAttribute("User", user);
						if(user instanceof Passenger || user instanceof Driver)
							response.sendRedirect("mainpage.jsp");
						else {  // for admin
							response.sendRedirect("show_tables.jsp");
						}
					}else { // wrong password or username
						throw new Exception("invalid password or Username");
					}
		} catch (Exception e) {
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    
		    //show error
			String str = "<script>" + "alert('" + e.getMessage() + "')" + "</script>";
		    out.print(str);
			out.print("<script >window.location.href = \"clear_page.jsp\";</script >");
			out.println("</body>");
		    out.println("</html>");
		    
		} finally {

		    out.close();
		}
    }
}
