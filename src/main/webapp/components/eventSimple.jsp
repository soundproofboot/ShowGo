<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="outline: 1px solid black;">
    <p><a href="${context}/events/${event.id}">${event.title}</a></p>
    <p><a href="${context}/venues/${event.venue.id}">${event.venue.name}</a></p>
<%--    <p>createdAt${event.createdAt}</p>--%>
<%--    <p>eventStart ${event.eventStart}</p>--%>
    <p>date ${event.dateString}</p>
    <p>time ${event.timeString}</p>
    <p>Lineup</p>
    <ul>
        <c:forEach items="${event.performers}" var="eventPerformer">
            <li><a href="${context}/performers/${eventPerformer.performer.id}">${eventPerformer.performer.name}</a></li>
        </c:forEach>
    </ul>
</div>
