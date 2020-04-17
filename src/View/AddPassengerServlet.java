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
 * Servlet implementation class AddPassengerServlet
 */
@WebServlet("/AddPassengerServlet")
public class AddPassengerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPassengerServlet() {
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

		try{
			int idGroup = Integer.parseInt(request.getParameter("idGroup"));
			Passenger pass = (Passenger)session.getAttribute("User");
			session.setAttribute("User", pass);
			boolean successJoin = pass.joinGroup(idGroup);

					 	//message for user
			out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Servlet GreetingServlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    
		    if(successJoin){ 
			    out.println("<script> alert('success, for more data go to tremp zone');");
			    out.println("window.location.href = \"mainpage.jsp\";");
			    out.println("</script>");
			}else{ 
			
		    out.println("<script> alert('you can't sign to this ride,\n try another ride');");
		    out.println("window.location.href = \"automaticSearch.jsp\";");
		    out.println("</script>");
			}
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
