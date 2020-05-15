<c:if test="${empty userId}">
	<c:redirect url="/login.jsp" />
</c:if>