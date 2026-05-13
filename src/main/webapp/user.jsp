<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<html>
<body>
<div class="container">

<c:import url="nav.jsp" />
<c:choose>
    <c:when test="${empty thisUser}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center">${thisUser.username}</h1>
        <p class="text-center">${thisUser.city}, ${thisUser.state}</p>
        <c:if test="${thisUser.venues.size() > 0}">
            <h2 class="text-center">Venues</h2>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                <c:forEach items="${thisUser.venues}" var="venue">
                    <c:set var="venue" value="${venue}" scope="request"/>
                    <jsp:include page="components/venueSimple.jsp"/>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${thisUser.performers.size() > 0}">
            <h2 class="text-center">Performers</h2>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                <c:forEach items="${thisUser.performers}" var="performer">
                    <c:set var="performer" value="${performer}" scope="request"/>
                    <jsp:include page="components/performerSimple.jsp"/>
                </c:forEach>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>