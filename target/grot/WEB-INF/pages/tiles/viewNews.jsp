<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="edit_news">
        <div class="h_right">
            <div class="user-name edit-form">
                <c:out value="${sessionScope.login}" />
            </div>
            <div class="user-role edit-form">
                <c:out value="${sessionScope.role}" />

            </div>

        </div>
        <input type="hidden" name="command" value="go_view_news" />
        <div class="form_edit_news">
            <div class="news-title-edit">
                <c:out value=" ${post.title}" />
            </div>
            <div class="post-img">
                <img src="
            <c:out value=" ${post.imageDir}" />
                " alt="Photo">

            </div>
            <div class="news-content">
                <c:out value="${post.text}" />

            </div>
            <div class="news-date">
                <c:out value="${post.postDate}" />
            </div>
            <div>
                like
            </div>

        </div>
        <c:if test="${sessionScope.user eq 'active'}">
            <c:import url="/WEB-INF/pages/tiles/viewComment.jsp" />
            <c:import url="/WEB-INF/pages/tiles/postEditor.jsp" />
        </c:if>
        <c:if test="${not (sessionScope.user eq 'active')}">
            <div>
                To view and add comments, log in or register!
            </div>
        </c:if>
    </div>