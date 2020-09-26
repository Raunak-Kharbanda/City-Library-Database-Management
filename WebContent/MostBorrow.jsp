<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MostBorrowedBooks</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
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

			<li class="nav-item"><a class="nav-link" href="Branches">Branches</a></li>

			<li class="nav-item"><a class="nav-link" href="FreqBorrowServlet">Frequent
					Borrowers</a></li>

			<li class="nav-item"><a class="nav-link active" href="#">Most
					Borrowed Books</a></li>

			<li class="nav-item"><a class="nav-link" href="PopularBooksServlet">2020's
					Popular Books</a></li>

			<li class="nav-item"><a class="nav-link" href="AvgFinesServlet">Average
					Fines</a></li>

			<li class="nav-item"><a class="nav-link" href="MainLogin.jsp">Logout</a></li>

		</ul>
	</nav>

	<div class="col-sm">
		<div class="card">
			<h5 class="card-header">Enter Library ID to check most popular
				books in branch</h5>
			<div class="card-body">
				<form
					action="http://localhost:8080/LibraryManagement/MostBorrowedServlet"
					method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Library Id"
							aria-label="LibId" aria-describedby="basic-addon1" name="LibId">

					</div>
					<div class="input-group mb-3">
						<input class="btn btn-primary" type="submit" value="Submit"
							name="add">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div>
		<%
			if (data.size() > 0) {
		%>
		<table class="table">
			<tr>
				<th>Document ID</th>
				<th>Title</th>
				<th>Number of Times Borrowed</th>
			</tr>

			<%
				for (Document d : data) {
			%>
			<tr>
				<td><%=d.getDocID()%></td>
				<td><%=d.getTitle()%></td>
				<td><%=d.getTimesBorrowed()%></td>
			</tr>
			<%
				}
			%>
		</table>
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