<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<div class="container">

<c:import url="nav.jsp" />
<c:choose>
    <c:when test="${empty performer}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center">${performer.name}</h1>
        <c:set var="isFollowed" value="false" />
        <c:choose>
            <c:when test="${not empty user}">
                <c:forEach items="${user.performerFollows}" var="performerFollow">
                    <c:if test="${performerFollow.performer.id == performer.id}">
                        <c:set var="isFollowed" value="true" />
                    </c:if>
                </c:forEach>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${isFollowed}">
                <p>Followed</p>
                <form action="${context}/removePerformerFollow" method="POST" class="text-center">
                    <input type="hidden" name="performer_id" id="performer_id" value="${performer.id}">
                    <input type="submit" value="Remove">
                </form>
            </c:when>
            <c:otherwise>
                <form action="${context}/addPerformerFollow" method="POST" class="text-center">
                    <input type="hidden" name="performer_id" id="performer_id" value="${performer.id}">
                    <input type="submit" value="Follow">
                </form>
            </c:otherwise>
        </c:choose>
        <p><strong>Manager:</strong> <a href="${context}/users/${performer.user.username}">${performer.user.username}</a></p>
        <p><strong>Genre: </strong>${performer.genre}</p>
        <p><strong>Description: </strong>${performer.description}</p>
        <h2 class="text-center">Upcoming Shows</h2>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-4 justify-content-center">
            <c:choose>
                <c:when test="${performer.events.size() == 0}">
                    <p>Nothing on the calendar.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${performer.events}" var="event">
                        <c:set var="event" value="${event.event}" scope="request" />
                        <jsp:include page="components/eventSimple.jsp" />
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>