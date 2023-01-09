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
        <form action="controller?command=do_add_news" method="post">
            <input type="hidden" name="command" value="do_add_news" />
            <div class="form_edit_news">
                <div class="select">
                    <select id="select_category" name="category">
                        <c:forEach var="category" items="${sessionScope.listCategory}">
                        <option value="${category.idCategory}">
                            <c:out value="${category.nameCategory}" />
                        </option>
                    </c:forEach>
                    </select>
                </div>
                <div class="news-title-edit">
                    <input placeholder="Title" name="title">
                </div>
                <div class="news-content">
                    <textarea name="postText" placeholder="Enter your text there"></textarea>
                </div>

                <div class="post_img">
                    <img src="" alt="Photo">

                </div>
            </div>
            <input class="button" type="submit" value="Add post">
        </form>
    </div>