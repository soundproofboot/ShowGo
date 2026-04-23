<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<c:import url="nav.jsp" />
<c:choose>
    <c:when test="${empty venue}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1>name: ${venue.name}</h1>
        <p>managed by: <a href="${context}/users/${venue.user.username}">${venue.user.username}</a></p>
        <p>logged in user: ${user.username}</p>
    </c:otherwise>
</c:choose>
</body>
</html>