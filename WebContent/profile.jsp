<%@ include file="WEB-INF/jsp/protection.jsp"%>

<h1>Profile</h1>
<div class="container">
	<h2>Informations</h2>
	<table class="table table-dark table-striped">
		<tr>
			<td>Username</td>
			<td><c:out value="${person.username}" default="Username::TEST" /></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><c:out value="${person.firstName}" default="firstName::TEST" /></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><c:out value="${person.lastName}" default="lastName::TEST" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><c:out value="${person.email}" default="email::TEST" /></td>
		</tr>
		<tr>
			<td>Website</td>
			<td><c:out value="${person.website}" default="website::TEST" /></td>
		</tr>
		<tr>
			<td>Birth day</td>
			<td><c:out value="${person.birthDay}" default="birthDay::TEST" /></td>
		</tr>
	</table>
	<c:if test="${person.personId == userId}">
		<a href="actions/edit-profile?id=${person.personId}">
			<button type="button" class="btn btn-info pull-right">Edit</button>
		</a>
	</c:if>
</div>