<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <head>
            <fmt:setLocale value="${sessionScope.local}" />
            <fmt:setBundle basename="localization.local" var="loc" />
            <fmt:message bundle="${loc}" key="local.deletenews" var="deletenews" />
            <fmt:message bundle="${loc}" key="local.addtranslate" var="addtranslate" />
            <fmt:message bundle="${loc}" key="local.sendchanges" var="sendchanges" />


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
                                <span><a href="controller?command=do_delete_news&idNews=${post.id}">
                                        <c:out value="${deletenews}" />
                                    </a></span>
                                <span><a href="controller?command=go_to_add_translate&idNews=${post.id}">
                                        <c:out value="${addtranslate}" />
                                    </a></span>
                            </c:if>

                        </div>
                        <svg aria-label="dotted" class="menu-svg" color="#262626" fill="#262626" height="24" role="img"
                            viewBox="0 0 24 24" width="24">
                            <circle cx="12" cy="12" r="1.5"></circle>
                            <circle cx="6" cy="12" r="1.5"></circle>
                            <circle cx="18" cy="12" r="1.5"></circle>
                        </svg>
                    </div>
                </div>
            </div>
            <form action="controller?command=do_edit_news&idNews=${post.id}" method="post"
                enctype="multipart/form-data">

                <input type="hidden" name="command" value="do_edit_news" />
                <div class="form_edit_news">
                    <div class="news-title-edit">
                        <input value="
                        <c:out value=" ${post.title}" />
                        " name="title">
                        <span class="input__label">Title</span>
                    </div>
                    <div class="select">
                        <select id="select_category" name="category">
                            <c:forEach var="category" items="${sessionScope.listCategory}">
                                <option value="${category.idCategory}">
                                    <c:out value="${category.nameCategory}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="news-content">
                        <textarea name="postText">
                            <c:out value="${post.text}"/>
                        </textarea>
                        <span class="input__label">Text</span>

                    </div>
                    <div class="news-date">
                        <c:out value="${post.postDate}" />
                    </div>
                    <div class="post_img">
                        <input type="file" name="file" id="img-download" size="50" multiple
                            accept="image/*,image/jpeg,image/png" />
                        <img id="img-display" src="#" alt="" />



                    </div>
                </div>
                <input class="button" type="submit" value="${sendchanges}">
            </form>


        </div>