<footer class="footer">
<hr/>
<div class="d-flex justify-content-between align-items-center">
	<a class="text-muted" href="<%=application.getContextPath()%>/terms-of-service.jsp">Terms of service</a>
	<a href="#">
		<i class="fas fa-crow text-muted"></i>
	</a>
	<div>
		<c:if test="${userRole == \"ADMIN\"}">
			<a class="text-muted" href="<%=application.getContextPath()%>/actions/admin-panel">Admin panel</a>
		</c:if>
		<c:if test="${userRole != \"ADMIN\"}">
			<div class="text-muted" href="#">all rights reserved</div>
		</c:if>
	</div>
</div>
<br/>
</footer>