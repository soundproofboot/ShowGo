<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<html>
<body>
    <c:import url="nav.jsp" />
    <h1>Home</h1>
    <c:choose>
        <c:when test="${empty user}">
            <p>
                Welcome to ShowGo! <a href="login">log in</a> or <a href="signup">sign up</a> to find shows in your area.
            </p>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty user.city}">
                    <a href="dashboard.jsp">Tell us about yourself</a>
                </c:when>
                <c:otherwise>
                    <h2>Find out what's happening in ${user.city}</h2>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
