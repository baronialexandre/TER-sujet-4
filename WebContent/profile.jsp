<%@ include file="WEB-INF/jsp/protection.jsp"%>

<h1>Profile</h1>
<div class="container">
	<div class="d-flex justify-content-between">
		<h2>Informations</h2>
		<div>
			<c:if test="${researcher.researcherId == userId || userRole == \"ADMIN\"}">
				<a href="actions/edit-profile?id=${researcher.researcherId}">
					<button type="button" class="btn btn-info pull-right">Edit</button>
				</a>
			</c:if>
		</div>
	</div>
	
	<table class="table table-dark table-striped">
		<tr>
			<td>Role</td>
			<td><c:out value="${researcher.role}" default="role::TEST" /></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><c:out value="${researcher.firstName}"
					default="firstName::TEST" /></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><c:out value="${researcher.lastName}"
					default="lastName::TEST" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><c:out value="${researcher.email}" default="email::TEST" /></td>
		</tr>
		<tr>
			<td>Birth day</td>
			<td><c:out value="${researcher.birthDay}"
					default="birthDay::TEST" /></td>
		</tr>
		<tr>
			<td>Website</td>
			<td><c:out value="${researcher.website}" default="website::TEST" /></td>
		</tr>
		<tr>
			<td>Laboratory</td>
			<td><c:out value="${researcher.lab.labName}" default="Laboratory::TEST" /></td>
		</tr>
	</table>
</div>