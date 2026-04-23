<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<html>
<body>
<c:import url="nav.jsp" />
<c:choose>
    <c:when test="${empty thisUser}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1>name: ${thisUser.username}</h1>
        <p>logged in user: ${user.username}</p>
    </c:otherwise>
</c:choose>
</body>
</html>