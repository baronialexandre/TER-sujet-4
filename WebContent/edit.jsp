<%@ include file="WEB-INF/jsp/protection.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Edit profile</h1>
<div class="container">
	<h2>Informations</h2>
	<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/save-profile?id=${person.personId}" method="POST" modelAttribute="person" >
		<form:errors path="*" cssClass="alert alert-danger" element="div" />
		<table class="table table-dark table-striped">
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input type="text" class="form-control" path="username" 
					placeholder="Enter username" value="${person.username}"/></td>
			    <td><form:errors path="username" cssClass="alert alert-warning" element="div" /></td>
					
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input type="password" class="form-control" path="password" 
					placeholder="Enter password" value="${person.password}"/></td>
			    <td><form:errors path="password" cssClass="alert alert-warning" element="div" /></td>
					
			</tr>
			<tr>
				<td><form:label path="firstName">First name</form:label></td>
				<td><form:input type="text" class="form-control" path="firstName" 
					placeholder="Enter first name" value="${person.firstName}"/></td>
			    <td><form:errors path="firstName" cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last name</form:label></td>
				<td><form:input type="text" class="form-control" path="lastName" 
					placeholder="Enter last name" value="${person.lastName}"/></td>
			    <td><form:errors path="lastName" cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input type="email" class="form-control" path="email" 
					placeholder="Enter email" value="${person.email}"/></td>
			    <td><form:errors path="email" cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="website">Website</form:label></td>
				<td><form:input type="text" class="form-control" path="website" 
					placeholder="Enter website address" value="${person.website}"/></td>
			    <td><form:errors path="website" cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="birthDay">Birth Day (format: aaaa-mm-jj)</form:label></td>
				<td><form:input type="datetime" class="form-control" path="birthDay" 
					placeholder="2000-01-01" value="${person.birthDay}"/></td>
			    <td><form:errors path="birthDay" cssClass="alert alert-warning" element="div" /></td>
			</tr>
		</table>
		<button name="submit" class="btn btn-success pull-right">Save</button>
	</form:form>
	<c:set var = "disableMenu" scope = "session" value = "${false}"> </c:set>

	
</div>