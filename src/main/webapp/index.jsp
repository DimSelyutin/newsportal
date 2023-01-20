<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                <title>Loading news parametrs</title>
                <fmt:setLocale value="${sessionScope.local}" />
                <fmt:setBundle basename="localization.local" var="loc" />
                <fmt:message bundle="${loc}" key="local.title" var="title" />

                <fmt:message bundle="${loc}" key="local.en" var="en_button" />
                <fmt:message bundle="${loc}" key="local.ru" var="ru_button" />

            </head>

            <body>
                <c:if test="${sessionScope.local eq 'null'}">
                    <c:redirect url="controller?command=go_to_main_page&local=${en_button}" />
                </c:if>
                <c:if test="${not (sessionScope.local eq 'null')}">
                    <c:redirect url="controller?command=go_to_main_page&local=${sessionScope.local}" />
                </c:if>
            </body>

            </html>