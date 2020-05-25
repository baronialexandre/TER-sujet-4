<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Edit profile</h1>
<div class="container">
	<h2>Informations</h2>
	<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-password-profile?researcherId=${researcher.researcherId}" method="POST" modelAttribute="researcher">
		<form:errors path="*" cssClass="alert alert-danger" element="div" />
		<div class="card">
			<div class="card-header">Change password <sup style="opacity:0.6">Optional</sup></div>
			<div class="card-body">
				<table class="table table-striped">
					<tr>
						<td><form:label path="password">New password</form:label></td>
						<td><form:input type="password" class="form-control" path="" placeholder="Enter password" value="" /></td>
						<td><form:errors path="password" cssClass="alert alert-warning" element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Confirm new password</form:label></td>
						<td><form:input type="password" class="form-control" path="" placeholder="Enter password" value="" /></td>
						<td><form:errors path="password" cssClass="alert alert-warning" element="div" /></td>
					</tr>
				</table>
				<br />
				<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
			</div>
		</div>
	</form:form>
	
	<hr />
	
	<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-mail-profile?researcherId=${researcher.researcherId}" method="POST" modelAttribute="researcher">
		<form:errors path="*" cssClass="alert alert-danger" element="div" />
		<div class="card">
			<div class="card-header">Change email <sup style="opacity:0.6">Optional</sup></div>
			<div class="card-body">
				<table class="table table-striped">
					<tr>
						<td><form:label path="email">New email</form:label></td>
						<td><form:input type="email" class="form-control" path="email" placeholder="Enter email" value="" /></td>
						<td><form:errors path="email" cssClass="alert alert-warning" element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="email">Confirm new email</form:label></td>
						<td><form:input type="email" class="form-control" path="email" placeholder="Enter email" value="" /></td>
						<td><form:errors path="email" cssClass="alert alert-warning" element="div" /></td>
					</tr>
				</table>
				<br />
				<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
			</div>
		</div>
	</form:form>
		
	<hr />
	
	<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-other-profile?researcherId=${researcher.researcherId}" method="POST" modelAttribute="researcher">
		<div class="card">
			<div class="card-header">Other</div>
				<div class="card-body">
				<table class="table table-striped">
					<tr>
						<td><form:label path="firstName">First name</form:label></td>
						<td><form:input type="text" class="form-control" path="firstName" placeholder="Enter first name" value='${researcher.firstName}' /></td>
						<td><form:errors path="firstName" cssClass="alert alert-warning" element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="lastName">Last name</form:label></td>
						<td><form:input type="text" class="form-control" path="lastName" placeholder="Enter last name" value='${researcher.lastName}' /></td>
						<td><form:errors path="lastName" cssClass="alert alert-warning" element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="website">Website</form:label></td>
						<td><form:input type="text" class="form-control" path="website" placeholder="Enter website address" value='${researcher.website}' /></td>
						<td><form:errors path="website" cssClass="alert alert-warning" element="div" /></td>
					</tr>
					<tr>
						<td><form:label path="birthDay">Birth Day (format: aaaa-mm-jj)</form:label></td>
						<td><form:input type="datetime" class="form-control" path="birthDay" placeholder="2000-01-01" value="${researcher.birthDay}"/></td>
						<td><form:errors path="birthDay" cssClass="alert alert-warning" element="div" /></td>
					</tr>
				</table>
				<br />
				<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
			</div>
		</div>
	</form:form>
	
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>