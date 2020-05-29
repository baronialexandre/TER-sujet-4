<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>Profile</h1>
<div class="container">
	<hr />
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

	<hr />

	<div class="card-header">Attending these events</div>
	<div class="card-body">
		<button class="btn btn-primary" data-toggle="collapse"
			data-target="#collapse-attending" aria-expanded="true"
			aria-controls="collapse-attending">Show
			(${fn:length(eventsUpcoming)})</button>
		<div id="collapse-attending" class="collapse"
			aria-labelledby="heading-research">
			<div class="card card-body">
				<table class="table table-light table-striped">
					<c:forEach items="${eventsUpcoming}" var="e">
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
	</div>

	<hr />

	<div class="card-header">Events attended</div>
	<div class="card-body">
		<button class="btn btn-primary" data-toggle="collapse"
			data-target="#collapse-attended" aria-expanded="true"
			aria-controls="collapse-attended">Show
			(${fn:length(eventsAttended)})</button>
		<div id="collapse-attended" class="collapse"
			aria-labelledby="heading-research">
			<div class="card card-body">
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
	</div>

	<hr />

	<div class="card-header">Events organized</div>
	<div class="card-body">
		<button class="btn btn-primary" data-toggle="collapse"
			data-target="#collapse-organized" aria-expanded="true"
			aria-controls="collapse-organized">Show
			(${fn:length(eventsOrganized)})</button>
		<div id="collapse-organized" class="collapse"
			aria-labelledby="heading-research">
			<div class="card card-body">
				<table class="table table-light table-striped">
					<c:forEach items="${eventsOrganized}" var="e">
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
	</div>

</div>