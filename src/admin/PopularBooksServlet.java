package admin;

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

import commons.MyJDBCConnection;
import models.Document;

/**
 * Servlet implementation class PopularBooksServlet
 */
@WebServlet("/PopularBooksServlet")
public class PopularBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopularBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Document> dataList = new ArrayList<Document>();
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			ResultSet rs = stmt.executeQuery("select bs.DocID, d.Title from borrows bs, book b, document d where bs.DocID = b.DocID and b.DocID = d.DocID group by (bs.DocID) order by count(bs.DocID) desc limit 10;");  
			rs = stmt.getResultSet();

			while (rs.next ()){
				
				Document r = new Document(rs.getString("DocID"),rs.getString("Title"));
				dataList.add(r);

			}

			rs.close ();
			stmt.close ();
		}catch (Exception e) 
		{  
			System.out.println("error"); 
			e.printStackTrace();
		}  


		request.setAttribute("errorMessage", null);
		request.setAttribute("data",dataList);


		RequestDispatcher dispatcher = request.getRequestDispatcher("PopularBooks.jsp");

		if (dispatcher != null){

			dispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
