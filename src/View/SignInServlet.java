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
import Model.User;

/**
 * Servlet implementation class SignInServlet
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
        	//validation- check password and type
	        Controller conn = new Controller();
			String type = request.getParameter("type");
			String first = request.getParameter("first");
			String last = request.getParameter("last");
			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");


			int valid = conn.addNewUser(first, last, type, userName, psw, "test@gmail.com");
	        
			if(valid == 0){
				User user = conn.getUser(userName);
				session.setAttribute("User", user);
				response.sendRedirect("mainpage.jsp");
			}else {
				throw new Exception("invalid user name, the name is already in use");
			}
		} catch (Exception e) {
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    
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
