<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${!disableMenu}">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="actions/list">JEEDA</a>
			</div>
	
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when
							test="${fn:contains(pageContext.request.requestURI, '/list.')}">
							<li class="active"><a href="actions/list">List</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="actions/list">List</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${fn:contains(pageContext.request.requestURI, '/profile.')}">
							<li class="active"><a href="actions/profile">Profile</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="actions/profile">Profile</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty userId}">
							<li><a href="login.jsp" class="text-success"><span
									class="glyphicon glyphicon-log-in text-info"></span> Login</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="actions/logout" class="text-danger"><span
									class="glyphicon glyphicon-log-out text-danger"></span> Logout</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				<c:if test="${not empty userId}">
					<form action="actions/search" method="GET" class="navbar-form navbar-right">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search teams"
							name="search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</c:if>
			</div>
		</div>
	</nav>
</c:if>