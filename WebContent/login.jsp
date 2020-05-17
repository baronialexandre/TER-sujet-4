<h1>Login</h1>
<div class="container-fluid">
	<!-- Login Form -->
	<form class="form-horizontal" action="${pageContext.servletContext.contextPath}/actions/login" method="POST" >
		<div class="form-group">
			<div class="col-sm-offset-1 col-xs-8" style="min-width: 200px;">
				<span>Email</span> <input type="email" class="form-control"
					name="email" placeholder="Enter your email" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-1 col-xs-8" style="min-width: 200px;">
				<span>Password</span> <input type="password" class="form-control"
					name="password" placeholder="Enter your password" required>
			</div>
		</div>
		<c:if test="${loginFailed}">
			<label class="alert alert-danger">
				Username or password incorrect
			</label>
		</c:if>	
		<c:set var = "loginFailed" scope = "session" value = "${false}"> </c:set>
		<div class="form-group">
			<div class="col-xs-8" style="min-width: 200px; text-align: right;">
				<button onclick="location.href='${pageContext.servletContext.contextPath}/actions/register'" class="btn btn-info" type="button">Sign in</button>
				<button name="submit" class="btn btn-success">Log in</button>
			</div>
		</div>
	</form>
	<!--  End Form -->
</div>
