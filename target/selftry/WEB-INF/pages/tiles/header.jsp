<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<h2>News Managment</h2>
	<div class="burger_menu">
		<a href="">Main</a>
		<a href="">News</a>
		<a href="">About</a>

	</div>
	<div class="header_container">

		<div class="language">
			<a href=""> en </a>&nbsp;&nbsp;
			<a href=""> ru </a>
		</div>
		<!-- ///menu with authorization -->
		<c:if test="${not (sessionScope.user eq 'active')}">

			<div class="header_button">
				<button>SIgnIn</button>
			</div>

		</c:if>

		<c:if test="${sessionScope.user eq 'active'}">
			<div class="header_buttons">
			<c:import url="/WEB-INF/pages/tiles/lkHeader.jsp" />
			<form action="controller" method="post">
				<input type="hidden" name="command" value="do_sign_out" />
				<div class="header_button">
					<input class="button" type="submit" value="Sign Out" /><br />
				</div>
			</form>
			</div>

		</c:if>
	</div>