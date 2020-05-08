<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Event</h1>
<div class="container">
	<form:form class="form-horizontal"
		action="${pageContext.servletContext.contextPath}/actions/create-event"
		method="POST">
		
		<table class="table table-dark table-striped">
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="name" placeholder="Enter name" /></td>
				<td><form:errors path="name"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input type="text" class="form-control"
						path="description" placeholder="Enter name"/></td>
				<td><form:errors path="description"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="type">Type:</form:label></td>
				<form:select class="form-control" path="type">
					 <option value="Congress">Congress</option>
					 <option value="Conference">Conference</option>
					 <option value="Seminar">Seminar</option>
				</form:select>
				<td><form:errors path="type"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="location">Location</form:label></td>
				<td><form:input type="text" class="form-control"
						path="location" placeholder="Enter name"/></td>
				<td><form:errors path="location"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="beginDate">Begin date (format: aaaa-mm-jj)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="beginDate" placeholder="2000-01-01"/></td>
				<td><form:errors path="beginDate" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="endDate">End date (format: aaaa-mm-jj)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="endDate" placeholder="2000-01-01"/></td>
				<td><form:errors path="endDate" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="speakers">Speakers</form:label></td>
				<td><form:input type="text" class="form-control"
						path="location" placeholder="Speakers names"/></td>
				<td><form:errors path="speakers"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="attendeeCap">Attendee number cap</form:label></td>
				<td><form:input type="number" class="form-control"
						path="attendeeCap" placeholder="Attendee cap"/></td>
				<td><form:errors path="attendeeCap"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="fee">fee</form:label></td>
				<td><form:input type="number" class="form-control"
						path="fee" placeholder="fee"/></td>
				<td><form:errors path="fee"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
		</table>
	</form:form>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>