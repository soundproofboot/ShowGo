<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="col">
    <div class="card h-100">
        <div class="card-body">
            <div class="card-title h4 text-center"><a href="${context}/events/${event.id}">${event.title}</a></div>
            <div class="card-subtitle h5 text-center">@<a href="${context}/venues/${event.venue.id}">${event.venue.name}</a></div>
            <div class="card-text">
                <p>${event.description}</p>
                <p class="h6">Lineup</p>
                <ul>
                    <c:forEach items="${event.performers}" var="eventPerformer">
                        <li><a href="${context}/performers/${eventPerformer.performer.id}">${eventPerformer.performer.name}(${eventPerformer.performer.genre})</a></li>
                    </c:forEach>
                </ul>
                <p>Price: <c:choose>
                    <c:when test="${not empty event.ticketPrice and event.ticketPrice != 0}">
                        <fmt:formatNumber value="${event.ticketPrice}" type="currency" currencySymbol="$" maxFractionDigits="2" minFractionDigits="2" />
                    </c:when>
                    <c:otherwise>
                        Free
                    </c:otherwise>
                </c:choose></p>
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
