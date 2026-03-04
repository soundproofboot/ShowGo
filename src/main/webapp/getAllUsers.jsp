<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<html>
<body>
    <h1>All users and performers they follow</h1>
    <c:forEach items = "${allUsers}" var="thisUser">
        <h2>username: ${thisUser.username}</h2>
        <p>Performers followed</p>
        <ul>
            <c:forEach items = "${thisUser.performerFollows}" var="thisFollow">
                <li>${thisFollow.performer.name}</li>
            </c:forEach>
        </ul>
    </c:forEach>
</body>
</html>
