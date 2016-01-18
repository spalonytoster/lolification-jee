<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!--  Meta and CSS -->
	<jsp:include page="${request.contextPath}/WEB-INF/jsp/includes/head.jspf" />
</head>
<body>

	<!-- Navigation bar -->
	<jsp:include page="${request.contextPath}/WEB-INF/jsp/includes/navbar.jspf" />
	<jsp:useBean id="modelBean" class="com.mposluszny.lolesportsapp.web.teams.TeamBean" scope="request" />
	
	<!-- Primary Page Layout -->
	<div class="container wrapper title">
		<div class="row">
			<div class="one column">
				<h2>Teams</h2>
			</div>
		</div>
		<div class="row">
			<div class="twelve columns">
				<form class="editRow" action="team" method="post">
					<input type="hidden" value="true" name="add">
					<div class="row">
						<div class="one-third column">
							<label for="nazwa">Nazwa</label>
						</div>
						<div class="one-third column">
							<label for="region">Region</label>
						</div>
						<div class="one-third column">
							<label for="dataUtworzenia">Data utworzenia</label>
						</div>
					</div>
					<div class="row">
						<div class="one-third column">
							<input type="text" value="${team.name}" id="nazwa" name="name">
						</div>
						<div class="one-third column">
							<input type="text" value="${team.region}" id="region" name="region">
						</div>
						<div class="one-third column">
							<input type="text" value="${team.dateOfEstablishment}" id="dataUtworzenia" name="dateOfEstablishment">
						</div>
					</div>
					<input class="button-primary" type="submit" value="Add">
				</form>
			
				<table class="center table text-center">
					<tbody>
						<tr>
							<th>Lp.</th>
							<th>Nazwa</th>
							<th>Region</th>
							<th>Data utworzenia</th>
							<th>Akcja</th>
						</tr>
						<c:forEach var="team" items="${modelBean.teams}" varStatus="row">
							<tr>
								<td>${row.index+1}</td>
								<td>${team.name}</td>
								<td>${team.region}</td>
								<td>${team.dateOfEstablishment}</td>
								<td class="actionColumn">
									<form class="actionForm" action="team" method="get">
										<button class="actionButton" type="submit" name="view" value="${team.id}">
											<i class="fa fa-eye"></i>
										</button>
										<button class="actionButton" type="submit" name="edit" value="${team.id}">
											<i class="fa fa-edit"></i>
										</button>
									</form>
									<form class="actionForm" action="team" method="post">
										<button class="actionButton" type="submit" name="delete" value="${team.id}">
											<i class="fa fa-close"></i>
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/includes/footer.jspf" />
</body>
</html>
