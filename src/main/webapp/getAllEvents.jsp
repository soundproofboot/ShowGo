<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
    <c:import url="nav.jsp" />
    <c:choose>
        <c:when test="${empty citySearched && empty stateSearched}">
            <h1>Search for events</h1>
            <form action="${context}/events" method="GET">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <input type="text" name="state" id="state" value="${stateSearched}" required>
                <input type="submit">
            </form>
        </c:when>
        <c:otherwise>
            <h1>Events in ${citySearched}, ${stateSearched}</h1>
            <form action="${context}/events" method="GET">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <input type="text" name="state" id="state" value="${stateSearched}" required>
                <input type="submit">
            </form>
            <c:choose>
                <c:when test="${allEvents.size() > 0}">
                    <c:forEach items="${allEvents}" var="event">
                        <h2><a href="${context}/events/${event.id}">${event.title}</a></h2>
                        <a href="${context}/venues/${event.venue.id}">@ ${event.venue.name}</a>
                        <p>Lineup</p>
                        <ul>
                            <c:forEach items="${event.performers}" var="eventPerformer">
                                <li><a href="${context}/performers/${eventPerformer.performer.id}"></a>${eventPerformer.performer.name}</li>
                            </c:forEach>
                        </ul>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No results for ${citySearched}, ${stateSearched}</p>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>