<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<html>

			<head>
				<title>Html page</title>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<script src="./script/jquery.min.js"></script>
				<script type="text/javascript" src="./script/script.js"></script>

				<link rel="stylesheet" type="text/css" href="./css/style.css">
				<link href="/examples/libs/toast/toast.min.css" rel="stylesheet">
				<script src="/examples/libs/toast/toast.min.js"></script>

				<fmt:setLocale value="${sessionScope.local}" />
				<fmt:setBundle basename="localization.local" var="loc" />
				<fmt:message bundle="${loc}" key="local.title" var="title" />
				<fmt:message bundle="${loc}" key="local.main" var="main" />

				<fmt:message bundle="${loc}" key="local.en" var="en_button" />
				<fmt:message bundle="${loc}" key="local.ru" var="ru_button" />
			</head>

			<body>

				<c:import url="/WEB-INF/pages/tiles/icons/errorIcon.jsp" />


				<div class="header">
					<c:import url="/WEB-INF/pages/tiles/header.jsp" />
				</div>


				<div class="first-page">
					<h1>
						<c:out value="${title}" />

					</h1>
				</div>
				<div class="content">
					<div class="base-layout-wrapper">

						<div class="menu">
							<c:if test="${not (sessionScope.user eq 'active')}">
								Log in or register for read news.
							</c:if>
							<c:if test="${sessionScope.user eq 'active'}">
								<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
							</c:if>

						</div>
						<div class="content_news">
							<c:if test="${not (sessionScope.user eq 'active')}">
								<c:import url="/WEB-INF/jsp/login.jsp" />
								<c:import url="/WEB-INF/jsp/register.jsp" />
							</c:if>

							<c:import url="/WEB-INF/pages/tiles/body.jsp" />

						</div>
					</div>

				</div>


				<div class="footer">
					<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
				</div>
			</body>

			</html>