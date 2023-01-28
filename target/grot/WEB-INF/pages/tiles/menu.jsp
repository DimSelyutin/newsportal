<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<head>
			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="localization.local" var="loc" />
			<fmt:message bundle="${loc}" key="local.category" var="category" />
		</head>

		<div class="menu-wrapper">
			<div class="menu-title-wrapper">
				<div class="menu-title">
					<c:out value="${category}"/>:
				</div>
			</div>

			<div class="list-menu-invisible-wrapper">
				<div class="list-menu-wrapper">
					<ul class="list-menu">
						<c:forEach var="category" items="${sessionScope.listCategory}">
							<li class="list-menu-post">
								<a href="controller?command=go_to_news&category=${category.nameCategory}">
									<c:out value="${category.nameCategory}" /> (<c:out value="${category.countPosts}" />)
								</a>
							</li>
						</c:forEach>
						<li>
							<a href="controller?command=go_to_news&category=sortbydate">Sort by date</a>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!--  grey free space at the bottom of menu -->
		</div>