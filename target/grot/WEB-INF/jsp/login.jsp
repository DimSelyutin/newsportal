<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="popup popup-login" style="display: none;">
<form action="controller" method="post" id="js-modal" class="modal">
    <input type="hidden" name="command" value="do_signin" />
    <c:if test="${not (requestScope.AuthenticationError eq null)}">
        <font color="red">
            <c:out value="${requestScope.AuthenticationError}" />
        </font>
    </c:if>
    <div class="modal__close js-close-button">
        <div id="" title="" class="icon ">
            <svg viewBox="0 0 32 32">
                <use xlink:href="#close-icon"></use>
            </svg>
        </div>
    </div>
    <div class="modal__header">Log In</div>
    <div class="modal__description">Enter your login and password</div>
    <div class="modal__section">
        <div class="input-with-label">
            <input id="name" name="login" type="text" value="" class="input-with-label__input">
            <label for="name" class="input-with-label__label">login
                <div class="input-with-label__label__corner"></div>
            </label>
        </div>
    </div>
    <div class="modal__section">
        <div class="input-with-label">
            <input id="password" name="password" type="password" value=""
                class="input-with-label__input">
            <label for="password" class="input-with-label__label">password
                <div class="input-with-label__label__corner"></div>
            </label>
        </div>
    </div>
    
    <div class="modal__section grid grid--sliced grid--gutter-x2">
        <div class="grid-bit grid-bit--14-20">
            <input type="submit" value="log in" class="button"></input>
        </div>
        <div class="grid-bit grid-bit--6-20">
            <button class="button--grey">cancel</button>
        </div>
        <a href="">Registration</a>
    </div>
</form>
</div>


