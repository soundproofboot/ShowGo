<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<html>
<body>
    <c:import url="nav.jsp" />
    <h1>All events the venue hosting them</h1>
    <c:forEach items="${allEvents}" var="event">
        <h2>${event.title} hosted by ${event.venue.name}</h2>
    </c:forEach>
</body>
</html>