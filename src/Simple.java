import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Simple extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String p = request.getParameter("userPass");
		String n = request.getParameter("userName");
		
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/login","postgres", "postgres123");
	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM \"user\"; " );
	         while ( rs.next() ) {
	             
	        	 String name = rs.getString("NAME");
	        	 String pass = rs.getString("PASS");
	        	 
	        	 if(p.equals(pass)&& n.equals(name)){
	 	  			RequestDispatcher rd=request.getRequestDispatcher("welcome");
	 	  			rd.forward(request, response);
	 	  			break;
	        	 }}
         rs.close();
	          stmt.close();
	          c.close();
	         
	   } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	 
	      
	  			out.print("Sorry username or password error!");
	  			RequestDispatcher rd=request.getRequestDispatcher("login.html");
	  			rd.include(request, response);
	  		
	}

}
