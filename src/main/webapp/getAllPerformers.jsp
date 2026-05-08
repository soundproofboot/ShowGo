<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
    <body>
    <div class="container">

        <c:import url="nav.jsp" />
        <h1>Search performers</h1>
        <c:choose>
            <c:when test="${empty searchTerm}">
                <form action="${context}/performers" method="GET">
                    <label for="performerName">Name</label>
                    <input type="text" name="performerName" id="performerName" value="${searchTerm}" required>
                    <input type="submit">
                </form>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty performers}">
                        <p>No results for ${searchTerm}</p>
                        <form action="${context}/performers" method="GET">
                            <label for="performerName">Name</label>
                            <input type="text" name="performerName" id="performerName" value="${searchTerm}" required>
                            <input type="submit">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>Showing results for ${searchTerm}</p>
                        <form action="${context}/performers" method="GET">
                            <label for="performerName">Name</label>
                            <input type="text" name="performerName" id="performerName" value="${searchTerm}" required>
                            <input type="submit">
                        </form>
                        <c:forEach items="${performers}" var="performer">
                            <h2><a href="${context}/performers/${performer.id}">${performer.name}</a></h2>
                            <p>manager: ${performer.user.username}</p>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>

    </body>
</html>