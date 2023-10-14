package App;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/booklist")
public class booklist extends HttpServlet {

	public static final String query="select id,bookname,bookEdition,bookprice from bookdata3";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		try(
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book1","root","password");
				
			PreparedStatement pstmt=con.prepareStatement(query);){
			
			ResultSet rs=pstmt.executeQuery();
			
			//creating table
			out.println("<table border= '1px solid black';style='align:center '>");
			out.println("<tr>");
			out.println("<th>Book ID</th>");
			out.println("<th>Book Name</th>");
			out.println("<th>Book Edition</th>");
			out.println("<th>Book Price</th>");
			out.println("</tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getFloat(4)+"</td>");
				out.println("</tr>");	
			}
			out.println("</table>");
			
		}catch(SQLException e) {
			e.printStackTrace();
			out.println("<h2>"+e.getMessage()+"</h2>");
			
		}
		catch(Exception e1) {
			e1.printStackTrace();
			out.println("<h2>"+e1.getMessage()+"</h2>");
			
		}
	out.println("<a href=\"index.html\">Home</a>");	
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
