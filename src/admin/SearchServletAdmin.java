package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.MyJDBCConnection;
import models.Document;


/**
 * Servlet implementation class SearchServletAdmin
 */
@WebServlet("/SearchServletAdmin")
public class SearchServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServletAdmin() {
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

		String id = request.getParameter("DocId");
		String document = request.getParameter("DocName");

		ArrayList<Document> dataList = new ArrayList<Document>();

		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			String sql = "select * from document where DocID = '"+id+"' and Title ='"+document+"';";
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()==false) {

				request.setAttribute("errorMessage","No record found for Doc Id " + id + " and Title " + document + ".");
				request.setAttribute("data",null);
			}
			else {

				String sql1 = "select c.DocID, c.CopyNo, r.ResNo from copy c LEFT OUTER JOIN reserves r ON (c.copyNo = r.copyNo and c.DocID = r.DocID) where c.DocID='"+id+"';";
				ResultSet rs1 = stmt.executeQuery(sql1);
				rs1 = stmt.getResultSet();

				while (rs1.next ()){
					
					

					String regNo = rs1.getString("ResNo");
					String copyNum = rs1.getString("CopyNo");
					Document d = new Document(id, document);
					d.setCopyNumber(copyNum);
					d.setReserved(regNo);
					dataList.add(d);

				}
				
				
				
				request.setAttribute("errorMessage",null);
				request.setAttribute("data", dataList);
			}

			rs.close ();
			stmt.close ();
		}
		catch (Exception e) {  

			System.out.println("error"); 
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchAdmin.jsp");

		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}

	}
}
