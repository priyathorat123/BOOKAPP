package App;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	 public static final String query="insert into bookdata3(bookname,bookEdition,bookprice) values(?,?,?) ";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String bname=req.getParameter("bookname");
		String bedi=req.getParameter("bookedi");
		float bprice=Float.parseFloat(req.getParameter("bookprice"));
		
		int count=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		try(
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book1","root","password");
				
			PreparedStatement pstmt=con.prepareStatement(query);){
			
			
			pstmt.setString(1,bname);
			pstmt.setString(2,bedi);
			pstmt.setFloat(3,bprice);
			
			count=pstmt.executeUpdate();
			if(count==1) {
			    out.println("<h3 style='color:green;text-align:center'>data inserted successfully!!!</h3>");
			    
			}
			else {
				out.println("<h3 style='color:red; text-align:center'>oops something went wrong!!!</h3><br><br>");
				RequestDispatcher rd=req.getRequestDispatcher("index.html");
				rd.include(req, res);
				
			}
			
			con.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			out.println("<h2>"+e.getMessage()+"</h2>");
			
		}
		catch(Exception e1) {
			e1.printStackTrace();
			out.println("<h2>"+e1.getMessage()+"</h2>");
			
		}
		out.println("<a href=\"index.html\">Home</a>");	
		out.println("<br>");
		out.println("<a href=\"booklist\">Book list</a>");	
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
