<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<html>
<body>
    <c:import url="nav.jsp" />
    <h1>All users and performers they follow</h1>
    <c:forEach items = "${allUsers}" var="user">
        <h2>username: ${user.username}</h2>
        <p>Performers followed</p>
        <ul>
            <c:forEach items = "${user.performerFollows}" var="performerFollow">
                <li>${performerFollow.performer.name}</li>
            </c:forEach>
        </ul>
        <p>Venues followed</p>
        <ul>
            <c:forEach items = "${user.venueFollows}" var="venueFollow">
                <li>${venueFollow.venue.name}</li>
            </c:forEach>
        </ul>
    </c:forEach>
</body>
</html>
