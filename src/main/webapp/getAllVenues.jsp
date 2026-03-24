<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<html>
<body>
    <c:import url="nav.jsp" />
    <h1>All venues and the users that follow them</h1>
    <c:forEach items="${allVenues}" var="venue">
        <h2>name: ${venue.name}</h2>
        <p>followers</p>
        <ul>
            <c:forEach items="${venue.followers}" var="follow">
                <li>${follow.user.username}</li>
            </c:forEach>
        </ul>
        <p>events</p>
        <ul>
            <c:forEach items="${venue.events}" var="event">
                <li>${event.title}</li>
            </c:forEach>
        </ul>
    </c:forEach>
</body>
</html>
