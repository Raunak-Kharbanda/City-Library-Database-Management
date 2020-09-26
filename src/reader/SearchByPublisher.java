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
 * Servlet implementation class SearchByPublisher
 */
@WebServlet("/SearchByPublisher")
public class SearchByPublisher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchByPublisher() {
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
		String PubId = request.getParameter("PubId");
		ArrayList<Document> dataList = new ArrayList<Document>();

		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			String sql = "select DocID, Title, PublisherID from document where PublisherID = '"+PubId+"';";
			ResultSet rs = stmt.executeQuery(sql);


			if(rs.next()==false) {

				request.setAttribute("errorMessage","No record found for Publisher Id " + PubId +".");
				request.setAttribute("data",null);
			}
			else {
				while (rs.next ()){
				String pID = rs.getString("PublisherID");
				String DocID = rs.getString("DocID");
				String DocName = rs.getString("Title");
				Document d = new Document(DocID, DocName);
				d.setDocID(DocID);
				d.setTitle(DocName);
				d.setPublisherID(pID);
				dataList.add(d);

				request.setAttribute("errorMessage",null);
				request.setAttribute("data",dataList);
				}
			}
			rs.close ();
			stmt.close ();
		}
		catch (Exception e) {  

			System.out.println("error"); 
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchByPub.jsp");

		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}

	}
}



