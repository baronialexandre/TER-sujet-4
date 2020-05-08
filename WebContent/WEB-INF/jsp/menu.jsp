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
				<li class="nav-item active"><a class="nav-link" href="#">Events</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Archives</a>
				</li>
			</ul>
			<ul class="navbar-nav mr-inline">
				<li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Connect</a></li>
			</ul>
		</div>
	</nav>
</c:if>