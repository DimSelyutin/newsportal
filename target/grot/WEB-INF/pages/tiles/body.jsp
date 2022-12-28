<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${requestScope.presentation eq 'guestInfo'}">
	<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
</c:if>


<c:if test="${requestScope.presentation eq 'userInfo'}">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'userId'}">
	<c:import url="/WEB-INF/pages/tiles/editNews.jsp" />
</c:if>
