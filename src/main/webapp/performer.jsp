<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<c:import url="nav.jsp" />
<c:choose>
    <c:when test="${empty performer}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1>name: ${performer.name}</h1>
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
                <form action="${context}/removePerformerFollow" method="POST">
                    <input type="hidden" name="performer_id" id="performer_id" value="${performer.id}">
                    <input type="submit" value="Remove">
                </form>
            </c:when>
            <c:otherwise>
                <form action="${context}/addPerformerFollow" method="POST">
                    <input type="hidden" name="performer_id" id="performer_id" value="${performer.id}">
                    <input type="submit" value="Follow">
                </form>
            </c:otherwise>
        </c:choose>
        <p>managed by: <a href="${context}/users/${performer.user.username}">${performer.user.username}</a></p>
        <p>logged in user: ${user.username}</p>
    </c:otherwise>
</c:choose>
</body>
</html>