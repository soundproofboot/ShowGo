<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<body>
    <c:import url="nav.jsp" />
    <h1>Home</h1>
    <c:choose>
        <c:when test="${empty user}">
            <p>
                Welcome to ShowGo! <a href="login">log in</a> or <a href="signup">sign up</a> to find shows in your area.
            </p>
            <h2>Latest events</h2>
            <c:forEach items="${events}" var="event">
                <c:set var="event" value="${event}" scope="request"/>
                <jsp:include page="components/eventSimple.jsp"/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty user.city && empty user.state}">
                    <a href="dashboard.jsp">Tell us about yourself</a>
                </c:when>
                <c:otherwise>
                    <p>Upcoming events in ${user.city}, ${user.state}</p>
                    <c:forEach items="${events}" var="event">
                        <c:set var="event" value="${event}" scope="request"/>
                        <jsp:include page="components/eventSimple.jsp"/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
