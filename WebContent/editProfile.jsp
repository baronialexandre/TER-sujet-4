<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${not empty editProfileNotification}">
	<div>
		<div class="alert alert-info alert-dismissible fade show"
			role="alert">
			<strong>Info : </strong>
			<c:out value="${editProfileNotification}" default="---" />
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			session.removeAttribute("editProfileNotification");
		%>
	</div>
</c:if>
<h1>Edit profile</h1>
<div class="container">
	<hr />
	<h2>Informations</h2>
	<form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-password-profile" method="POST">
		<input type="hidden" name="researcherId" value="${researcher.researcherId}"/>
		<div class="card">
			<div class="card-header">Change password <sup style="opacity:0.6">Optional</sup></div>
			<div class="card-body">
				<table class="table table-striped">
					<tr>
						<td>New password</td>
						<td><input type="password" name="pass1" class="form-control" placeholder="Enter password" required/></td>
					</tr>
					<tr>
						<td>Confirm new password</td>
						<td><input type="password" name="pass2" class="form-control" placeholder="Enter password" required/></td>
					</tr>
				</table>
				<br />
				<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
			</div>
		</div>
	</form>
	
	<hr />
	
	<form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-mail-profile" method="POST">
		<input type="hidden" name="researcherId" value="${researcher.researcherId}"/>
		<div class="card">
			<div class="card-header">Change email <sup style="opacity:0.6">Optional</sup></div>
			<div class="card-body">
				<table class="table table-striped">
					<tr>
						<td>New email</td>
						<td><input type="email" name="email1" class="form-control" placeholder="Enter email" value="${researcher.email}" required/></td>
					</tr>
					<tr>
						<td>Confirm new email</td>
						<td><input type="email" name="email2" class="form-control" placeholder="Enter email" value="${researcher.email}" required/></td>
					</tr>
				</table>
				<br />
				<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
			</div>
		</div>
	</form>
		
	<hr />
	
	<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-other-profile?researcherId=${researcher.researcherId}" method="POST" modelAttribute="researcher">
		<div class="card">
			<div class="card-header">Other <sup style="opacity:0.6">Optional</sup></div>
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
						<td><form:label path="birthDay">Birth Day (format: yyyy-mm-dd)</form:label></td>
						<td><form:input type="datetime" class="form-control" path="birthDay" placeholder="2000-01-01" value="${researcher.birthDay}"/></td>
						<td><form:errors path="birthDay" cssClass="alert alert-warning" element="div" /></td>
					</tr>
				</table>
				<br />
				<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
			</div>
		</div>
	</form:form>
	
	<c:if test="${userRole == \"ADMIN\"}">
	<hr />
		<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/change-admin-profile?researcherId=${researcher.researcherId}" method="POST" modelAttribute="researcher">
			<div class="card">
				<div class="card-header">Admin edition <sup style="opacity:0.6">Optional</sup></div>
					<div class="card-body">
					<table class="table table-striped">
						<tr>
							<td><form:label path="lab.labId">Laboratory</form:label></td>
							<td>
								<ul>
									<form:select class="form-control" path="lab.labId">
                						<form:options items="${labs}" />
						            </form:select>
								</ul>
							</td>
							<td><form:errors path="lab.labId" cssClass="alert alert-warning" element="div" /></td>
						</tr>
						<tr>
							<td><form:label path="role">Role</form:label></td>
							<td>
								<ul>
									<form:select class="form-control" path="role">
						                <form:option value="USER">User</form:option>
						                <form:option value="ORGANIZER">Organizer</form:option>
						                <form:option value="ADMIN">Admin</form:option>
						            </form:select>
								</ul>
							</td>
							<td><form:errors path="role" cssClass="alert alert-warning" element="div" /></td>
						</tr>
					</table>
					<br />
					<div class="col-xs-8" style="min-width: 200px; text-align: right;"> <button name="submit" class="btn btn-success pull-right">Save</button></div>
				</div>
			</div>
		</form:form>
	</c:if>
	<c:set var="disableMenu" scope="session" value="${false}">
	</c:set>
</div>