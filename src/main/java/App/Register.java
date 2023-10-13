package App;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	 public static final String query="insert into bookdata1(id,bookname,bookedition,bookprice) values(?,?,?,?) ";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String bname=req.getParameter("bookname");
		String bedi=req.getParameter("bookedi");
		float bprice=Float.parseFloat(req.getParameter("bookprice"));
		
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver.");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","password");
			PreparedStatement pstmt=con.prepareStatement(query);
			
			pstmt.setInt(1, 101);
			pstmt.setString(2,bname);
			pstmt.setString(3,bedi);
			pstmt.setFloat(4,bprice);
			
			pstmt.execute();
			count++;
			con.commit();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		if(count==1) {
		    out.println("<h3 style='color:green;text-align:center'>data inserted successfully!!!</h3>");
		    out.println("<a href='booklist'>Book list</a>");
		}
		else {
			out.println("<h3 style='color:red; text-align:center'>oops something went wrong!!!</h3><br><br>");
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.include(req, res);
			
		}
		
		out.println("<h1>"+bname+"</h1>");
		out.println("<h1>"+bedi+"</h1>");
		out.println("<h1>"+bprice+"</h1>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
