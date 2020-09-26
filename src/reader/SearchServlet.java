package reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.LoggedInReader;
import commons.MyJDBCConnection;
import models.AccountInfo;
import models.Branch;
import models.Document;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		List<Document> dataList = new ArrayList<Document>();
		
		String readerID = LoggedInReader.getLoggedInReader();
		
		String id = request.getParameter("DocId");
		String document = request.getParameter("DocName");
		String publisher = request.getParameter("PublisherID");
		
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "select * from document where DocID = '"+id+"' and Title ='"+document+"' and PublisherID = '"+publisher+"';";
			ResultSet rs = stmt.executeQuery(sql);  


			if(rs.next()==false)
			{
			
				request.setAttribute("errorMessage","No record found for Doc Id " + id + " and Title " + document + " and Publisher " + publisher +".");
				request.setAttribute("data",null);
			}
			else
			{
				
				String sql1 = "select c.DocID, c.CopyNo, c.LibID, b.BorNo, b.BorDtime, b.RetDtime from copy c LEFT JOIN borrows b ON (c.copyNo = b.copyNo and c.DocID = b.DocID) where c.DocID='"+id+"';";
				//select c.DocID, c.CopyNo, b.BorNo, b.BorDtime from copy c LEFT OUTER JOIN borrows b ON (c.copyNo = b.copyNo and c.DocID = b.DocID) where c.DocID='Doc1002';
				ResultSet rs1 = stmt.executeQuery(sql1);
				rs1 = stmt.getResultSet();

				while (rs1.next ()){
					String borrowNo = rs1.getString("BorNo");
					String copyNum = rs1.getString("CopyNo");
					String documentId = rs1.getString("DocID");
					String libraryId = rs1.getString("LibID");
					String borrowDate = rs1.getString("BorDtime");
					String retDTime = rs1.getString("RetDtime");
					
					Document d = new Document();
					d.setBorNo(borrowNo);
					d.setCopyNumber(copyNum);
					d.setDocID(documentId);
					d.setLibID(libraryId);
					d.setBorDate(borrowDate);
					d.setRetDtime(retDTime);
					
					
					dataList.add(d);
				}
				request.setAttribute("errorMessage",null);
				request.setAttribute("title", document);
				request.setAttribute("data",dataList);
			}
			
			rs.close ();
			stmt.close ();
		}
		catch (Exception e) 
		{  
			System.out.println("Caution. Error!!"); 
			e.printStackTrace();
		}  


		RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");

		if (dispatcher != null){

			dispatcher.forward(request, response);

		}
	}
}


