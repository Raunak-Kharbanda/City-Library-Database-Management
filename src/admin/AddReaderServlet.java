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
import models.Reader;

/**
 * Servlet implementation class AddReaderServlet
 */
@WebServlet("/AddReaderServlet")
public class AddReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReaderServlet() {
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
		String readerId = request.getParameter("readerId");
		String type = request.getParameter("readerType");
		String name = request.getParameter("readerName");
		String address = request.getParameter("readerAdd");
		
		ArrayList<Reader> dataList = new ArrayList<Reader>();
		
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			String sql = "insert into reader(ReaderID,Rtype,Rname,RAddress) values('"+readerId+"','"+type+"','"+name+"','"+address+"');";
			//insert into reader(ReaderID,Rtype,Rname,RAddress) values ('R0018','Employee','Jane','NJIT Newark');
			boolean test = stmt.execute(sql);
			
			if(test) {
				
				request.setAttribute("errorMessage","Value Insert not possible check the values again!!!!");
				request.setAttribute("data",null);
			}
			else {
				
			String sql1 = "select * from reader;";
			ResultSet rs1 = stmt.executeQuery(sql1);
			rs1 = stmt.getResultSet();
			
			while (rs1.next ()){
				
				String readerID = rs1.getString("ReaderID");
				String readerType = rs1.getString("Rtype");
				String readerName = rs1.getString("Rname");
				String readerAdd = rs1.getString("RAddress");
				Reader r = new Reader(readerID, readerType, readerName, readerAdd); 
				r.setReaderID(readerID);
				r.setReaderType(readerType);
				r.setReaderName(readerName);
				r.setReaderAddress(readerAdd);
				dataList.add(r);
			}
			
			request.setAttribute("errorMessage",null);
			request.setAttribute("data",dataList);
		}

			stmt.close ();
		}
		catch (Exception e) {  
			
			//System.out.println("error"); 
			request.setAttribute("errorMessage","Reader ID already exist choose another one");
			request.setAttribute("data",null);
			e.printStackTrace();
	}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AddReader.jsp");

		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}

	}

}
