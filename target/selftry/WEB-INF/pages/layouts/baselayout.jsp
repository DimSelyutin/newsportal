<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<!DOCTYPE HTML>
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<script src="script/jquery.min.js"></script>
			<script type="text/javascript" src="script/script.js"></script>

			<title>locale.linkname.headertitle
				<!-- <bean:message key="locale.linkname.headertitle" />
 -->
			</title>
			<link rel="stylesheet" type="text/css" href="styles/style.css">
		</head>

		<body>
			<div class="header">
				<c:import url="/WEB-INF/pages/tiles/header.jsp" />
			</div>
			<div class="first-page">
				<h1>News Blog</h1>
			</div>
			<div class="content">
				<div class="base-layout-wrapper">

					<div class="menu">

						<c:if test="${not (sessionScope.user eq 'active')}">
							Welcome!!!!!
							<%-- <c:import url="">
								</c:import> --%>
						</c:if>

						<c:if test="${sessionScope.user eq 'active'}">
							<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
						</c:if>
					</div>

					<div class="content_news">

						<c:if test="${not (sessionScope.user eq 'active')}">
							<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
						</c:if>
						<c:if test="${sessionScope.user eq 'active'}">
							<c:import url="/WEB-INF/pages/tiles/body.jsp" />
						</c:if>


					</div>
				</div>

			</div>
			<div class="footer">
				<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
			</div>



			<div class="popup">
				<form action="controller" method="post" id="js-modal" class="modal">
					<input type="hidden" name="command" value="do_signin" />
					<c:if test="${not (requestScope.AuthenticationError eq null)}">
							<font color="red">
								<c:out value="${requestScope.AuthenticationError}" />
							</font>
					</c:if>
					<div id="js-close-button" class="modal__close">
						<div id="" title="" class="icon ">
							<svg viewBox="0 0 32 32">
								<use xlink:href="#close-icon"></use>
							</svg>
						</div>
					</div>
					<div class="modal__header">Log In</div>
					<div class="modal__description">Enter your login and password</div>
					<div class="modal__section">
						<div class="input-with-label">
							<input id="name" name="login" type="text" value="" class="input-with-label__input">
							<label for="name" class="input-with-label__label">login
								<div class="input-with-label__label__corner"></div>
							</label>
						</div>
					</div>
					<div class="modal__section">
						<div class="input-with-label">
							<input id="password" name="password" type="password"  value="" class="input-with-label__input">
							<label for="password" class="input-with-label__label">password
								<div class="input-with-label__label__corner"></div>
							</label>
						</div>
					</div>
					<div class="modal__section grid grid--sliced grid--gutter-x2">
						<div class="grid-bit grid-bit--14-20">
							<input type="submit" value="log in" class="button"></input>
						</div>
						<div class="grid-bit grid-bit--6-20">
							<button class="button--grey">cancel</button>
						</div>
						<a href="">Registration</a> <input type="submit" value="Sign In" />
					</div>
				</form>
			</div>
		</body>

		</html>