<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

            <head>
                <fmt:setLocale value="${sessionScope.local}" />
                <fmt:setBundle basename="localization.local" var="loc" />
                <fmt:message bundle="${loc}" key="local.editnews" var="editnews" />
                <fmt:message bundle="${loc}" key="local.forviewnews" var="forviewnews" />

            </head>
            <div class="edit_news">
                <div class="h_right">
                    <div class="h_right_info">
                        <div class="user-name edit-form">
                            <c:out value="${sessionScope.login}" />
                        </div>
                        <div class="user-role edit-form">
                            <c:out value="${sessionScope.role}" />
                        </div>
                    </div>
                    <div class="menu-icons">
                        <div class="setting-svg">
                            <div class="hidden_menu" style="display: none;">
                                <c:if test="${requestScope.post.userId == sessionScope.idUser}">
                                    <span><a href="controller?command=go_to_edit_news&idNews=${post.id}">
                                            <c:out value="${editnews}" />
                                        </a></span>
                                </c:if>
                                <span><a href="">Hidden news</a></span>
                            </div>
                            <svg aria-label="Дополнительно" class="menu-svg" color="#262626" fill="#262626" height="24"
                                role="img" viewBox="0 0 24 24" width="24">
                                <circle cx="12" cy="12" r="1.5"></circle>
                                <circle cx="6" cy="12" r="1.5"></circle>
                                <circle cx="18" cy="12" r="1.5"></circle>
                            </svg>
                        </div>

                    </div>
                </div>
                <input type="hidden" name="command" value="go_view_news" />
                <div class="form_edit_news">
                    <div class="news-title-edit">
                        <pre><c:out value=" ${post.title}" /></pre>
                    </div>
                    <div class="post-img">
                        <img src="
                        <c:out value=" ${post.imageDir}" />
                        " alt="Photo">

                    </div>
                    <div class="news-content">
                        <pre><c:out value="${post.text}" /></pre>

                    </div>
                    <div class="news-footer">
                        <div class="news-date">
                            <c:out value="${post.postDate}" />
                        </div>
                        <div class="news-like">
                            <div class="like-svg-container">
                                <input name="idNews" value="${post.id}" id="idNews" type="hidden" />

                                <svg aria-label="like" id="like-btn" class="like-svg 
										<c:if test="${fn:contains(requestScope.likedNews, post.id)}">
										like-svg-active
										</c:if>
										" color="#ed4956"
											fill="#fafafa" height="24" role="img" viewBox="0 0 48 48" width="2vw">
											<path
												d="M34.6 3.1c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5s1.1-.2 1.6-.5c1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z">
											</path>
										</svg>

                                <div class="counter">
                                    <c:out value="${post.likeCount}" />
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <c:if test="${sessionScope.user eq 'active'}">
                    <c:import url="/WEB-INF/pages/tiles/viewComment.jsp" />
                    <c:import url="/WEB-INF/pages/tiles/postEditor.jsp" />
                </c:if>
                <c:if test="${not (sessionScope.user eq 'active')}">
                    <div>
                        <c:out value="${forviewnews}" />
                    </div>
                </c:if>
            </div>