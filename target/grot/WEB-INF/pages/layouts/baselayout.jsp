<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<title>Html page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="./script/jquery.min.js"></script>
		<script type="text/javascript" src="./script/script.js"></script>
		<script
			src="https://www.google.com/recaptcha/enterprise.js?render=6Ldn_KMjAAAAAOwa5s3MK4ax_NqNXt5rwa_9LTch"></script>
		<link rel="stylesheet" type="text/css" href="./css/style.css">

	</head>

	<body>
		<c:if test="${not (requestScope.exception eq 'yes')}">
			<div class="pop-up-exception">
				<p>
					<c:out value="${requestScope.exception}"/>
				</p>
			</div>
		</c:if>
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