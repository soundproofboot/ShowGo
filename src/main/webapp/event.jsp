<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<div class="container">

<c:import url="nav.jsp" />
<c:choose>
    <c:when test="${empty event}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center">${event.title}</h1>
        <c:set var="isOwner" value="${event.venue.user.id == user.id}"/>
        <c:choose>
            <c:when test="${isOwner}">
<%--                OWNER--%>
                <div class="text-center">
                    <a href="${context}/editEvent/${event.id}" class="btn btn-primary">Edit</a>
                </div>
            </c:when>
            <c:otherwise>
<%--                NOT OWNER--%>
                <c:set var="isInterested" value="false" />
                <c:choose>
                    <c:when test="${not empty user}">
                        <c:forEach items="${user.eventInterests}" var="eventInterest">
                            <c:if test="${eventInterest.event.id == event.id}">
                                <c:set var="isInterested" value="true" />
                            </c:if>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${isInterested}">
                        <form action="${context}/removeEventInterest" method="POST" class="text-center">
                            <p>Interested</p>
                            <input type="hidden" name="event_id" id="event_id" value="${event.id}">
                            <input type="submit" value="Remove">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${context}/addEventInterest" method="POST" class="text-center">
                            <input type="hidden" name="event_id" id="event_id" value="${event.id}">
                            <input type="submit" value="I'm interested" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
                <p>
                    <strong>Where:</strong> <a href="${context}/venues/${event.venue.id}">${event.venue.name}</a> in ${event.venue.city}, ${event.venue.state}
                </p>
                <p>
                    <strong>What:</strong> ${event.description}
                </p>
                <p>
                    <strong>When:</strong> ${event.dateString} @ ${event.timeString}
                </p>
                <p>
                    <strong>Who:</strong>
                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center">
                    <c:forEach items="${event.performers}" var="eventPerformer">
                        <c:set var="performer" value="${eventPerformer.performer}" scope="request" />
                        <jsp:include page="components/performerSimple.jsp" />
                    </c:forEach>
                </div>
                </p>
                <p><strong>How much:</strong><c:choose>
                    <c:when test="${not empty event.ticketPrice and event.ticketPrice != 0}">
                        <fmt:formatNumber value="${event.ticketPrice}" type="currency" currencySymbol="$" maxFractionDigits="2" minFractionDigits="2" />
                    </c:when>
                    <c:otherwise>
                        Free
                    </c:otherwise>
                </c:choose></p>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>