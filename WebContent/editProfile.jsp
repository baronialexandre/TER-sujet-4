<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Edit profile</h1>
<div class="container">
	<h2>Informations</h2>
	<form:form class="form-horizontal"
		action="${pageContext.servletContext.contextPath}/actions/save-profile?id=${researcher.researcherId}"
		method="POST" modelAttribute="researcher">
		<form:errors path="*" cssClass="alert alert-danger" element="div" />

		<div class="card">
			<div class="card-header">Optional change password</div>
			<div class="card-body">
				<table class="table table-dark table-striped">
					<tr>
						<td><form:label path="password">New password</form:label></td>
						<td><form:input type="password" class="form-control"
								path="password" placeholder="Enter password" value="" /></td>
						<td><form:errors path="password"
								cssClass="alert alert-warning" element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Confirm new password</form:label></td>
						<td><form:input type="password" class="form-control"
								path="password" placeholder="Enter password" value="" /></td>
						<td><form:errors path="password"
								cssClass="alert alert-warning" element="div" /></td>
					</tr>
				</table>
				<br />
			</div>
		</div>

		<div class="card">
			<div class="card-header">Optional change Email</div>
			<div class="card-body">
				<table class="table table-dark table-striped">
					<tr>
						<td><form:label path="email">New Email</form:label></td>
						<td><form:input type="email" class="form-control"
								path="email" placeholder="Enter email" value="" /></td>
						<td><form:errors path="email" cssClass="alert alert-warning"
								element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="email">Confirm new Email</form:label></td>
						<td><form:input type="email" class="form-control"
								path="email" placeholder="Enter email" value="" /></td>
						<td><form:errors path="email" cssClass="alert alert-warning"
								element="div" /></td>
					</tr>
				</table>
				<br />
			</div>
		</div>

		<table class="table table-dark table-striped">
			<tr>
				<td><form:label path="firstName">First name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="firstName" placeholder="Enter first name"
						value='<c:out value="${researcher.firstName}" default="firstName::TEST" />' /></td>
				<td><form:errors path="firstName"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="lastName" placeholder="Enter last name"
						value='<c:out value="${researcher.lastName}" default="lastName::TEST" />' /></td>
				<td><form:errors path="lastName" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="website">Website</form:label></td>
				<td><form:input type="text" class="form-control" path="website"
						placeholder="Enter website address"
						value='<c:out value="${researcher.website}" default="website::TEST" />' /></td>
				<td><form:errors path="website" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="birthDay">Birth Day (format: aaaa-mm-jj)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="birthDay" placeholder="2000-01-01"
						value='<c:out value="${researcher.birthDay}" default="birthDay::TEST" />' /></td>
				<td><form:errors path="birthDay" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>

		</table>
		<button name="submit" class="btn btn-success pull-right">Save</button>
	</form:form>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>