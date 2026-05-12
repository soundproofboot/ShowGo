<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<body>
<div class="container">
    <c:import url="nav.jsp" />
    <c:choose>
        <c:when test="${empty user}">
            <p>
                Welcome to ShowGo! <a href="login">log in</a> or <a href="signup">sign up</a> to find shows in your area.
            </p>
            <h1 class="text-center">Latest events</h1>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 gap-3 justify-content-center">
                <c:forEach items="${events}" var="event">
                    <c:set var="event" value="${event}" scope="request"/>
                    <jsp:include page="components/eventSimple.jsp"/>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty user.city && empty user.state}">
                    <a href="dashboard.jsp">Tell us about yourself</a>
                </c:when>
                <c:otherwise>
                    <h2 class="text-center">Upcoming events in ${user.city}, ${user.state}</h2>
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center">
                        <c:forEach items="${events}" var="event">
                            <c:set var="event" value="${event}" scope="request"/>
                            <jsp:include page="components/eventSimple.jsp"/>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
