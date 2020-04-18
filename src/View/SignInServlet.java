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
import Model.User;

/**
 * Servlet implementation class SignInServlet:
 * 
 * the servlet check if the user is valid
 * create session for the user
 * and send the user to mainpage or show_tables depend on kind of the user
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
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
        	//Sign up - get data for the new user
        	//get parameters from form
	        Controller conn = new Controller();
			String type = request.getParameter("type");
			String first = request.getParameter("first");
			String last = request.getParameter("last");
			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");
			String mail = request.getParameter("mail");

			//check valid username 
			int valid = conn.addNewUser(first, last, type, userName, psw, mail);
	        
			if(valid == 0){  // if user is valid 
				User user = conn.getUser(userName);
				session.setAttribute("User", user);
				
				if(user instanceof Admin)  // redirect to show_tables(admin main) for admin user
					response.sendRedirect("show_tables.jsp");
				response.sendRedirect("mainpage.jsp");  // for other users send to mainpage
			}else {
				throw new Exception("invalid user name, the name is already in use");
			}
		} catch (Exception e) {
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    
		    //print the error
			String str = "<script>" + "alert('" + e.getMessage() + "')" + "</script>";
		    out.print(str);
			out.print("<script >window.location.href = \"index.jsp\";</script >");
			out.println("</body>");
		    out.println("</html>");		    
		} finally {

		    out.close();
		}
    
	}

}
