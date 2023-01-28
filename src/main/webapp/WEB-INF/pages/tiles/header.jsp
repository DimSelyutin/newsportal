<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

		<head>
			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="localization.local" var="loc" />
			<fmt:message bundle="${loc}" key="local.mainbtn" var="mainbtn" />
			<fmt:message bundle="${loc}" key="local.newsbtn" var="newsbtn" />
			<fmt:message bundle="${loc}" key="local.aboutbtn" var="aboutbtn" />
			<fmt:message bundle="${loc}" key="local.management" var="management" />
			<fmt:message bundle="${loc}" key="local.signin" var="signin" />
            <fmt:message bundle="${loc}" key="local.signout" var="signout" />
			<fmt:message bundle="${loc}" key="local.signup" var="signup" />
		</head>
		<h2>
			<c:out value="${management}" />
		</h2>
		<div class="burger_menu">
			<a href="controller?command=go_to_news">
				<c:out value="${mainbtn}" />
			</a>
			<a href="">
				<c:out value="${newsbtn}" />
			</a>
			<a href="">
				<c:out value="${aboutbtn}" />
			</a>
			<c:out value="${sessionScope.local}" />


		</div>
		<div class="header_container">

			<div class="language">
				<form action="controller?command=change_local" method="post">
						<input type="hidden" name="local" value="ru" />
					<a class="language-button">
						<input class="b_white" type="submit" value="RU" />
					</a>
				</form>

				<form action="controller?command=change_local" method="post">
						<input type="hidden" name="local" value="en" />
					<a class="language-button">
						<input class="b_black" type="submit" value="EN" />
					</a>
				</form>
			</div>
			<!-- ///menu with authorization -->
			<c:if test="${not (sessionScope.user eq 'active')}">
				<div class="header_button">
					<button class="signin"><c:out value="${signin}" /></button>
					<button class="signup"><c:out value="${signup}" /></button>
				</div>

			</c:if>

			<c:if test="${sessionScope.user eq 'active'}">
				<div class="header_buttons">

					<c:import url="/WEB-INF/pages/tiles/lkHeader.jsp" />

					<form action="controller" method="post">
						<input type="hidden" name="command" value="do_sign_out" />
						<div class="header_button">
							<input class="button" type="submit" value="${signout}" /><br />
						</div>
					</form>
				</div>
			</c:if>
		</div>