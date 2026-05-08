<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<html>
<body>
<div class="container">

    <c:import url="nav.jsp" />
    <h1>All users and performers they follow</h1>
    <c:forEach items = "${allUsers}" var="user">
        <h2>username: ${user.username}</h2>
        <h3>${user.city}, ${user.state}</h3>
        <c:if test="${user.performerFollows.size() > 0}">
            <p>Performers followed</p>
            <ul>
                <c:forEach items = "${user.performerFollows}" var="performerFollow">
                    <li>${performerFollow.performer.name}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${user.venueFollows.size() > 0}">
            <p>Venues followed</p>
            <ul>
                <c:forEach items = "${user.venueFollows}" var="venueFollow">
                    <li>${venueFollow.venue.name}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${user.performers.size() > 0}">
            <p>Performers they manage</p>
            <ul>
                <c:forEach var="performer" items="${user.performers}">
                    <li>${performer.name}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${user.venues.size() > 0}">
            <p>Venues they manage</p>
            <ul>
                <c:forEach var="venue" items="${user.venues}">
                    <li>${venue.name}</li>
                </c:forEach>
            </ul>
        </c:if>
    </c:forEach>
</div>

</body>
</html>
