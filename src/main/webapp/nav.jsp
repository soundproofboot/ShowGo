<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<nav>
    <a href="index.jsp">Home</a> ||
    <a href="getAllUsers">Get all users</a> ||
    <a href="getAllPerformers">Get all performers</a> ||
    <a href="getAllVenues">Get all venues</a> ||
    <a href="getAllEvents">Get all events</a> ||
    <c:choose>
        <c:when test="${empty cognitoId}">
            <a href="login">Log in</a> ||
        </c:when>
        <c:otherwise>
            <span>user: ${cognitoId}</span> ||
            <a href="logout">Log Out</a>
        </c:otherwise>
    </c:choose>
</nav>