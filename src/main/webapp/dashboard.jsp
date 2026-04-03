<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<html>
<body>
    <c:import url="nav.jsp" />
    <h1>Dashboard</h1>
    <c:choose>
<%--        redirect to home page if not logged in--%>
        <c:when test="${empty user}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty user.username}">
                    <p>need to provide info</p>
                </c:when>
                <c:otherwise>
                    <h2>${user.username}</h2>
                    <p>${user.city}, ${user.state}</p>
                    <p>...more</p>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
