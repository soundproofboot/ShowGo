<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
    <c:import url="nav.jsp" />
    <c:choose>
        <c:when test="${empty citySearched && empty stateSearched}">
            <h1>Search for music venues</h1>
            <form action="${context}/venues" method="GET">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <input type="text" name="state" id="state" value="${stateSearched}" required>
                <input type="submit">
            </form>
        </c:when>
        <c:otherwise>
            <h1>Venues in ${citySearched}, ${stateSearched}</h1>
            <form action="${context}/venues" method="GET">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <input type="text" name="state" id="state" value="${stateSearched}" required>
                <input type="submit" value="Search">
            </form>
            <c:choose>
                <c:when test="${allVenues.size() > 0}">
                    <c:forEach items="${allVenues}" var="venue">
                        <h2><a href="${context}/venues/${venue.id}">${venue.name}</a></h2>
                        <p>manager: ${venue.user.username}</p>
                        <p>followers</p>
                        <ul>
                            <c:forEach items="${venue.followers}" var="follow">
                                <li>${follow.user.username}</li>
                            </c:forEach>
                        </ul>
                        <p>events</p>
                        <ul>
                            <c:forEach items="${venue.events}" var="event">
                                <li>
                                    <h3>${event.title}</h3>
                                    <p>lineup</p>
                                    <ul>
                                        <c:forEach items="${event.performers}" var="eventPerformer">
                                            <li>${eventPerformer.performer.name}</li>
                                        </c:forEach>
                                    </ul>
                                </li>
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
