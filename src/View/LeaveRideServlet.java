package View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Passenger;

/**
 * Servlet implementation class LeaveRideServlet
 */
@WebServlet("/LeaveRideServlet")
public class LeaveRideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveRideServlet() {
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
	    		//parameter and initialization
	        	Passenger user = (Passenger)session.getAttribute("User");
	    		//save userName for next page
	    		session.setAttribute("User", user);
	    		//delete the ride		
	    		user.leaveRide();
				out.println("<html>");
			    out.println("<head>");
			    out.println("<title>Servlet GreetingServlet</title>");
			    out.println("</head>");
			    out.println("<body>");
	        	response.sendRedirect("mainpage.jsp");

			} catch (Exception e) {

				//print the error
				String str = "<script>" + "alert('" + e.getMessage() + "')" + "</script>";
			    out.print(str);
	
			    
			} finally {
				out.println("</body>");
			    out.println("</html>");
				out.close();
			}
	    }


}
