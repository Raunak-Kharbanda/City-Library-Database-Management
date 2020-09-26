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
import models.Branch;
import models.Reader;
import sun.security.action.GetLongAction;

/**
 * Servlet implementation class ReaderFines
 */
@WebServlet("/ReaderFines")
public class ReaderFines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReaderFines() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Reader> dataList = new ArrayList<Reader>();
		String readerId = LoggedInReader.getLoggedInReader();
		
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			
			Statement stmt = connection.createStatement();  
			ResultSet rs = stmt.executeQuery("SELECT * ,case when DATEDIFF(NOW(), b.BorDtime) > 20 then (DATEDIFF(NOW(), b.BorDtime) * 0.20 -(20*0.20))  else 0 end as FINE from LibSystemNew.borrows b where b.ReaderID = '"+readerId+"';");
			rs = stmt.getResultSet();

			while (rs.next ()){
				
				Reader r = new Reader(rs.getString("BorNo"),rs.getString("DocID"),rs.getString("CopyNo"),rs.getString("BorDtime"),rs.getString("RetDtime"),rs.getString("FINE"));
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


		RequestDispatcher dispatcher = request.getRequestDispatcher("Fines.jsp");

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
