<%@ include file="WEB-INF/jsp/protection.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />

<div class="container-fluid">
	<div class="d-flex justify-content-between">
		<h2>Archived events</h2>
		<div>
			<form action="<%=application.getContextPath()%>/actions/archives"
				method="GET" class="navbar-form navbar-right">
				<div class="form-row">
					<div class="form-group border rounded">
						<c:choose>
							<c:when test="${empty year}">
								<c:set var="yearValue" value="${currentYear}" />
							</c:when>
							<c:otherwise>
								<c:set var="yearValue" value="${year}" />
							</c:otherwise>
						</c:choose>
						<input type="number" class="form-control" min="1900"
							max="${currentYear}" step="1" value="${yearValue}" name="year">
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
	<hr />
	<div>

		<c:if test="${empty events}">
			<div class="alert alert-primary" role="alert">
				<h4 class="alert-heading">Oops!</h4>
				<p>We found no events for this year!</p>
			</div>
		</c:if>

		<c:forEach items="${events}" var="event">
			<c:choose>
				<c:when test="${event.type == \"CONGRESS\"}">
					<c:set var="typeColor" value="primary" />
				</c:when>
				<c:when test="${event.type == \"CONFERENCE\"}">
					<c:set var="typeColor" value="danger" />
				</c:when>
				<c:when test="${event.type == \"SEMINAR\"}">
					<c:set var="typeColor" value="success" />
				</c:when>
				<c:otherwise>
					<c:set var="typeColor" value="muted" />
				</c:otherwise>
			</c:choose>
			<div class="card border-${typeColor} mb-3">
				<div class="card-header" style="display: flex;">
					<h2 class="card-text" style="flex: none;">
						<c:out value="${event.eventName}" default="Test::Name" />
					</h2>
					<p class="card-text text-${typeColor}"
						style="flex: auto; margin-left: 1em;">
						<c:out value="${event.type}" default="Test::Seminaire" />
					</p>
					<p class="card-text" style="flex: auto; text-align: right;">
						<c:out value="${event.beginDate}" default="Test::03/09/2020" />
					</p>
				</div>
				<div class="card-body" style="display: flex;">
					<p class="card-text" style="flex: auto;">
						<c:out value="${event.organizer.lab.labName}" default="No labo" />
					</p>
					<p class="card-text" style="flex: auto; text-align: right;">
						<c:out value="${event.location}" default="Test::Lieu" />
					</p>
				</div>
				<div class="card-footer" style="display: flex;">
					<p class="card-text" style="flex: auto;">
						<c:out value="${fn:length(event.attendees)}" default="Test::0" />
						/
						<c:out value="${event.attendeeCap}" default="Test::15" />
					</p>
					<a
						href="${pageContext.servletContext.contextPath}/actions/eventdetail?eventId=${event.eventId}">
						<button type="button"
							class="btn btn-outline-${typeColor} btn-sm text-dark"
							style="flex: auto;">
							More
							<c:choose>
								<c:when
									test="${(researcher.lab.labId == event.organizer.lab.labId && researcher.role == 'ORGANIZER') || (userRole == 'ADMIN')}">
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