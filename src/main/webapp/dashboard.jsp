<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<script>console.log('can I just write javascript like this')</script>
<body>
    <c:import url="nav.jsp" />
    <h1>Dashboard</h1>
    <c:choose>
<%--        redirect to home page if not logged in--%>
        <c:when test="${empty user}">
            <c:redirect url="${context}/home.jsp"></c:redirect>
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
                    <h3>Your upcoming events</h3>
                    <c:forEach items="${user.eventInterests}" var="eventInterest">
                        <c:set var="event" value="${eventInterest.event}" scope="request"/>
                        <jsp:include page="components/eventSimple.jsp"/>
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
                                <h4><a href="${context}/performers/${performerFollow.performer.id}">${performerFollow.performer.name}</a></h4>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
