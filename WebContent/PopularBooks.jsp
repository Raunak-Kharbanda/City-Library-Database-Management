<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PopularBooks</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<form action="http://localhost:8080/LibraryManagement/PopularBooksServlet"
	method="post"></form>
<body>

<!-- Request attribute sanitization -->
	<%
		ArrayList<Document> data = null;
		if (request.getAttribute("data") != null) {
			data = (ArrayList<Document>) request.getAttribute("data");
		} else {
			data = new ArrayList<Document>();
		}

		String errorMessage = "";
		if (request.getAttribute("errorMessage") != null) {
			errorMessage = (String) request.getAttribute("errorMessage");
		}
	%>
<nav class="class="navbar navbar-light" style="background-color: #D3D3D3;"">
		<ul class="nav nav-pills">

			<li class="nav-item"><a class="nav-link" href="SearchAdmin.jsp">Search Document</a></li>

			<li class="nav-item"><a class="nav-link" href="AddDoc.jsp">Add Document</a></li>

			<li class="nav-item"><a class="nav-link" href="AddReader.jsp">Add Reader Info</a></li>

			<li class="nav-item"><a class="nav-link" href="Branches">Branches</a></li>
			
			<li class="nav-item"><a class="nav-link" href="FreqBorrowServlet">Frequent Borrowers</a></li>
			
			<li class="nav-item"><a class="nav-link" href="MostBorrow.jsp">Most Borrowed Books</a></li>
			
			<li class="nav-item"><a class="nav-link active" href="PopularBooksServlet">2020's Popular Books</a></li>
			
			<li class="nav-item"><a class="nav-link" href="AvgFinesServlet">Average Fines</a></li>
			
			<li class="nav-item"><a class="nav-link" href="MainLogin.jsp">Logout</a></li>

		</ul>
	</nav>
	
	<div>
		<%
			if (data.size() > 0) {
		%>
		<table class="table">
			<tr>
				<th>Document ID</th>
				<th>Book Name</th>
			</tr>


			<%
				for (Document d : data) {
			%>
			<tr>
				<td><%=d.getDocID()%></td>
				<td><%=d.getTitle()%></td>

			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>
	</div>

	</body>
	</html>