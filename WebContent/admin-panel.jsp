<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty adminPanelError}">
	<div>
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Error : </strong>
			<c:out value="${adminPanelError}" default="---" />
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			session.removeAttribute("adminPanelError");
		%>
	</div>
</c:if>
<c:if test="${not empty adminPanelSuccess}">
	<div>
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Success : </strong>
			<c:out value="${adminPanelSuccess}" default="---" />
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			session.removeAttribute("adminPanelSuccess");
		%>
	</div>
</c:if>

<div class="container-fluid">
	<div class="d-flex justify-content-between">
		<h2>Admin panel</h2>
		<button onclick="location.href='#footer'" alt="Max-width 10%" class="btn btn-outline-info" type="button"><i class="fas fa-arrow-down"></i></button>
	</div>
	<hr />
	<div>
		<h3>Add laboratory</h3>
		<div>
			<form
				action="<%=application.getContextPath()%>/actions/admin-panel-AddLab"
				method="GET">
				<div class="form-row">
					<div class="form-group border rounded">
						<input type="text" class="form-control" name="labName"
							placeholder="Lab name" required>
					</div>
					<div class="form-group border rounded">
						<button type="submit" class="btn btn-default">
							<i class="fas fa-plus"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<hr />
	<div class="d-flex justify-content-between">
		<h3>Laboratories and researchers</h3>
		<div>
			<form
				action="<%=application.getContextPath()%>/actions/admin-panel-searchResearcher"
				method="GET" class="navbar-form navbar-right">
				<div class="form-row">
					<div class="form-group border rounded">
						<input type="text" class="form-control" name="researcher"
							placeholder="Firstname or lastname">
					</div>
					<div class="form-group border rounded">
						<button type="submit" class="btn btn-default">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>

			</form>
		</div>
	</div>
	<c:if test="${not empty researchersFound}">
		<hr />
		<h4>Researchers found</h4>
		<button class="btn btn-primary" data-toggle="collapse"
			data-target="#collapse-research" aria-expanded="true"
			aria-controls="collapse-research">Show
			(${fn:length(researchersFound)})</button>
		<div id="collapse-research" class="collapse"
			aria-labelledby="heading-research">
			<div class="card card-body">
				<c:forEach items="${researchersFound}" var="rs">
					<a
						href="<%=application.getContextPath()%>/actions/profile?researcherId=${rs.researcherId}">
						<c:out value="${rs.firstName} ${rs.lastName}" default="---" />
					</a>
					<br />
				</c:forEach>
			</div>
		</div>
		<hr />
		<%
			session.removeAttribute("researchersFound");
		%>
	</c:if>

	<div id="accordion">
		<c:forEach items="${labs}" var="lab">
			<div class="card">
				<div class="card-header" id="heading-${lab.labId}">
					<div class="d-flex justify-content-between">
						<h5 class="mb-0">
							<button class="btn btn-link" data-toggle="collapse"
								data-target="#collapse-${lab.labId}" aria-expanded="true"
								aria-controls="collapse-${lab.labId}">Laboratoire :
								${lab.labName} - ${fn:length(lab.researchers)} researchers</button>
						</h5>
						<form
							action="<%=application.getContextPath()%>/actions/admin-panel-removeLab"
							method="GET" class="navbar-form navbar-right">
							<input type="hidden" name="labId" value="${lab.labId}">
							<button type="submit" class="btn btn-outline-danger btn-sm"
								style="margin: 0px;">
								<i class="fas fa-trash"></i>
							</button>
						</form>
					</div>
				</div>

				<div id="collapse-${lab.labId}" class="collapse"
					aria-labelledby="heading-${lab.labId}" data-parent="#accordion">
					<div class="card-body">
						<c:forEach items="${lab.researchers}" var="rs">
							<a
								href="<%=application.getContextPath()%>/actions/profile?researcherId=${rs.researcherId}">
								<c:out value="${rs.lastName} ${rs.firstName}" default="---" />
							</a>
							<br />
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<h4>Researchers with no lab</h4>
	<button class="btn btn-primary" data-toggle="collapse"
		data-target="#collapse-no-lab" aria-expanded="true"
		aria-controls="collapse-no-lab">Show
		(${fn:length(researchersWithoutLab)})</button>
	<div id="collapse-no-lab" class="collapse"
		aria-labelledby="heading-research">
		<div class="card card-body">
			<c:forEach items="${researchersWithoutLab}" var="rs">
				<a
					href="<%=application.getContextPath()%>/actions/profile?researcherId=${rs.researcherId}">
					<c:out value="${rs.firstName} ${rs.lastName}" default="---" />
				</a>
				<br />
			</c:forEach>
		</div>
	</div>

</div>