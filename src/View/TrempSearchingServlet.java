package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.*;
import Model.Time;
import Model.User;
import Model.Path;
/**
 * Servlet implementation class TrempSearchingServlet
 */
@WebServlet("/TrempSearchingServlet")
public class TrempSearchingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrempSearchingServlet() {
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
				
				
		//get data from form
		//get city and station for source and destination
		String[] src = request.getParameter("Origin").toString().split(",");  // source
		String srcStation = src[0];
		String srcCity = src[1];
		String[] dst = request.getParameter("destination").toString().split(",");  // destination
		String dstStation = dst[0];
	 	String dstCity = dst[1];
		//get departure and arival time
	 	String  timeDep = request.getParameter("departureT");
	 	String timeAR = request.getParameter("desiredArriveT");
		try{
			Time desiredArriveT = new Time(timeAR);
			Time departureT = new Time(timeDep);
			//get prefer result from the user
			Integer prefer =  Integer.parseInt(request.getParameter("priority"));
			
			Algorithms alg = new Algorithms();

			//parameter and initialization
			User user = (User)session.getAttribute("User");			
			//save userName for next page
			session.setAttribute("User", user);
			
			//use optimal path algoritm to find the best ride
			ArrayList<Path> pathResult;
			pathResult= alg.findTramps(srcStation, srcCity, dstStation, dstCity, desiredArriveT, departureT, prefer);			
			session.setAttribute("PathResult", pathResult);

		 	//message for user
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<script> window.location.href = \"tremp_searching.jsp\";</script>");
		    
	}catch(NullPointerException e){
	    out.println("<script> alert('connection has lost');");
	    out.println("window.location.href = \"clear_page.jsp\";");
	    out.println("</script>");
	}catch(Exception e) {
	    out.println("<script> alert('problem with dB');");
	    out.println("window.location.href = \"clear_page.jsp\";");
	    out.println("</script>");
	}finally {
		out.println("</body>");
	    out.println("</html>");
	    out.close();
		}
	}

}
