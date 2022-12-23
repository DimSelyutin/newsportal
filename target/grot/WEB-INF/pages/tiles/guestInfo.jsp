<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="body-title">
	<a href="">News >> </a> Latest News
</div>

<form action="command.do?method=delete" method="post">
	<c:forEach var="news" items="${requestScope.newsList}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.postDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.text}" />
				</div>
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        No news.
	</c:if>
	</div>

</form>
