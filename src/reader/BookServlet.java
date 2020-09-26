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
import models.Document;
import models.Reader;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
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

		List<Document> dataList = new ArrayList<Document>();
		String borrowParams = request.getParameter("Borrow");
		String reserveParamsReseve = request.getParameter("Reserve");
		RequestDispatcher dispatcher = null;
		
		if (reserveParamsReseve == null) {
			String borrowParamsSplit[] = borrowParams.split("~");
			String copyNumberToBook = borrowParamsSplit[0];
			String docIdToBook = borrowParamsSplit[1];
			String libIDForBook = borrowParamsSplit[2];
			String readerIdForbooking = LoggedInReader.getLoggedInReader();
			


			try 
			{  
				Connection connection = MyJDBCConnection.getConnection();
				Statement stmt = connection.createStatement();
				String sql = "INSERT INTO borrows (`ReaderID`, `DocID`, `CopyNo`, `LibID`, `BorDtime`) VALUES ('"+readerIdForbooking+"','"+docIdToBook+"','"+copyNumberToBook+"','"+libIDForBook+"',NOW());";
				//INSERT INTO borrows (`ReaderID`, `DocID`, `CopyNo`, `LibID`, `BorDtime`) VALUES ('R0016', 'Doc1001', '4', 'lib003', NOW());

				boolean test = stmt.execute(sql);

				if(test) {

					request.setAttribute("errorMessage","ERROR!!!!");
					request.setAttribute("data",null);
					dispatcher = request.getRequestDispatcher("Search.jsp");
				}
				else {
					
					dispatcher = MyDocuments.doGetHelper(request, readerIdForbooking).getRequestDispatcher("MyDocs.jsp");

				}

				stmt.close ();
			}
			catch (Exception e) {  

				System.out.println("error"); 
				e.printStackTrace();
			}
		} else if (borrowParams == null ) {
			String reserveParamsReseveSplit[] = reserveParamsReseve.split("~");
			String copyNumberToReserve = reserveParamsReseveSplit[0];
			String docIdToReserve = reserveParamsReseveSplit[1];
			String libIDForReserve = reserveParamsReseveSplit[2];
			String readerIdForReserve = LoggedInReader.getLoggedInReader();
			


			try 
			{  
				Connection connection = MyJDBCConnection.getConnection();
				Statement stmt = connection.createStatement();
				String sql = "INSERT INTO reserves (`ReaderID`, `DocID`, `CopyNo`, `LibID`, `Dtime`) VALUES ('"+readerIdForReserve+"','"+docIdToReserve+"','"+copyNumberToReserve+"','"+libIDForReserve+"',NOW());";

				boolean test = stmt.execute(sql);

				if(test) {

					request.setAttribute("errorMessage","ERROR!!!!");
					request.setAttribute("data",null);
					dispatcher = request.getRequestDispatcher("Search.jsp");
				}
				else {
					
					dispatcher = ReserveList.doGetHelper(request, readerIdForReserve).getRequestDispatcher("Reserve.jsp");

				}

				stmt.close ();
			}
			catch (Exception e) {  

				System.out.println("error"); 
				e.printStackTrace();
			}
		}
		

		

		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}


	}

}
