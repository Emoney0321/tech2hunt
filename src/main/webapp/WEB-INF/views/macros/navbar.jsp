<%@ include file="includes.jsp"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="/">Home</a>
			<sec:authorize access="isFullyAuthenticated()">
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="${request.contextPath}/messages">View Messages</a></li>
						<li><a href="<c:url value="j_spring_security_logout" />">Logout</a></li>
					</ul>
				</div>
			</sec:authorize>
		</div>
	</div>
</div>
