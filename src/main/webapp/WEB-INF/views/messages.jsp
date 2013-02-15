<%@ include file="macros/includes.jsp"%>
<html>
<head>
<title>Messages</title>
<link href="${request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<%@ include file="macros/navbar.jsp"%>

	<div style="padding-top: 60px">
		<c:if test="${not empty messages.messages}">
			<table class="table">
				<thead>
					<tr>
						<td>Subject</td>
						<td>Message</td>
						<td>Actions</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="message" items="${messages.messages}">
						<tr>
							<td><c:out value="${message.subject}"></c:out></td>
							<td><c:out value="${message.message}"></c:out></td>
							<td><a href="${message.uri}">View Message</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<p>Enter a subject and message then click the Add Message button.</p>
		<form action="${request.contextPath}/messages" method="post"
			class="form-horizontal">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="subject">Subject</label>
					<div class="controls">
						<input type="text" id="subject" name="subject"
							placeholder="Subject">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="message">Message</label>
					<div class="controls">
						<input type="text" id="message" name="message"
							placeholder="Message">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn">Add Message</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>