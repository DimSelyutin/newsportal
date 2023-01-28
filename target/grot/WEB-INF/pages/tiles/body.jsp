<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${requestScope.presentation eq 'guestInfo'}">
	<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
</c:if>


<c:if test="${requestScope.presentation eq 'userInfo'}">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'postId'}">
	<c:import url="/WEB-INF/pages/tiles/editNews.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'newsTranslate'}">
	<c:import url="/WEB-INF/pages/tiles/addTranslate.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'viewNews'}">
	<c:import url="/WEB-INF/pages/tiles/viewNews.jsp" />
</c:if>


<c:if test="${requestScope.presentation eq 'addNews'}">
	<c:import url="/WEB-INF/pages/tiles/addNews.jsp" />
</c:if>
