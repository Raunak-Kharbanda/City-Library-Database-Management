<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>

<!-- Request attribute sanitization -->
	<%
		String errorMessage = "";
		if (request.getAttribute("errorMessageAdmin") != null) {
			errorMessage = (String) request.getAttribute("errorMessageAdmin");
		}
		else if(request.getAttribute("errorMessageReader") != null){
			errorMessage = (String) request.getAttribute("errorMessageReader");
		}
	%>
	<!-- Request attribute sanitization ends -->
	
	
	<h1 class="blockquote text-center" style="font-size: 250%;">CITY LIBRARY SYSTEM</h1>

	<div class="container">
		<div class="row">
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">User Login</h5>
					<div class="card-body">
						<form action="http://localhost:8080/LibraryManagement/LoginServlet" method="post">
							<div class="input-group mb-3">
								<div class="input-group-prepend"></div>
								<input type="text" class="form-control" placeholder="ReaderID"
									aria-label="Username" aria-describedby="basic-addon1"
									name="readerId">
							</div>
							<input class="btn btn-primary" type="submit" value="Login" name="userSubmit">
						</form>
					</div>
				</div>
			</div>
			<br />
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">Admin Login</h5>
					<div class="card-body">
						<form action="http://localhost:8080/LibraryManagement/LoginServlet" method="post">
							<div class="input-group mb-3">
								<input type="text" class="form-control" placeholder="Admin Id"
									aria-label="AdminID" aria-describedby="basic-addon1" name="adminId">

							</div>
							<div class="input-group mb-3">
								<input type="password" class="form-control"
									placeholder="Password" aria-label="Password"
									aria-describedby="basic-addon1" name="adminPassword">

							</div>
							<div class="input-group mb-3">
								<input class="btn btn-primary" type="submit" value="Login" name = "adminSubmit">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div>

		<%
			if (errorMessage != "") {
		%>
		<div class="alert alert-danger" role="alert"><%=errorMessage%></div>

		<%
			}
		%>
	</div>

</body>
</html>