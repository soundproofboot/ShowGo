<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<nav>
    <div>
        <a href="${context}/index.jsp">Home</a> ||
        <a href="${context}/users">Users</a> ||
        <a href="${context}/performers">Performers</a> ||
        <a href="${context}/venues">Venues</a> ||
        <a href="${context}/events">Events</a> ||
    </div>
    <div>
        <c:choose>
            <c:when test="${empty user}">
                <a href="${context}/login">Log in</a>
                <a href="${context}/signup">Sign up</a>
            </c:when>
            <c:otherwise>
                <a href="${context}/dashboard">Dashboard</a>
                <a href="${context}/logout">Log Out</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>