package App;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final String query="insert into bookdata(id,bookname,bookedition,bookprice) values(?,?,?) ";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String bname=req.getParameter("bookname");
		String bedi=req.getParameter("bookedi");
		float bprice=Float.parseFloat(req.getParameter("bookprice"));
		
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","password");
			PreparedStatement pstmt=con.prepareStatement(query);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("<h1>"+e.getMessage()+"</h1>");
			
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
