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
                    <p>...list events user followed order by most recent</p>
                    <h3>Venues you follow</h3>
                    <c:forEach items="${user.venues}" var="venue">
                        <h4>${venue.name}</h4>
                    </c:forEach>
                    <p>...list venues user followed</p>
                    <h3>Performers you follow</h3>
                    <p>...list performers user followed</p>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>
