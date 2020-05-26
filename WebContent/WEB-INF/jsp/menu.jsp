<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${!disableMenu}">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand"><i class="fas fa-crow" style="color: SkyBlue;"></i></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="<%=application.getContextPath()%>/actions/events">Events</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="<%=application.getContextPath()%>/actions/archives">Archived events</a>
				</li>
			</ul>
			<ul class="navbar-nav mr-inline">
				<li class="nav-item"><a class="nav-link" href="<%=application.getContextPath()%>/actions/profile">Profile</a></li>
				<c:choose>
					<c:when test="${empty userId}">
						<li class="nav-item"><a class="nav-link" href="<%=application.getContextPath()%>">Log in</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="<%=application.getContextPath()%>/actions/logout">Log out</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
</c:if>
<c:if test="${not empty userId}">
	<div class="alert alert-info alert-dismissible fade show" role="alert">
	  <strong>Notification : </strong>
	  <c:out value="Your id is ${userId} ; your role is ${userRole}" default="---"/>
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
</c:if>
