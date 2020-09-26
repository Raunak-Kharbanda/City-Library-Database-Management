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
import models.Branch;

/**
 * Servlet implementation class BranchServlet
 */
@WebServlet("/Branches")
public class BranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BranchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Branch> dataList = new ArrayList<Branch>();
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from branch");  

			rs = stmt.getResultSet();

			while (rs.next ()){
				
				Branch b = new Branch(rs.getString("LibId"),rs.getString("LibName"),rs.getString("LibLocation"));
				dataList.add(b);

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


		RequestDispatcher dispatcher = request.getRequestDispatcher("Branches.jsp");

		if (dispatcher != null){

			dispatcher.forward(request, response);

		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
	}
}

