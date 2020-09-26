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
 * Servlet implementation class AddDocServlet
 */
@WebServlet("/AddDocServlet")
public class AddDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDocServlet() {
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
		String documentId = request.getParameter("DocId");
		String copyNum = request.getParameter("CopyNum");
		String libraryId = request.getParameter("LibId");
		String position = request.getParameter("position");
		
		ArrayList<Document> dataList = new ArrayList<Document>();
		
		try 
		{  
			Connection connection = MyJDBCConnection.getConnection();
			Statement stmt = connection.createStatement();  
			String sql = "insert into copy(DocID,CopyNo,LibID,Position) values('"+documentId+"','"+copyNum+"','"+libraryId+"','"+position+"');";
			//insert into copy(DocID,CopyNo,LibID,Position) values ('Doc1011','90','lib001','Row60');
			boolean test = stmt.execute(sql);
			
			if(test) {
				
				request.setAttribute("errorMessage","Value Insert not possible check the values again!!!!");
				request.setAttribute("data",null);
			}
			else {
				
			String sql1 = "select c.DocID, c.CopyNo, d.Title from copy c LEFT OUTER JOIN document d ON (c.DocID = d.DocID) where c.DocID= d.DocID;";
			//select c.DocID, c.CopyNo, d.Title from copy c LEFT OUTER JOIN document d ON (c.DocID=d.DocID) where c.DocID = D.DocID;
			ResultSet rs1 = stmt.executeQuery(sql1);
			rs1 = stmt.getResultSet();
			
			while (rs1.next ()){
				
				String document = rs1.getString("DocID");
				String copy = rs1.getString("CopyNo");
				String name = rs1.getString("Title");
				Document d = new Document(document, copy, name); 
				d.setDocID(document);
				d.setCopyNumber(copy);
				d.setTitle(name);
				dataList.add(d);
			}
			
			request.setAttribute("errorMessage",null);
			request.setAttribute("data",dataList);
		}

			//rs.close ();
			stmt.close ();
		}
		catch (Exception e) {  
			
			//System.out.println("error"); 
			request.setAttribute("errorMessage","Document ID and Copy Number already exist choose another one");
			request.setAttribute("data",null);
			e.printStackTrace();
	}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AddDoc.jsp");

		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}

	}

}
