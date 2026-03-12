<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<html>
<body>
    <c:import url="nav.jsp" />
    <h1>Home</h1>
    <c:choose>
        <c:when test="${empty cognitoId}">
            <a href="login">Log in</a>
        </c:when>
        <c:otherwise>
            <h3>Welcome ${cognitoId}</h3>
<%--            TODO logout not set up--%>
            <a href="logout">Sign Out</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
