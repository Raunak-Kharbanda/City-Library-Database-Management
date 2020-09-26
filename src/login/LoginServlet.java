package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.LoggedInReader;
import commons.MyJDBCConnection;
import models.Document;
import reader.MyDocuments;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// TODO Auto-generated method stub
		String readerId = request.getParameter("readerId");
		String adminId = request.getParameter("adminId");
		String adminPassword = request.getParameter("adminPassword");
		RequestDispatcher dispatcher = null;
		

		if (adminId != null && adminPassword != null && readerId == null) {
			
			if(adminId.equals("admin") && adminPassword.equals("admin")) 
			{
				
				dispatcher = request.getRequestDispatcher("SearchAdmin.jsp");
			}else {
				
				request.setAttribute("errorMessageAdmin","Not a valid credentials check your Id and Password");
				dispatcher = request.getRequestDispatcher("MainLogin.jsp");
			}
				
		} else if (readerId != null){

			try 
			{  
				Connection connection = MyJDBCConnection.getConnection();
				Statement stmt = connection.createStatement();  
				String sql = "select ReaderID  from reader  where ReaderID = '"+readerId+"';";
				//select ReaderID from reader where ReaderID= 'R0011';
				ResultSet rs = stmt.executeQuery(sql);

				if(rs.next()==false) {
					
					
					request.setAttribute("errorMessageReader","No record found for Reader Id " +readerId+".");
					dispatcher = request.getRequestDispatcher("MainLogin.jsp");

				}
				else {

					LoggedInReader.setLoggedInUser(readerId);// Catching reader id to use further include this line.
					dispatcher = MyDocuments.doGetHelper(request, readerId).getRequestDispatcher("MyDocs.jsp");
				}

				rs.close ();
				stmt.close ();
			}
			catch (Exception e) {  

				System.out.println("error"); 
				e.printStackTrace();
			}

		}

		if (dispatcher != null)
		{
			System.out.println(dispatcher.toString());
			dispatcher.forward(request, response);
		}

	}

}
