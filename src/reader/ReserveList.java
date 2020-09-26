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
import models.Reader;

/**
 * Servlet implementation class ReserveList
 */
@WebServlet("/ReserveList")
public class ReserveList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveList() {
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
		RequestDispatcher dispatcher = null;



		dispatcher = doGetHelper(request, readerId).getRequestDispatcher("Reserve.jsp");

		if (dispatcher != null){

			dispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String resNumToDel = request.getParameter("Cancel");

		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			String sql = "Delete from reserves where ResNo = '"+resNumToDel+"';";
			//DELETE FROM `LibSystemNew`.`reserves` WHERE (`ResNo` = '20');
			boolean test = stmt.execute(sql);

			if(test) {

				request.setAttribute("errorDelMessage","ERROR while cancelling the reservation!!!!");
				request.setAttribute("data",null);
			}
			else {

			}


			stmt.close ();
		}
		catch (Exception e) {  

			System.out.println("error"); 
			e.printStackTrace();
		}

		doGet(request, response);
	}

	public static HttpServletRequest doGetHelper(HttpServletRequest request, String readerId) {
		ArrayList<Document> dataList = new ArrayList<Document>();
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "select * from reserves where ReaderID = '"+readerId+"';";
			ResultSet rs = stmt.executeQuery(sql); 

			rs = stmt.getResultSet();

			if(rs.first()==false) {
				

				request.setAttribute("errorMessage","No Documents in reserve list!!");
				request.setAttribute("data",null);
			}
			else {
				rs.beforeFirst();
				while (rs.next ()){
					Document d = new Document(rs.getString("ResNo"),rs.getString("ReaderID"),rs.getString("DocID"),rs.getString("CopyNo"),rs.getString("LibID"), rs.getString("Dtime"));
					dataList.add(d);
				}
				rs.close ();
				stmt.close ();
				request.setAttribute("errorMessage", null);
				request.setAttribute("data",dataList);
			}
		}catch (Exception e) 
		{  
			System.out.println("error"); 
			e.printStackTrace();
		}  


		return request;

	}

}
