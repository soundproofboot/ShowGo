<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col">
    <div class="card h-100">
        <div class="card-body">
            <div class="card-title h4 text-center"><a href="${context}/events/${event.id}">${event.title}</a></div>
            <div class="card-subtitle h5 text-center">@<a href="${context}/venues/${event.venue.id}">${event.venue.name}</a></div>
            <div class="card-text">
                <p class="h6">Lineup</p>
                <ul>
                    <c:forEach items="${event.performers}" var="eventPerformer">
                        <li><a href="${context}/performers/${eventPerformer.performer.id}">${eventPerformer.performer.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="card-footer">
            <p class="mb-0 text-center">
                ${event.timeString}
            </p>
            <p class="mb-0 text-center">
                ${event.dateString}
            </p>
        </div>
    </div>
</div>
