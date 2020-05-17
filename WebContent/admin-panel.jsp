<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="container-fluid">
	<div class="d-flex justify-content-between">
		<h2>Admin panel</h2>
		<div>
			<form action="actions/searchUser" method="GET" class="navbar-form navbar-right">
				<div class="form-row">
					<div class="form-group border rounded">
						<input type="text" class="form-control" name="search" placeholder="Firstname or lastname">
					</div>
					<div class="form-group border rounded">
						<button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
					</div>
				</div>
				
			</form>
		</div>
	</div>
	<div id="accordion">

		<c:forEach items="${labs}" var="lab">
			<div class="card">
				<div class="card-header" id="heading-${lab.labId}">
					<h5 class="mb-0">
						<button class="btn btn-link" data-toggle="collapse" data-target="#collapse-${lab.labId}" aria-expanded="true" aria-controls="collapse-${lab.labId}">Laboratoire : ${lab.labName} - ${fn:length(lab.researchers)} researchers</button>
					</h5>
				</div>

				<div id="collapse-${lab.labId}" class="collapse"
					aria-labelledby="heading-${lab.labId}" data-parent="#accordion">
					<div class="card-body">
						<c:forEach items="${lab.researchers}" var="rs">
							<a href="<%=application.getContextPath()%>/actions/profile?researcherId=${rs.researcherId}">
								${rs.firstName} ${rs.lastName}
							</a><br />
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>