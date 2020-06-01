<%@ include file="WEB-INF/jsp/protection.jsp"%>

<h1>Payment page</h1>
<div class="container">
	<hr />

	<i class="fas fa-credit-card fa-10x" style="color: Grey;"></i>

	<div class="d-flex justify-content-around bd-highlight">
		<div class="p-2">
			<a
				href="${pageContext.servletContext.contextPath}/actions/joinevent?eventId=${event.eventId}">
				<button type="button" class="btn btn-info pull-right">Pay</button>
			</a>
		</div>
		<div class="p-2">
			<a
				href="${pageContext.servletContext.contextPath}/actions/eventdetail?eventId=${event.eventId}">
				<button type="button" class="btn btn-info pull-right">Cancel</button>
			</a>
		</div>
	</div>

	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>