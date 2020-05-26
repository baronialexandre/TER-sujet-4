<%@ include file="WEB-INF/jsp/protection.jsp"%>

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
			<td><c:out value="${researcher.website}" default="---" /></td>
		</tr>
		<tr>
			<td>Laboratory</td>
			<td><c:out value="${researcher.lab.labName}" default="---" /></td>
		</tr>
	</table>
</div>