<footer class="footer">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="<%=application.getContextPath()%>/terms-of-service.jsp">Terms of service</a>
				</li>
			</ul>
			<c:if test="${userRole == \"ADMIN\"}">
				<ul class="navbar-nav mr-inline">
					<li class="nav-item"><a class="nav-link" href="<%=application.getContextPath()%>/actions/admin-panel">Admin panel</a></li>
				</ul>
			</c:if>
		</div>
	</nav>
</footer>