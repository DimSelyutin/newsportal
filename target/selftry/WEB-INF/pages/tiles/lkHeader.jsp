<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="user-info">
    <div class="user-icon">
        
    </div>
    <div class="h_right">
    <div class="user-name">
        <c:out value = "${sessionScope.login}"/>
    </div>
    <div class="user-role">
        <c:out value = "${sessionScope.role}"/>
    </div>
    </div>
</div>