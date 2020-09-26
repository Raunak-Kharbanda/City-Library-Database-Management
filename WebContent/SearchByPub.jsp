<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search by Publisher</title>
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
	<nav class="class=" navbar
		navbar-light" style="background-color: #D3D3D3;"">
		<ul class="nav nav-pills">

			<li class="nav-item"><a class="nav-link" href="MyDocuments">My
					Documents</a></li>

			<li class="nav-item"><a class="nav-link" href="Search.jsp">Search</a></li>

			<li class="nav-item"><a class="nav-link " href="ReserveList">Reserve
					List</a></li>

			<li class="nav-item"><a class="nav-link" href="ReaderFines">Check
					Fines</a></li>

			<li class="nav-item"><a class="nav-link active"
				href="SearchByPub.jsp">Search by PubID</a></li>
				
			<li class="nav-item"><a class="nav-link" href="MainLogin.jsp">Logout</a></li>

		</ul>
	</nav>

	<div class="col-sm">
		<div class="card">
			<h5 class="card-header">Search a document with Publisher ID</h5>
			<div class="card-body">

					<form
						action="http://localhost:8080/LibraryManagement/SearchByPublisher"
						method="post">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">Publisher
									Id</span>
							</div>
							<input type="text" class="form-control"
								placeholder="Publisher Id" aria-label="Username"
								aria-describedby="basic-addon1" name="PubId" value="">
						</div>
						<div>
							<input class="btn btn-primary" type="submit" value="Submit"
								name="button" />
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
				<th>Publisher ID</th>
				<th>Document ID</th>
				<th>Title of document</th>
			</tr>

			<%
								for (Document d : data) {
							%>
			<tr>
				<td><%=d.getPublisherID()%></td>
				<td><%=d.getDocID()%></td>
				<td><%=d.getTitle()%></td>
			</tr>
			<%
								}
							%>
		</table>
		<%
							} else if (errorMessage != "") {
						%>
		<div class="alert alert-danger" role="alert"><%=errorMessage %></div>

		<%} %>
	</div>
</body>
</html>