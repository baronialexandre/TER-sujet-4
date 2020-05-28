<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Register</h1>
<div class="container">
	<hr />
	<h2>Informations</h2>
	<hr />
	<form:form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/register-account" method="POST" modelAttribute="researcher">
		<form:errors path="*" cssClass="alert alert-danger" element="div" />
		<table class="table table-striped">
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input type="email" class="form-control" path="email"
						placeholder="Enter email" /></td>
				<td><form:errors path="email" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input type="password" class="form-control"
						path="password" placeholder="Enter password" /></td>
				<td><form:errors path="password" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="firstName">First name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="firstName" placeholder="Enter first name" /></td>
				<td><form:errors path="firstName"
						cssClass="alert alert-warning" element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last name</form:label></td>
				<td><form:input type="text" class="form-control"
						path="lastName" placeholder="Enter last name" /></td>
				<td><form:errors path="lastName" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="website">Website(optional)</form:label></td>
				<td><form:input type="text" class="form-control" path="website"
						placeholder="Enter website address" /></td>
				<td><form:errors path="website" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
			<tr>
				<td><form:label path="birthDay">Birth Day (format: yyyy-mm-dd)</form:label></td>
				<td><form:input type="datetime" class="form-control"
						path="birthDay" placeholder="yyyy-mm-dd" /></td>
				<td><form:errors path="birthDay" cssClass="alert alert-warning"
						element="div" /></td>
			</tr>
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
		</table>
		<button name="submit" class="btn btn-success pull-right">Register</button>
	</form:form>

</div>