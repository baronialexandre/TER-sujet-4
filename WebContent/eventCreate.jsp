<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>Create event</h1>
<div class="container">
	<hr />
	<form:form class="form-horizontal"
		action="${pageContext.servletContext.contextPath}/actions/create-event"
		method="POST" modelAttribute="event">

		<table class="table table-light table-striped">
			<tr>
				<td><form:label path="eventName">Name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="eventName" placeholder="Enter name" /></td>
				<td><form:errors path="eventName"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input type="text" class="form-control"
						path="description" placeholder="Enter description" /></td>
				<td><form:errors path="description"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="speakers">Speakers</form:label></td>
				<td><form:input type="text" class="form-control"
						path="speakers" placeholder="[speaker1, speaker2, speaker3]" /></td>
				<td><form:errors path="speakers" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="type">Type:</form:label></td>
				<td><form:select class="form-control" path="type">
						<form:options />
					</form:select></td>
				<td><form:errors path="type" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="location">Location</form:label></td>
				<td><form:input type="text" class="form-control"
						path="location" placeholder="Enter location name" /></td>
				<td><form:errors path="location" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="beginDate">Begin date (format: yyyy-mm-dd)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="beginDate" placeholder="2000-01-01" /></td>
				<td><form:errors path="beginDate"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="endDate">End date (format: yyyy-mm-dd)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="endDate" placeholder="2000-01-01" /></td>
				<td><form:errors path="endDate" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="attendeeCap">Attendee number cap</form:label></td>
				<td><form:input type="number" class="form-control"
						path="attendeeCap" placeholder="Attendee cap" /></td>
				<td><form:errors path="attendeeCap"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="fee">fee</form:label></td>
				<td><form:input type="number" class="form-control" path="fee"
						placeholder="fee" /></td>
				<td><form:errors path="fee" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
		</table>
		<button name="submit" class="btn btn-success pull-right">Create</button>
	</form:form>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>