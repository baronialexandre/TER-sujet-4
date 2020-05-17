<c:if test="${empty userId}">
	<c:redirect url="redirect:<%=application.getContextPath()%>/login" />
</c:if>