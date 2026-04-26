<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<script>console.log('can I just write javascript like this')</script>
<body>
    <c:import url="nav.jsp" />
    <h1>Dashboard</h1>
    <c:choose>
<%--        redirect to home page if not logged in--%>
        <c:when test="${empty user}">
            <c:redirect url="${context}/index.jsp"></c:redirect>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty user.username}">
                    <p>need to provide info</p>
                    <form action="${context}/newUserData" method="POST">
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username" required>
                        <label for="city">City</label>
                        <input type="text" name="city" id="city" required>
                        <label for="state">State</label>
                        <input type="text" name="state" id="state" required>
                        <button type="submit">Submit</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h2>${user.username}</h2>
                    <p>${user.city}, ${user.state}</p>
                    <h3>Upcoming events</h3>
                    <c:forEach items="${user.eventInterests}" var="eventInterest">
                        <p><a href="${context}/events/${eventInterest.event.id}">${eventInterest.event.title}</a></p>
                        <p>@<a href="${context}/venues/${eventInterest.event.venue.id}">${eventInterest.event.venue.name}</a></p>
                        <p>Lineup</p>
                        <c:forEach items="${eventInterest.event.performers}" var="eventPerformer">
                            <p><a href="${context}/performers/${eventPerformer.performer.id}">${eventPerformer.performer.name}</a></p>
                        </c:forEach>
                    </c:forEach>
                    <h3>Venues you follow</h3>
                    <c:choose>
                        <c:when test="${user.venueFollows.size() == 0}">
                            <p>You don't follow any venues. <a href="${context}/venues">Discover some new venues.</a></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${user.venueFollows}" var="venueFollow">
                                <h4><a href="${context}/venues/${venueFollow.venue.id}">${venueFollow.venue.name}</a></h4>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <h3>Performers you follow</h3>
                    <c:choose>
                        <c:when test="${user.performerFollows.size() == 0}">
                            <p>You don't follow any performers.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${user.performerFollows}" var="performerFollow">
                                <h4><a href="${context}/performers/${performerFollow.performer.id}"></a></h4>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
