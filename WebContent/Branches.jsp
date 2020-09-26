<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Branch"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Branches</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<form action="http://localhost:8080/LibraryManagement/BranchServlet"
	method="post"></form>
<body>
	<!-- Request attribute sanitization -->
	<%
		ArrayList<Branch> data = null;
		if (request.getAttribute("data") != null) {
			data = (ArrayList<Branch>) request.getAttribute("data");
		} else {
			data = new ArrayList<Branch>();
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

			<li class="nav-item"><a class="nav-link" href="SearchAdmin.jsp">Search
					Document</a></li>

			<li class="nav-item"><a class="nav-link" href="AddDoc.jsp">Add
					Document</a></li>

			<li class="nav-item"><a class="nav-link" href="AddReader.jsp">Add
					Reader Info</a></li>

			<li class="nav-item"><a class="nav-link active" href="Branches">Branches</a></li>

			<li class="nav-item"><a class="nav-link" href="FreqBorrowServlet">Frequent
					Borrowers</a></li>

			<li class="nav-item"><a class="nav-link" href="MostBorrow.jsp">Most
					Borrowed Books</a></li>

			<li class="nav-item"><a class="nav-link" href="PopularBooksServlet">2020's
					Popular Books</a></li>

			<li class="nav-item"><a class="nav-link" href="AvgFinesServlet">Average
					Fines</a></li>

			<li class="nav-item"><a class="nav-link" href="MainLogin.jsp">Logout</a></li>

		</ul>
	</nav>

	<div>
		<%
			if (data.size() > 0) {
		%>
		<table class="table">
			<tr>
				<th>Library ID</th>
				<th>Name</th>
				<th>Location</th>
			</tr>


			<%
				for (Branch b : data) {
			%>
			<tr>
				<td><%=b.getLibID()%></td>
				<td><%=b.getLibName()%></td>
				<td><%=b.getLibLocation()%></td>
				<%-- <%
					if (a.getBalance() > 1000) {
				%>
				<td><input class="btn btn-danger" type="submit" value="Cancel"
					name="button" /></td>
				<%
					}
				%> --%>
				<!-- <td><input class="btn btn-success" type="submit" value="Book"
					name="button" /></td> -->
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