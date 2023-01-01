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
        <form action="controller?command=do_edit_news&idNews=${post.id}" method="post">
            <input type="hidden" name="command" value="do_edit_news" />
            <div class="form_edit_news">
                <div class="news-title-edit">
                    <input value="
                <c:out value=" ${post.title}" />
                    " name="title">
                </div>
                <div class="news-content">
                    <textarea name="postText">
                    <c:out value="${post.text}"/>
                </textarea>
                </div>
                <div class="news-date">
                    <c:out value="${post.postDate}" />
                </div>
                <div>
                    like
                </div>
                <div class="post_img">
                    <img src="
                <c:out value=" ${post.imageDir}" />
                    " alt="Photo">

                </div>
            </div>
            <input class="button" type="submit" value="Send changes">
        </form>
    </div>