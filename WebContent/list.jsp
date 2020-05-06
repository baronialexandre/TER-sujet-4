<%@ include file="WEB-INF/jsp/protection.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container-fluid">
	<h1>List</h1>
	<div id="accordion" class="panel-group">
		<c:forEach items="${teams}" var="tm">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse-${tm.teamId}">Team ${tm.teamName} - ${fn:length(tm.members)} members</a>
					</h4>
				</div>
				<div id="collapse-${tm.teamId}" class="panel-collapse collapse">

					<c:forEach items="${tm.members}" var="mb">
						<div class="panel-body">
							<a href="actions/profile?personId=${mb.personId}">${mb.firstName} ${mb.lastName}</a>
						</div>
					</c:forEach>

				</div>
			</div>
		</c:forEach>

	</div>
</div>


<!--  TEST -->
<!-- --
<div class="container-fluid">
	<h2>List</h2>
	<div id="accordion2" class="panel-group">

		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseTEST">Group : TEST</a>
				</h4>
			</div>
			<div id="collapseTEST" class="panel-collapse collapse">
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
			</div>
		</div>

		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseTEST2">Group : TEST2</a>
				</h4>
			</div>
			<div id="collapseTEST2" class="panel-collapse collapse">
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
				<div class="panel-body">
					<a href="card/TEST.htm">TEST : TEST TEST</a>
				</div>
			</div>
		</div>

	</div>
</div>
<!---->