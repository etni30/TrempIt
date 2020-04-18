package View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Driver;

/**
 * Servlet implementation class AddNewTrempServlet
 */
@WebServlet("/AddNewTrempServlet")
public class AddNewTrempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewTrempServlet() {
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
			//parameter and initialization
			Driver user = (Driver)session.getAttribute("User");
			
			//save userName for next page
			session.setAttribute("User", user);
			
			//get data from form
			//get city and station for source and destination
			String[] src = request.getParameter("Origin").toString().split(",");  // source
			String srcStation = src[0];
			String srcCity = src[1];
			String[] dst = request.getParameter("destination").toString().split(",");  // destination
			String dstStation = dst[0];
		 	String dstCity = dst[1];
		 	//get departure time
		 	String  timeDep = request.getParameter("departureT");
		 	
		 	//message for user
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    

			//create a new ride
			user.addRide(timeDep, srcStation, srcCity, dstStation, dstCity);
			
			
		    out.println("<script> alert('success, for more data go to TrempBox');");
		    out.println("window.location.href = \"mainpage.jsp\";");
		    out.println("</script>");
		    
// if time expired or someone tried to get access without permission		    
	}catch(NullPointerException e){
	    out.println("<script> alert('connection has lost');");
	    out.println("window.location.href = \"clear_page.jsp\";");
	    out.println("</script>");
	}catch(Exception e) {
	    //show error
		String str = "<script>" + "alert('" + e.getMessage() + "')" + "</script>";
	    out.print(str);
	    out.println("<script> window.location.href = \"clear_page.jsp\";</script>");
	}finally {
		out.println("</body>");
	    out.println("</html>");
	    out.close();
		}
	}
}
	


