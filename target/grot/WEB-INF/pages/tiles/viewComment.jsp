<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="comments-wrapper">
        <div class="comments-wrapper-header">
            <h2 class="comments-wrapper_title">
                Comments
                <span class="comments-wrapper_comments-count">
                    Count
                </span>
            </h2>
        </div>

        <c:forEach var="comments" items="${requestScope.comments}">
            <div class="comments-wrapper_inner">
                <!---->
                <div data-comments-list="" class="tm-comments_tree">
                    <div class="comment-header">
                        <div class="c-user-info">

                            <div class="user-icon">
                            </div>

                            <div class="c-user-name">
                                <div class="c-user-title">
                                    <c:out value="${comments.userName}" />
                                </div>

                                <div class="user-role">
                                    Unknown
                                </div>
                            </div>
                            <div class="comment-time">
                                <c:out value="${comments.commentDate}" />
                            </div>
                        </div>
                        
                        <div class="link" style="float: right;">
                            <div class="menu-icons">
                                <div class="setting-svg">
                                    <div class="hidden_menu" style="display: none;">
                                        <c:if test="${comments.userId == sessionScope.idUser}">
                                            <span><a href="controller?command=do_delete_comment&commentId=${comments.commentId}">Delete comment</a></span>
                                            <span><a href="controller?command=do_change_comment&commentId=${comments.commentId}">Change comment</a></span>
                                        </c:if>
                
                                    </div>
                                    <svg aria-label="dotted" class="menu-svg" color="#262626" fill="#262626"
                                        height="24" role="img" viewBox="0 0 24 24" width="24">
                                        <circle cx="12" cy="12" r="1.5"></circle>
                                        <circle cx="6" cy="12" r="1.5"></circle>
                                        <circle cx="18" cy="12" r="1.5"></circle>
                                    </svg>
                                </div>
                            </div>
                            <svg width="2rem" height="2rem" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M20 9a2.995 2.995 0 0 0-2.816 2H6.816c-.027-.074-.05-.15-.082-.221l7.09-4.726a3.006 3.006 0 1 0-.558-.83l-7.09 4.725a3 3 0 1 0 0 4.105l7.09 4.726A2.973 2.973 0 0 0 13 20a3.02 3.02 0 1 0 .823-2.052l-7.089-4.726A2.973 2.973 0 0 0 7 12h10a3 3 0 1 0 3-3zm-6-5a2 2 0 1 1 2 2 2.002 2.002 0 0 1-2-2zm2 14a2 2 0 1 1-2 2 2.002 2.002 0 0 1 2-2zm4-4a2 2 0 1 1 2-2 2.002 2.002 0 0 1-2 2z" />
                                <path fill="none" d="M0 0h24v24H0z" />
                            </svg>
                        </div>
                    </div>
                </div>

                <div class="comment-body">
                    <p>
                        <c:out value="${comments.commentText}"/>
                    </p>
                </div>

                <div class="comment-footer">
                    <div class="menu-icons">
                        <div>
                            <a href="">answer</a>
                        </div>
                        <c:import url="/WEB-INF/pages/tiles/icons/like.jsp" />
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="v-portal" style="display: none;"></div>
    </div>
    </div>