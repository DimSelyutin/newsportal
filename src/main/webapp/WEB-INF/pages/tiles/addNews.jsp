<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <head>
            <fmt:setLocale value="${sessionScope.local}" />
            <fmt:setBundle basename="localization.local" var="loc" />
            <fmt:message bundle="${loc}" key="local.addnews" var="addnews" />

        </head>s
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
                        <input type="file" name="file" id="img-download" size="50" multiple
                            accept="image/*,image/jpeg,image/png" />
                        <img id="img-display" src="#" alt="" />



                    </div>
                </div>
                <input class="button" type="submit" value="${addpost}">
            </form>
        </div>