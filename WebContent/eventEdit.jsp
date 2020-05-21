<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>Event</h1>
<div class="container">
	<form:form class="form-horizontal"
		action="${pageContext.servletContext.contextPath}/actions/edit-event?eventId=${event.eventId}"
		method="POST" modelAttribute="event">
		
		<table class="table table-dark table-striped">
			<tr>
				<td><form:label path="eventName">Name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="eventName" placeholder="Enter name"
						value='${event.eventName}' /></td>
				<td><form:errors path="eventName"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input type="text" class="form-control"
						path="description" placeholder="Enter description"
						value='${event.description}' /></td>
				<td><form:errors path="description"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="type">Type:</form:label></td>
				<td><form:select class="form-control" path="type">
					 <form:options/>
				</form:select></td>
				<td><form:errors path="type"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="location">Location</form:label></td>
				<td><form:input type="text" class="form-control"
						path="location" placeholder="Enter name"
						value='${event.location}' /></td>
				<td><form:errors path="location"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="beginDate">Begin date (format: aaaa-mm-jj)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="beginDate" placeholder="2000-01-01"
						value='${event.beginDate}' /></td>
				<td><form:errors path="beginDate" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="endDate">End date (format: aaaa-mm-jj)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="endDate" placeholder="2000-01-01"
						value='${event.endDate}' /></td>
				<td><form:errors path="endDate" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="attendeeCap">Attendee number cap</form:label></td>
				<td><form:input type="number" class="form-control"
						path="attendeeCap" placeholder="Attendee cap"
						value='${event.attendeeCap}' /></td>
				<td><form:errors path="attendeeCap"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="fee">fee</form:label></td>
				<td><form:input type="number" class="form-control"
						path="fee" placeholder="fee"
						value='${event.fee}' /></td>
				<td><form:errors path="fee"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
		</table>
		
		<button name="submit" class="btn btn-success pull-right">Save</button>

		<div class="card">
		<div class="card-header">Involved (<c:out value="${fn:length(event.attendees)}" />/<c:out value="${event.attendeeCap}" />)</div>
		<div class="card-body">
			<table class="table table-dark table-striped">
				<c:forEach items="${event.attendees}" var="p">
					<tr>
						<td><c:out value="${p.firstName}" /></td>
						<td><c:out value="${p.lastName}" /></td>
						<td><a class="btn btn-primary"
							href="${pageContext.servletContext.contextPath}/actions/profile?researcherId=${p.researcherId}"
							role="button">View</a></td>
						<td>
							<a class="btn btn-primary"
							href="${pageContext.servletContext.contextPath}/actions/event-remove-user?researcherId=${p.researcherId}"
							role="button">Remove</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</form:form>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>