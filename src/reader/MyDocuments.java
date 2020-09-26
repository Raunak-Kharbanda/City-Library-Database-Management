package reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.LoggedInReader;
import commons.MyJDBCConnection;
import models.Document;

/**
 * Servlet implementation class MyDocuments
 */
@WebServlet("/MyDocuments")
public class MyDocuments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyDocuments() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String readerId = LoggedInReader.getLoggedInReader();



		RequestDispatcher dispatcher = doGetHelper(request, readerId).getRequestDispatcher("MyDocs.jsp");

		if (dispatcher != null){

			dispatcher.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String bowId = request.getParameter("Return");
		String readerId = LoggedInReader.getLoggedInReader();
		RequestDispatcher dispatcher = null;

		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE borrows SET RetDtime = NOW() WHERE BorNo = '" + bowId + "';";
			boolean test = stmt.execute(sql);
			if(test) {

				request.setAttribute("returnErrorMessage","Something went wrong while returning the document !!!!");
				request.setAttribute("data",null);
				dispatcher = doGetHelper(request, readerId).getRequestDispatcher("MyDocs.jsp");
			}
			else {
				dispatcher = doGetHelper(request, readerId).getRequestDispatcher("MyDocs.jsp");

			}
		} 	catch (Exception e) 
		{  
			System.out.println("error"); 
			e.printStackTrace();
		}
		if (dispatcher != null){

			dispatcher.forward(request, response);

		}


	}


	public static HttpServletRequest doGetHelper(HttpServletRequest request, String readerId) {


		ArrayList<Document> dataList = new ArrayList<Document>();
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "select * from borrows where ReaderID = '"+readerId+"' and RetDtime IS NULL;";
			ResultSet rs = stmt.executeQuery(sql); 

			rs = stmt.getResultSet();

			if(rs.first()==false) {

				request.setAttribute("errorMessage","No Documents Found!!");
				request.setAttribute("data",null);
			}
			else {
				rs.beforeFirst();	
				while (rs.next ()) {
					Document d = new Document(rs.getString("BorNo"),rs.getString("ReaderID"),rs.getString("DocID"),rs.getString("CopyNo"), rs.getString("BorDtime"));
					dataList.add(d);
				}
				rs.close ();
				stmt.close ();
				request.setAttribute("errorMessage", null);
				request.setAttribute("data",dataList);
			}
		}
		catch (Exception e) 
		{  
			System.out.println("error"); 
			e.printStackTrace();
		}

		return request;

	}

}
