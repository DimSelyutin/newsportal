<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

guestInfo
<div class="body-title">
	<a href="">News</a> News List

	<form action="command.do?method=delete" method="post" class="news_list">
		<c:forEach var="news" items="${requestScope.news}">
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
				<div class="menu-icons">
					<div class="like-svg">
						<svg aria-label="Не нравится" class="menu-svg" color="#ed4956" fill="#ed4956" height="24" role="img" viewBox="0 0 48 48" width="2vw">
							<path d="M34.6 3.1c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5s1.1-.2 1.6-.5c1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z"></path>
						</svg>
					</div>
					<div class="setting-svg">
						<div class="hidden_menu" style="display: none;">
							<span><a href="">Edit news</a></span>
							<span><a href="">View news</a></span>
							<span><a href="">Hidden news</a></span>
						</div>
						<svg aria-label="Дополнительно" class="menu-svg" color="#262626" fill="#262626" height="24" role="img" viewBox="0 0 24 24" width="24">
							<circle cx="12" cy="12" r="1.5"></circle>
							<circle cx="6" cy="12" r="1.5"></circle>
							<circle cx="18" cy="12" r="1.5"></circle>
						</svg>
						
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
</div>
