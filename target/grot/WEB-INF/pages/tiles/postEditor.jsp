<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <head>
                        <fmt:setLocale value="${sessionScope.local}" />
                        <fmt:setBundle basename="localization.local" var="loc" />
                        <fmt:message bundle="${loc}" key="local.addcomment" var="addcomment" />

                </head>
                <h2 class="space" id="your-answer-header">
                        <c:out value="${addcomment}" />
                </h2>
                <div class="wmd-containe">
                        <form action="controller?command=do_add_comment&idNews=${post.id}" method="post">
                                <input type="hidden" name="command" value="do_add_comment" />

                                <div id="wmd-button-bar" class="wmd-button-bar btr-sm">
                                        <ul id="wmd-button-row" class="wmd-button-row">
                                                <li class="wmd-button" id="wmd-bold-button"
                                                        title="Полужирный шрифт <strong> Ctrl+B"><span
                                                                style="background-position: 0px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-italic-button"
                                                        title="Курсив <em> Ctrl+I"><span
                                                                style="background-position: -20px 0px;"></span></li>
                                                <li class="wmd-spacer wmd-spacer1" id="wmd-spacer1"></li>
                                                <li class="wmd-button" id="wmd-link-button"
                                                        title="Гиперссылка <a> Ctrl+L"><span
                                                                style="background-position: -40px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-quote-button"
                                                        title="Цитата <blockquote> Ctrl+Q"><span
                                                                style="background-position: -60px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-code-button"
                                                        title="Пример кода <pre><code> Ctrl+K"><span
                                                                style="background-position: -80px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-image-button"
                                                        title="Изображение <img> Ctrl+G"><span
                                                                style="background-position: -100px 0px;"></span></li>
                                                <li class="wmd-button wmd-snippet-button" id="wmd-snippet-button"
                                                        title="Фрагмент кода на JavaScript/HTML/CSS Ctrl-M">
                                                        <span></span></li>
                                                <li class="wmd-spacer wmd-spacer2" id="wmd-spacer2"></li>
                                                <li class="wmd-button" id="wmd-olist-button"
                                                        title="Нумерованный список <ol> Ctrl+O"><span
                                                                style="background-position: -120px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-ulist-button"
                                                        title="Маркированный список <ul> Ctrl+U"><span
                                                                style="background-position: -140px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-heading-button"
                                                        title="Заголовок <h1>/<h2> Ctrl+H"><span
                                                                style="background-position: -160px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-hr-button"
                                                        title="Горизонтальная линия <hr> Ctrl+R"><span
                                                                style="background-position: -180px 0px;"></span></li>
                                                <li class="wmd-spacer wmd-spacer3" id="wmd-spacer3"></li>
                                                <li class="wmd-button" id="wmd-undo-button" title="Отменить — Ctrl+Z">
                                                        <span style="background-position: -200px 0px;"></span></li>
                                                <li class="wmd-button" id="wmd-redo-button" title="Повторить — Ctrl+Y">
                                                        <span style="background-position: -220px -20px;"></span></li>
                                                <li class="wmd-spacer wmd-spacer-max"></li>
                                                <li class="wmd-button wmd-help-button" id="wmd-help-button"
                                                        title="Справка о редакторе Markdown" style="right: 0px;"><span
                                                                style="background-position: -240px 0px;"></span></li>
                                        </ul>
                                </div>
                                <div class="ps-relative">
                                        <textarea id="wmd-input" name="post-text"
                                                class="wmd-input s-input bar0 js-post-body-field processed"
                                                data-editor-type="wmd" data-post-type-id="2" cols="92" rows="15"
                                                aria-labelledby="your-answer-header" tabindex="101"
                                                data-min-length=""></textarea>
                                        <div class="grippie bbr-sm" style="margin-right: 0px;"></div>
                                </div>
                                <input class="button" type="submit">
                        </form>
                </div>