<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Event</h1>
<div class="container">
	<div class="card">
	  <div class="card-header"><c:out value="${event.eventName}" default="name::TEST"/></div>
	  <div class="card-body"><c:out value="${event.description}" default="description::TEST"/></div>
	</div>
	<div class="d-flex justify-content-around bd-highlight">
	<c:choose>
		<c:when test="${fees == 0}">
			<div class="p-2">
		    	<a href="actions/join-event?id=${event.eventId}">
					<button type="button" class="btn btn-info pull-right">Join</button>
				</a>
			</div>
		</c:when>
	    <c:otherwise>
	    	<div class="p-2">
		    	<a href="actions/pay-event?id=${event.eventId}">
					<button type="button" class="btn btn-info pull-right">Pay to join</button>
				</a>
			</div>
	    </c:otherwise>
	 </c:choose>
	 
    <c:if test="${(researcher.lab.labId == event.organizer.lab.labId && researcher.role == 'ORGANIZER') || researcher.role == 'ADMIN'}">
	    <div class="p-2">
			<a href="actions/edit-event?id=${event.eventId}">
				<button type="button" class="btn btn-info pull-right">Edit</button>
			</a>
		</div>
	</c:if>
	 </div>
	 <div class="card">
	  <div class="card-header">Involved</div>
		  <div class="card-body">
			 <table class="table table-dark table-striped">
				<c:forEach items="${researchers}" var="p">
					<tr>
					    <td><c:out value="${p.firstName}" /></td>
						<td><c:out value="${p.lastName}" /></td>
						<td>
							<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/actions/profile?id=${p.id}" role="button">View</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>