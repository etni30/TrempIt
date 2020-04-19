package View;
//admin method adding a new station
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Admin;

/**
 * Servlet implementation class addStationServlet
 */
@WebServlet("/addStationServlet")
public class addStationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStationServlet() {
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
			//get parameters f
			Admin admin = (Admin)session.getAttribute("User");
			session.setAttribute("User", admin);
			String station = request.getParameter("stationName");
			String city = request.getParameter("city");
			
			//add a new station method
			admin.addStation(station, city);
			
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
		out.println("<script> alert('insert number please');");
	    out.println("window.location.href = \"show_tables.jsp\";");
	    out.println("</script>");
	}catch(Exception e) {
	    //show error
		String str = "<script>" + "alert('duplicate entry for station name')" + "</script>";
	    out.print(str);
	    out.println("<script> window.location.href = \"show_tables.jsp\";</script>");
	}finally {
		out.println("</body>");
	    out.println("</html>");
	    out.close();
		
	}
	
	}

}
