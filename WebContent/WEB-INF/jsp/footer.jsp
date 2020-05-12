<footer class="footer">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="#">Terms of service</a>
				</li>
			</ul>
			<c:if test="${user.type == \"Administrator\"}">
				<ul class="navbar-nav mr-inline">
					<li class="nav-item"><a class="nav-link" href="#">Admin panel</a></li>
				</ul>
			</c:if>
		</div>
	</nav>
</footer>