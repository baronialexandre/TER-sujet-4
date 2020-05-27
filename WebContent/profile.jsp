<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>Profile</h1>
<div class="container">
	<div class="d-flex justify-content-between">
		<h2>Informations</h2>
		<div>
			<c:if
				test="${researcher.researcherId == userId || userRole == \"ADMIN\"}">
				<a
					href="<%=application.getContextPath()%>/actions/edit-profile?researcherId=${researcher.researcherId}">
					<button type="button" class="btn btn-info pull-right">Edit</button>
				</a>
			</c:if>
		</div>
	</div>

	<table class="table table-striped">
		<tr>
			<td>Role</td>
			<td><c:out value="${researcher.role}" default="role::ERROR" /></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><c:out value="${researcher.firstName}"
					default="firstName::ERROR" /></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><c:out value="${researcher.lastName}"
					default="lastName::ERROR" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><c:out value="${researcher.email}" default="email::ERROR" /></td>
		</tr>
		<tr>
			<td>Birth day</td>
			<td><c:out value="${researcher.birthDay}"
					default="birthDay::TEST" /></td>
		</tr>
		<tr>
			<td>Website</td>
			<td><c:out value="${researcher.website}" default="No website" /></td>
		</tr>
		<tr>
			<td>Laboratory</td>
			<td><c:out value="${researcher.lab.labName}" default="No labo" /></td>
		</tr>
	</table>
	
	<div class="card-header">Your in coming events</div>
		<div class="card-body">
			<table class="table table-light table-striped">
				<c:forEach items="${eventsInc}" var="e">
					<tr>
						<td><c:out value="${e.eventName}" /></td>
						<td><c:out value="${e.type}" /></td>
						<td><c:out value="${e.beginDate}" /></td>
						<td><a class="btn btn-primary"
							href="<%=application.getContextPath()%>/actions/eventdetail?eventId=${e.eventId}"
							role="button">View</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="card-header">Events you attended</div>
		<div class="card-body">
			<table class="table table-light table-striped">
				<c:forEach items="${eventsAttended}" var="e">
					<tr>
						<td><c:out value="${e.eventName}" /></td>
						<td><c:out value="${e.type}" /></td>
						<td><c:out value="${e.beginDate}" /></td>
						<td><a class="btn btn-primary"
							href="<%=application.getContextPath()%>/actions/eventdetail?eventId=${e.eventId}"
							role="button">View</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>