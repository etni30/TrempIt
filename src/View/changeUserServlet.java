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
import Model.Model;
import Model.User;

/**
 * Servlet implementation class changeUserServlet
 */
@WebServlet("/changeUserServlet")
public class changeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeUserServlet() {
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
		
		try{
			Controller conn = new Controller();
			
			//get parameters f
			Admin admin = (Admin)session.getAttribute("User");
			session.setAttribute("User", admin);
			String us = request.getParameter("User");
			User user = conn.getUser(us);  // get the user that we want to change
			String UserName = request.getParameter("UserName");
			String FirstName = request.getParameter("FirstName");
			Boolean IsInARide = Boolean.parseBoolean(request.getParameter("IsInARide"));
			String LastName = request.getParameter("LastName");
			String Password = request.getParameter("Password");
			String Email = request.getParameter("Email");

			//Change user details
			user.setUserName(UserName);
			user.setFirstName(FirstName);
			user.setIsInARide(IsInARide);
			user.setLastName(LastName);
			user.setPassword(Password);
			user.setEmail(Email);
			user.updateDB();
			
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
			response.sendRedirect("show_tables.jsp");

	
	}catch(NullPointerException e){
	    out.println("<script> alert('connection has lost');");
	    out.println("window.location.href = \"clear_page.jsp\";");
	    out.println("</script>");
	}catch(NumberFormatException e) {
		out.println("<script> alert('Division by 0 Exception');");
	    out.println("window.location.href = \"clear_page.jsp\";");
	    out.println("</script>");
	}catch(Exception e) {
		String str = "<script>" + "alert('" + e.getMessage() + "')" + "</script>";
		out.print(str);
	    out.println("<script> window.location.href = \"show_tables.jsp\";</script >");
	}finally {
		out.println("</body>");
	    out.println("</html>");
	    out.close();
		
	}
	
	}

}
