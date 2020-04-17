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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	        Controller conn = new Controller();
			String type = request.getParameter("type");
			String first = request.getParameter("first");
			String last = request.getParameter("last");
			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");
			String mail = request.getParameter("mail");

			//sign up the new user & check validation
			int valid = conn.addNewUser(first, last, type, userName, psw, mail);
	        
			if(valid == 0){  // valid user
				User user = conn.getUser(userName);
				session.setAttribute("User", user);
				if(user instanceof Admin)  // for admin user
					response.sendRedirect("show_tables.jsp");
				response.sendRedirect("mainpage.jsp");  // for other users
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
		    
		} finally {
			out.print("<script >window.location.href = \"clear_page.jsp\";</script >");
			out.println("</body>");
		    out.println("</html>");
		    out.close();
		}
    
	}

}
