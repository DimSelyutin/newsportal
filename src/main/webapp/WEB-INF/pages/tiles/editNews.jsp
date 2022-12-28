<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="edit_news">
        <div class="user-info">
            <div class="user-info">

            </div>
            <div class="h_right">
                <div class="user-name">
                    <c:out value="${sessionScope.login}" />
                </div>
                <div class="user-role">
                    <c:out value="${sessionScope.login}" />
                </div>
            </div>
        </div>
        <div class="form_edit_news">

            <c:forEach var="post" items="${requestScope.post}">
                <div class="news-title">
                    <c:out value="${post.title}">
                </div>

                <div class="news-content">
                    text<c:out value="${post.text}"></c:out>
                </div>

                <div class="news-date">
                    <c:out value="${post.postDate}">
                </div>

                <div>
                    likeCount
                </div>

                <div>
                    <c:out value="${post.photo}"></c:out>Photo
                </div>
        </div>
    </div>