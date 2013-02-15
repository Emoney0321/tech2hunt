<%@ include file="macros/includes.jsp" %>
<html>
<head>
<title>Messages</title>
<link href="${request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<%@ include file="macros/navbar.jsp" %>
	
	<div style="padding-top: 60px" class="offset4">
		<p>
			<sec:authorize access="hasRole('ROLE_USER')">
		You have the User role so you can Update and Delete this message.
		</sec:authorize>
			<sec:authorize access="!hasRole('ROLE_USER')">
		You do not have any roles so you can only read this message.
		</sec:authorize>
		</p>

		<sec:authorize access="hasRole('ROLE_USER')">
			<form:form class="form-horizontal" method="put"
				action="${message.uri}">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="subject">Subject</label>
						<div class="controls">
							<input type="text" id="subject" name="subject"
								value="${message.subject}" placeholder="Subject">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="message">Message</label>
						<div class="controls">
							<input type="text" id="message" name="message"
								value="${message.message}" placeholder="Message">
						</div>
					</div>

					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">Update This Message</button>
						</div>
					</div>
				</fieldset>
			</form:form>
			<form:form class="form-horizontal" method="delete"
				action="${message.uri}">
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn">Delete This Message</button>
					</div>
				</div>
			</form:form>
		</sec:authorize>

		<sec:authorize access="!hasRole('ROLE_USER')">
			<div class="control-group">
				<label class="control-label" for="subject">Subject</label>
				<div class="controls">
					<input type="text" id="subject" name="subject"
						value="${message.subject}" placeholder="Subject">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="message">Message</label>
				<div class="controls">
					<input type="text" id="message" name="message"
						value="${message.message}" placeholder="Message">
				</div>
			</div>
		</sec:authorize>

	</div>

</body>
</html>