<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="container-fluid">
	<h1>Admin panel</h1>

	<div id="accordion">

		<c:forEach items="${laboratories}" var="lab">

			<div class="card">
				<div class="card-header" id="heading-${lab.labId}">
					<h5 class="mb-0">
						<button class="btn btn-link" data-toggle="collapse" data-target="#collapse-${lab.labId}" aria-expanded="true" aria-controls="collapse-${lab.labId}">Team ${lab.name} - ${fn:length(lab.members)} members</button>
					</h5>
				</div>

				<div id="collapse-${lab.labId}" class="collapse"
					aria-labelledby="heading-${lab.labId}" data-parent="#accordion">
					<div class="card-body">
						<c:forEach items="${lab.members}" var="mb">
							<a href="actions/profile?personId=${mb.personId}">
								${mb.firstName} ${mb.lastName}
							</a><br />
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>