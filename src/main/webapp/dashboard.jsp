<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<body>
<div class="container">

    <c:import url="nav.jsp" />
    <h1 class="text-center">Dashboard</h1>
    <c:choose>
<%--        redirect to home page if not logged in--%>
        <c:when test="${empty user}">
            <c:redirect url="${context}/" />
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty user.username}">
                    <form action="${context}/newUserData" method="POST" class="container mt-4">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" name="username" id="username" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="city" class="form-label">City</label>
                            <input type="text" name="city" id="city" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="state" class="form-label">State</label>
                            <input type="text" name="state" id="state" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h2 class="text-center">${user.username}</h2>
                    <p class="text-center">${user.city}, ${user.state}</p>
                    <c:if test="${user.venues.size() == 0}">
                        <a class="btn btn-primary" href="${context}/newVenue">Register a Venue</a>
                    </c:if>
                    <c:if test="${user.venues.size() > 0}">
                        <h3 class="text-center">Venues you manage <a class="btn btn-primary" href="${context}/newVenue">New</a></h3>
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                            <c:forEach items="${user.venues}" var="venue">
                                <c:set var="venue" value="${venue}" scope="request"/>
                                <jsp:include page="components/venueSimple.jsp"/>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${user.performers.size() == 0}">
                        <a href="${context}/newPerformer" class="btn btn-primary">Register a Performer</a>
                    </c:if>
                    <c:if test="${user.performers.size() > 0}">
                        <h3 class="text-center">Performers you manage <a class="btn btn-primary" href=${context}/newPerformer">New</a></h3>
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                            <c:forEach items="${user.performers}" var="performer">
                                <c:set var="performer" value="${performer}" scope="request"/>
                                <jsp:include page="components/performerSimple.jsp"/>
                            </c:forEach>
                        </div>
                    </c:if>
                    <h3 class="text-center">Your upcoming events</h3>
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                        <c:forEach items="${user.eventInterests}" var="eventInterest">
                            <c:set var="event" value="${eventInterest.event}" scope="request"/>
                            <jsp:include page="components/eventSimple.jsp"/>
                        </c:forEach>
                    </div>

                    <h3 class="text-center">Venues you follow</h3>
                    <c:choose>
                        <c:when test="${user.venueFollows.size() == 0}">
                            <p class="text-center">You don't follow any venues. <a href="${context}/venues">Discover some new venues.</a></p>
                        </c:when>
                        <c:otherwise>
                            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                                <c:forEach items="${user.venueFollows}" var="venueFollow">
                                    <c:set var="venue" value="${venueFollow.venue}" scope="request"/>
                                    <jsp:include page="components/venueSimple.jsp"/>
                                </c:forEach>
                            </div>
<%--                            <c:forEach items="${user.venueFollows}" var="venueFollow">--%>
<%--                                <h4><a href="${context}/venues/${venueFollow.venue.id}">${venueFollow.venue.name}</a></h4>--%>
<%--                            </c:forEach>--%>
                        </c:otherwise>
                    </c:choose>
                    <h3 class="text-center">Performers you follow</h3>
                    <c:choose>
                        <c:when test="${user.performerFollows.size() == 0}">
                            <p class="text-center">You don't follow any performers.</p>
                        </c:when>
                        <c:otherwise>
                            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                                <c:forEach items="${user.performerFollows}" var="performerFollow">
                                    <c:set var="performer" value="${performerFollow.performer}" scope="request"/>
                                    <jsp:include page="components/performerSimple.jsp"/>
                                </c:forEach>
                            </div>
<%--                            <c:forEach items="${user.performerFollows}" var="performerFollow">--%>
<%--                                <h4><a href="${context}/performers/${performerFollow.performer.id}">${performerFollow.performer.name}</a></h4>--%>
<%--                            </c:forEach>--%>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
