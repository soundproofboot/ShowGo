<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<c:import url="nav.jsp" />
<h2>${event.title}</h2>
<p><a href="${context}/venues/${event.venue.id}">${event.venue.name}</a></p>
<p>Lineup</p>
<ul>
    <c:forEach items="${event.performers}" var="eventPerformer">
        <li>${eventPerformer.performer.name}</li>
    </c:forEach>
</ul>
</body>
</html>