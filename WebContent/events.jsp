<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--  TEST -->
<div class="container-fluid">
	<h2>Events</h2>
	<div>
		<c:forEach items="${events}" var="event">
			<c:choose>
				<c:when test="${event.type == 'Congress'}">
					<!--primary-->
					<c:set var="typeColor" scope="request" value="primary" />
					<c:out value="${salary}" />
				</c:when>
				<c:when test="${event.type == 'Conference'}">
					<!--danger-->
					<c:set var="typeColor" value="danger" />
				</c:when>
				<c:when test="${event.type == 'Seminar'}">
					<!--success-->
					<c:set var="typeColor" value="success" />
				</c:when>
				<c:otherwise>
					<!-- muted -->
					<c:set var="typeColor" value="muted" />
				</c:otherwise>
			</c:choose>
			<div class="card border-${typeColor} mb-3">
				<div class="card-header" style="display: flex;">
					<h2 class="card-text" style="flex: none;">
						<c:out value="${event.name}" default="Test::Name" />
					</h2>
					<p class="card-text" style="flex: auto; margin-left: 1em;">
						<c:out value="${event.type}" default="Test::Seminaire" />
					</p>
					<p class="card-text" style="flex: auto; text-align: right;">
						<c:out value="${event.beginDate}" default="Test::03/09/2020" />
					</p>
				</div>
				<div class="card-body text-${typeColor}" style="display: flex;">
					<p class="card-text" style="flex: auto;">
						<c:out value="${event.organizer.lab}" default="Test::Labo" />
					</p>
					<p class="card-text" style="flex: auto; text-align: right;">
						<c:out value="${event.location}" default="Test::Lieu" />
					</p>
				</div>
				<div class="card-footer text-${typeColor}" style="display: flex;">
					<p class="card-text" style="flex: auto;">
						<c:out value="${fn:length(event.attendees)}" default="Test::0" />
						/
						<c:out value="${event.attendeeCap}" default="Test::15" />
					</p>
					<a href="#"> <!-- LIEN "MORE" -->
						<button type="button" class="btn btn-outline-${typeColor} btn-sm"
							style="flex: auto;">
							More
							<c:choose>
								<c:when test="${researcher.lab.id == event.organizer.lab.id && ( researcher.role == 'organizer' || researcher.role == 'admin')}">
									<i class="far fa-edit"></i>
								</c:when>
								<c:otherwise>
									<i class="fas fa-plus"></i>
								</c:otherwise>
							</c:choose>
						</button>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<!---->