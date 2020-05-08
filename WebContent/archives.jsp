<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />

<div class="container-fluid">
	<div class="d-flex justify-content-between">
		<h1>Archives</h1>
		<div>
			<form action="actions/search" method="GET" class="navbar-form navbar-right">
				<div class="form-row">
					<div class="form-group border rounded">
						<input type="number" class="form-control" min="1900" max="${currentYear}" step="1" value="2016" name="search">
					</div>
					<div class="form-group border rounded">
						<button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
					</div>
				</div>
				
			</form>
		</div>
	</div>
	<div>
		
		<c:if test="${empty events}">
		<div class="alert alert-primary">
			<strong>Hey !</strong> Enter a date and we will search past events for you !
		</div>
		</c:if>
	
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
						<c:out value="${fn:length(event.attendies)}" default="Test::0" />
						/
						<c:out value="${event.attendeeCap}" default="Test::15" />
					</p>
					<a href="#"> <!-- LIEN "MORE" -->
						<button type="button" class="btn btn-outline-${typeColor} btn-sm"
							style="flex: auto;">
							More
							<c:choose>
								<c:when test="${personId != event.organizer.id}">
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