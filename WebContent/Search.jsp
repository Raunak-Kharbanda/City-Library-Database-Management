<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="java.util.*"%>
<%@page language="java" import="models.Document"%>
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

		String title = "";
		if (request.getAttribute("title") != null) {
			title = (String) request.getAttribute("title");
		}
	%>
	<!-- Request attribute sanitization ends -->


	<!-- This is starting of Nav Bar -->
	<nav class="class=" navbar
		navbar-light" style="background-color: #D3D3D3;"">
		<ul class="nav nav-pills">

			<li class="nav-item"><a class="nav-link"
				href="MyDocuments">My Documents</a></li>

			<li class="nav-item"><a class="nav-link active" href="Search.jsp">Search</a></li>

			<li class="nav-item"><a class="nav-link" href="ReserveList">Reserve
					List</a></li>

			<li class="nav-item"><a class="nav-link" href="ReaderFines">Check
					Fines</a></li>

			<li class="nav-item"><a class="nav-link" href="SearchByPub.jsp">Search
					by PubID</a></li>

			<li class="nav-item"><a class="nav-link" href="MainLogin.jsp">Logout</a></li>


		</ul>
	</nav>

	<!-- This is ending of Nav Bar -->


	<div class="container">
		<div class="row">
			<!-- <div class="col-sm">
				<div class="card">
					<h5 class="card-header">Search All Documents</h5>
					<div class="card-body">
						<p class="card-text">Search all available documents.</p>
						<form
							action="http://localhost:8080/LibraryManagement/SearchServlet"
							method="post">
							<div>
								<input class="btn btn-primary" type="submit"
									value="Get All Records" name="button" />

							</div>
						</form>
					</div>
				</div>
			</div> -->
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">Search Particular Document</h5>
					<div class="card-body">
						<p class="card-text">Enter fields to search documents.</p>
						<form
							action="http://localhost:8080/LibraryManagement/SearchServlet"
							method="post">
							<div>

								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Document
											Id</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Document Id" aria-label="Username"
										aria-describedby="basic-addon1" name="DocId">
								</div>


								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Document
											Name</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Document Name" aria-label="Username"
										aria-describedby="basic-addon1" name="DocName">
								</div>


								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">Publisher</span>
									</div>
									<input type="text" class="form-control" placeholder="Publisher"
										aria-label="Username" aria-describedby="basic-addon1"
										name="PublisherID">
								</div>


								<input class="btn btn-primary" type="submit"
									value="Get All Records" name="button" />

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div>
		<%
			if (data.size() > 0) {
		%>
		<form action="http://localhost:8080/LibraryManagement/BookServlet"
			method="post">
			<table class="table">
				<tr>
					<th>Document ID</th>
					<th>Copy Number</th>
					<th>Library ID</th>
					<th>Borrow No</th>
					<th></th>
				</tr>
				<%
					for (Document d : data) {
							if (d.getBorNo() == null || d.getRetDtime() != null) {
								String params = d.getCopyNumber() + "~" + d.getDocID() + "~" + d.getLibID();
				%>
				<tr>
					<td><%=d.getDocID()%></td>
					<td><%=d.getCopyNumber()%></td>
					<td><%=d.getLibID()%></td>
					<td><%=title%></td>
					<td><button class="btn btn-secondary" type="submit" name="Borrow" value=<%=params%>>Borrow</button></td>
					<td><button class="btn btn-secondary" type="submit" name="Reserve" value=<%=params%>>Reserve</button></td>

				</tr>
				<%
					}
						}
				%>
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