<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<html>
    <body>
        <c:import url="nav.jsp" />
        <h1>All performers and the users that follow them</h1>
        <c:forEach items = "${allPerformers}" var="performer">
            <h2>name: ${performer.name}</h2>
            <p>followers</p>
            <ul>
                <c:forEach items = "${performer.followers}" var="follow">
                    <li>${follow.user.username}</li>
                </c:forEach>
            </ul>
        </c:forEach>
    </body>
</html>