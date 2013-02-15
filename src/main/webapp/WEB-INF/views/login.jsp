<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Login Page</title>
<link href="${request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>

	<!-- Navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="/">Home</a>
				<sec:authorize access="hasRole('ROLE_USER')">
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li><a href="${request.contextPath}/messages">View
									Messages</a></li>
							<li><a href="<c:url value="j_spring_security_logout" />">
									Logout</a></li>
						</ul>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>

	<div style="padding-top: 60px" class="offset4">
		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="alert alert-error">
				Your login attempt was not successful, try again.<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<form action="<c:url value='j_spring_security_check' />" method='POST'>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="username">User Name</label>
					<div class="controls">
						<input type="text" id="userName" name="username"
							placeholder="User Name">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" id="password" name="password"
							placeholder="Password">
					</div>
				</div>
				<button type="submit" class="btn">Submit</button>
			</fieldset>
		</form>
	</div>
</body>
</html>