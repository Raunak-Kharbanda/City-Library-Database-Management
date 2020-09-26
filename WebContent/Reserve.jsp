<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reader Reserve List</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<form action="http://localhost:8080/LibraryManagement/ReserveList"
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


	<nav class="class=" navbar
		navbar-light" style="background-color: #D3D3D3;"">
		<ul class="nav nav-pills">

			<li class="nav-item"><a class="nav-link" href="MyDocuments">My
					Documents</a></li>

			<li class="nav-item"><a class="nav-link" href="Search.jsp">Search</a></li>

			<li class="nav-item"><a class="nav-link active"
				href="ReserveList">Reserve List</a></li>

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
		<form action="http://localhost:8080/LibraryManagement/ReserveList"
			method="post">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Reservation Number</th>
						<th scope="col">Document ID</th>
						<th scope="col">Copy Number</th>
						<th scope="col">Date & time of reservation</th>
					</tr>
				</thead>

				<%
					for (Document d : data) {
				%>
				<tbody>
					<tr>
						<td><%=d.getResNo()%></td>
						<td><%=d.getDocID()%></td>
						<td><%=d.getCopyNumber()%></td>
						<td><%=d.getDtime()%></td>
						<td><button class="btn btn-secondary" type="submit" name="Cancel" value=<%=d.getResNo()%>>Cancel Reservation</button></td>

					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</form>
		<%
			} else if (errorMessage != "") {
		%>
		<div class="alert alert-danger" role="alert"><%=errorMessage%></div>

		<%
			}
		%>

	</div>
</body>
</html>