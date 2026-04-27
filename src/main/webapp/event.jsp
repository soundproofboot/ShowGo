<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<c:import url="nav.jsp" />
<h2>${event.title}</h2>
<c:set var="isInterested" value="false" />
<c:choose>
    <c:when test="${not empty user}">
        <c:forEach items="${user.eventInterests}" var="eventInterest">
            <c:if test="${eventInterest.event.id == event.id}">
                <c:set var="isInterested" value="true" />
            </c:if>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>otherwise</p>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${isInterested}">
        <p>You're interested.</p>
        <form action="${context}/removeEventInterest" method="POST">
            <input type="hidden" name="event_id" id="event_id" value="${event.id}">
            <input type="submit" value="Remove">
        </form>
    </c:when>
    <c:otherwise>
        <form action="${context}/addEventInterest" method="POST">
            <input type="hidden" name="event_id" id="event_id" value="${event.id}">
            <input type="submit" value="I'm interested" />
        </form>
    </c:otherwise>
</c:choose>
    <a href="${context}/venues/${event.venue.id}">${event.venue.name}</a>
<p>Lineup</p>
<ul>
    <c:forEach items="${event.performers}" var="eventPerformer">
        <li>${eventPerformer.performer.name}</li>
    </c:forEach>
</ul>
</body>
</html>