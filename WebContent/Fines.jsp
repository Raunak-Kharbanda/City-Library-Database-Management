<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Reader"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<form action="http://localhost:8080/LibraryManagement/ReaderFines"
	method="post"></form>
<body>
	<!-- Request attribute sanitization -->
	<%
		ArrayList<Reader> data = null;
		if (request.getAttribute("data") != null) {
			data = (ArrayList<Reader>) request.getAttribute("data");
		} else {
			data = new ArrayList<Reader>();
		}

		String errorMessage = "";
		if (request.getAttribute("errorMessage") != null) {
			errorMessage = (String) request.getAttribute("errorMessage");
		}
	%>
	<!-- Request attribute sanitization ends -->
	
	
	<nav class="class=" navbar
		navbar-light" style="background-color: #D3D3D3;"">
		<ul class="nav nav-pills">

			<li class="nav-item"><a class="nav-link active"
				href="MyDocuments">My Documents</a></li>

			<li class="nav-item"><a class="nav-link" href="Search.jsp">Search</a></li>

			<li class="nav-item"><a class="nav-link" href="ReserveList">Reserve
					List</a></li>

			<li class="nav-item"><a class="nav-link" href="ReaderFines">Check
					Fines</a></li>

			<li class="nav-item"><a class="nav-link" href="SearchByPub.jsp">Search
					by PubID</a></li>
			
			<li class="nav-item"><a class="nav-link" href="MainLogin.jsp">Logout</a></li>


		</ul>
	</nav>
	<div>
		<%
			if (data.size() > 0) {
		%>
		<table class="table">
			<tr>
				<th>Borrow No</th>
				<th>Document ID</th>
				<th>Copy Number</th>
				<th>Borrow Date</th>
				<th>Return Date</th>
				<th>Fine Due</th>
			</tr>


			<%
				for (Reader r : data) {
			%>
			<tr>
				<td><%=r.getBorrowNo()%></td>
				<td><%=r.getDocId()%></td>
				<td><%=r.getCopyNo()%></td>
				<td><%=r.getBorrDate()%></td>
				<td><%=r.getRetDate()%></td>
				<td><%=r.getFine()%></td>

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