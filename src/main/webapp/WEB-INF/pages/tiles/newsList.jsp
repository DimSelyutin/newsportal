<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="body-title">
		<a href="">News</a> News List

		<form action="command.do?method=delete" method="post" class="news_list">
			<c:forEach var="news" items="${requestScope.news}">
				<div class="single-news-wrapper">
					<div class="single-news-header-wrapper">
						<div class="menu-icons">
							<div class="setting-svg">
								<div class="hidden_menu" style="display: none;">
									<c:if test="${news.userId == sessionScope.idUser}">
										<span><a href="controller?command=go_to_edit_news&idNews=${news.id}">Edit
												news</a></span>
									</c:if>
									<span><a href="controller?command=go_to_view_news&idNews=${news.id}">View
											news</a></span>
									<span><a href="">Hidden news</a></span>
								</div>
								<svg aria-label="Дополнительно" class="menu-svg" color="#262626" fill="#262626"
									height="24" role="img" viewBox="0 0 24 24" width="24">
									<circle cx="12" cy="12" r="1.5"></circle>
									<circle cx="6" cy="12" r="1.5"></circle>
									<circle cx="18" cy="12" r="1.5"></circle>
								</svg>
							</div>

						</div>

						<div class="news-title">

							<c:out value="${news.title}" />
						</div>
						<div class="news-date">
							<div class="news_category">
								<a>Category: </a>
								<c:out value="${news.category}" />
							</div>
							<c:out value="${news.postDate}" />
						</div>

						<div class="news-content">
							<p>
								<c:out value="${news.text}" />
							</p>
						</div>
					</div>
					<div class="menu-icons">
						<a href="controller?command=go_to_view_news&idNews=${news.id}">
							<div class="comment-svg">
								<svg xmlns="http://www.w3.org/2000/svg" width="1.54vw" height="2vw" fill="none" viewBox="0 0 16 16">
									<path d="M1 4.5C1 3.11929 2.11929 2 3.5 2H12.5C13.8807 2 15 3.11929 15 4.5V9.5C15 10.8807 13.8807 12 12.5 12H8.68787L5.62533 14.6797C4.99168 15.2342 4 14.7842 4 13.9422V12H3.5C2.11929 12 1 10.8807 1 9.5V4.5ZM3.5 3C2.67157 3 2 3.67157 2 4.5V9.5C2 10.3284 2.67157 11 3.5 11H5V13.8981L8.31213 11H12.5C13.3284 11 14 10.3284 14 9.5V4.5C14 3.67157 13.3284 3 12.5 3H3.5Z" />
								</svg>
							</div>
						</a>
						<c:import url="/WEB-INF/pages/tiles/icons/like.jsp"/>
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