<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
    <body>
        <c:import url="nav.jsp" />
        <h1>All performers and the users that follow them</h1>
        <c:forEach items = "${allPerformers}" var="performer">
            <h2><a href="${context}/performers/${performer.id}">${performer.name}</a></h2>
            <p>manager: ${performer.user.username}</p>
            <p>followers</p>
            <ul>
                <c:forEach items = "${performer.followers}" var="follow">
                    <li>${follow.user.username}</li>
                </c:forEach>
            </ul>
            <p>events</p>
            <ul>
                <c:forEach items = "${performer.events}" var="eventPerformer">
                    <li>${eventPerformer.event.title}</li>
                </c:forEach>
            </ul>
        </c:forEach>
    </body>
</html>