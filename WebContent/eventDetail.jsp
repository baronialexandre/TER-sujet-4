<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:useBean id="now" class="java.util.Date" />

<h1>Event</h1>
<div class="container">
	<hr />
	<div class="card">
		<div class="card-header">
			<c:out value="${event.eventName}" default="name::TEST" />
		</div>
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><span
				style="text-decoration: underline;">Speaker(s)</span> : <c:forEach
					items="${event.speakers}" var="speaker">
					<c:out value="${speaker}" default="speakerName::TEST" />; 
				</c:forEach></li>
		</ul>
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><span
				style="text-decoration: underline;">Begin date: </span> <c:out
					value="${event.beginDate}" default="beginDate::TEST" /> ; <span
				style="text-decoration: underline;">End date: </span> <c:out
					value="${event.endDate}" default="endDate::TEST" /> ;</li>
		</ul>
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><span
				style="text-decoration: underline;">Organizer: </span> <a
				href="<%=application.getContextPath()%>/actions/profile?researcherId=${event.organizer.researcherId}">
					<c:out value="${event.organizer.firstName}"
						default="organizer.firstName::TEST" /> <c:out
						value="${event.organizer.lastName}"
						default="organizer.lastName::TEST" />
			</a></li>
		</ul>
		<div class="card-body">
			<c:out value="${event.description}" default="description::TEST" />
		</div>

		<c:choose>
			<c:when test="${event.beginDate lt now}">
				<div class="card-footer text-muted">Registration is over</div>
			</c:when>
			<c:when test="${fn:contains(event.attendees, researcher)}">
				<div class="card-footer text-muted">You are already
					registered.</div>
			</c:when>
			<c:when
				test="${event.organizer.lab.labName == researcher.lab.labName or event.fee == 0.0}">
				<div class="card-footer text-muted">
					Subscription fee if you're not working in the lab
					<c:out value="${event.organizer.lab.labName}" default="lab::TEST" />
					: <b>$<c:out value="${event.fee}" default="lab::TEST" /></b>.
				</div>
			</c:when>
			<c:otherwise>
				<div class="card-footer text-muted">
					Subscription fee : <b>$<c:out value="${event.fee}"
							default="lab::TEST" /></b>.
				</div>
			</c:otherwise>
		</c:choose>

	</div>

	<c:set var="contains" value="false" />
	<c:forEach var="item" items="${event.attendees}">
		<c:if test="${item.researcherId eq researcher.researcherId}">
			<c:set var="contains" value="true" />
		</c:if>
	</c:forEach>



	<div class="d-flex justify-content-around bd-highlight">
		<hr />
		<c:if test="${not empty event.organizer.lab}">
		<c:if test="${not contains}">
			<c:choose>
				<c:when
					test="${event.organizer.lab.labName == researcher.lab.labName or event.fee == 0.0}">
					<div class="p-2">
						<a
							href="${pageContext.servletContext.contextPath}/actions/joinevent?eventId=${event.eventId}">
							<button type="button" class="btn btn-info pull-right">Join</button>
						</a>
					</div>
				</c:when>
				<c:when test="${fn:contains(event.attendees, researcher) or (event.beginDate lt now)}">
				</c:when>
				<c:otherwise>
					<div class="p-2">
						<a
							href="${pageContext.servletContext.contextPath}/actions/payevent?eventId=${event.eventId}">
							<button type="button" class="btn btn-info pull-right">Pay
								to join</button>
						</a>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if
			test="${((researcher.lab.labId == event.organizer.lab.labId) and (userRole == 'ORGANIZER')) || userRole == 'ADMIN'}">
			<div class="p-2">
				<a
					href="${pageContext.servletContext.contextPath}/actions/edit-event?eventId=${event.eventId}">
					<button type="button" class="btn btn-info pull-right">Edit</button>
				</a>
			</div>
		</c:if>
		</c:if>
		<c:if test="${empty event.organizer.lab}">
		<br />
		<span>The laboratory has been deleted, please contact an administrator if this is an error.</span>
		<br />
		</c:if>
	</div>
	<div class="card">
		<div class="card-header">
			Attendees (
			<c:out value="${fn:length(event.attendees)}" />
			/
			<c:out value="${event.attendeeCap}" />
			)
		</div>
		<div class="card-body">
			<table class="table table-light table-striped">
				<c:forEach items="${event.attendees}" var="p">
					<tr>
						<td><c:out value="${p.firstName}" /></td>
						<td><c:out value="${p.lastName}" /></td>
						<td><a class="btn btn-primary"
							href="${pageContext.servletContext.contextPath}/actions/profile?researcherId=${p.researcherId}"
							role="button">View</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>