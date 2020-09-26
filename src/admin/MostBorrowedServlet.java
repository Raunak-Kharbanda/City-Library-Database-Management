package admin;

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

import commons.MyJDBCConnection;
import models.Document;

/**
 * Servlet implementation class MostBorrowedServlet
 */
@WebServlet("/MostBorrowedServlet")
public class MostBorrowedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostBorrowedServlet() {
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
		
		String libraryId = request.getParameter("LibId");
		ArrayList<Document> dataList = new ArrayList<Document>();
		
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			System.out.println(libraryId);
			String sql = "select bs.DocID, count(bs.DocID)as TimesBorrowed,d.Title  from borrows bs, book b, document d where bs.DocID = d.DocID and d.DocID = b.DocID and LibID = '"+libraryId+"' group by (bs.DocID) order by count(bs.DocID) desc limit 10;";
			//select bs.DocID, count(bs.DocID),d.Title  from borrows bs, book b, document d where bs.DocID = d.DocID and d.DocID = b.DocID and LibID = 'lib001' group by (bs.DocID) order by count(bs.DocID) desc limit 10;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			
			if(rs.first()==false) {
				
				
				request.setAttribute("errorMessage","No Record Found");
				request.setAttribute("data",null);
			}
			else {
			rs.beforeFirst();
			while (rs.next ()){

				String document = rs.getString("DocID");
				String borrowedTimes = rs.getString("TimesBorrowed");
				String name = rs.getString("Title");
				Document d = new Document(document, name); 
				d.setTimesBorrowed(borrowedTimes);
				dataList.add(d);
			}
			
			request.setAttribute("errorMessage",null);
			request.setAttribute("data",dataList);
		}

			rs.close ();
			stmt.close ();
		}
		catch (Exception e) {  
			
			System.out.println("error"); 
			e.printStackTrace();
	}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MostBorrow.jsp");

		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}
		
	}

}
