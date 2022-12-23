<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<meta>
</meta>
<body>
    Error!
    <c:forEach var="exception" items="${requestScope.e}">
        <c:out value="${e}" />
	</c:forEach>

</body>
</html>
