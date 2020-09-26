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
import models.Reader;

/**
 * Servlet implementation class FreqBorrowServlet
 */
@WebServlet("/FreqBorrowServlet")
public class FreqBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreqBorrowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Reader> dataList = new ArrayList<Reader>();
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			ResultSet rs = stmt.executeQuery("select bs.ReaderID, count(bs.DocID) as Dcount, r.RName from borrows bs, book b, reader r where bs.DocID = b.DocID and bs.ReaderID = r.ReaderID group by (bs.ReaderID) order by count(bs.DocID) desc limit 10;");  
			rs = stmt.getResultSet();

			while (rs.next ()){
				
				Reader r = new Reader(rs.getString("ReaderID"),rs.getString("Dcount"),rs.getString("RName"));
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


		RequestDispatcher dispatcher = request.getRequestDispatcher("FreqBorrow.jsp");

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
