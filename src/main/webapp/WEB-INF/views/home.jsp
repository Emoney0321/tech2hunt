<%@ include file="macros/includes.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link href="${request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>

	<%@ include file="macros/navbar.jsp" %>
	
	<div style="padding-top: 60px;" class="offset4">
		<p>
			Sign Up or <a href="${request.contextPath}/login">Login</a>
		</p>
		<form action="${request.contextPath}/signup" method="post"
			class="form-horizontal">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="firstName">First Name</label>
					<div class="controls">
						<input type="text" id="firstName" name="firstName"
							placeholder="First Name">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="lastName">Last Name</label>
					<div class="controls">
						<input type="text" id="lastName" name="lastName"
							placeholder="Last Name">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="email">Email</label>
					<div class="controls">
						<input type="email" name="email" placeholder="Email" />
					</div>
				</div>
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
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn">Submit</button>
					</div>
				</div>
			</fieldset>
		</form>

	</div>
</body>
</html>