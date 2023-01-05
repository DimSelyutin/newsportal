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
        <form action="controller?command=do_edit_news&idNews=${post.id}" method="post" enctype="multipart/form-data" >
            <input type="hidden" name="command" value="do_edit_news" />
            <div class="form_edit_news">
                <div class="news-title-edit">
                    <input value="
                        <c:out value=" ${post.title}" />
                    " name="title">
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
                </div>
                <div class="news-date">
                    <c:out value="${post.postDate}" />
                </div>
                <div class="post_img">
                    <input type="file" name="file" id="img-download" size ="50" multiple accept="image/*,image/jpeg,image/png" />
                    <img id="img-display" src="#" alt="" />
                    
                    

                </div>
            </div>
            <input class="button" type="submit" value="Send changes">
        </form>
        
       
    </div>