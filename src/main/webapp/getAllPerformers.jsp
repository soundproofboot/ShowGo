<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
    <body>
    <div class="container">
        <c:import url="nav.jsp" />
        <h1 class="text-center">Search performers</h1>
        <c:choose>
            <c:when test="${empty searchTerm}">
                <form action="${context}/performers" method="GET" class="text-center">
                    <label for="performerName">Name</label>
                    <input type="text" name="performerName" id="performerName" value="${searchTerm}" required>
                    <input type="submit">
                </form>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty performers}">
                        <p class="text-center">No results for ${searchTerm}</p>
                        <form action="${context}/performers" method="GET" class="text-center">
                            <label for="performerName">Name</label>
                            <input type="text" name="performerName" id="performerName" value="${searchTerm}" required>
                            <input type="submit">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="text-center">Showing results for ${searchTerm}</p>
                        <form action="${context}/performers" method="GET" class="text-center">
                            <label for="performerName">Name</label>
                            <input type="text" name="performerName" id="performerName" value="${searchTerm}" required>
                            <input type="submit">
                        </form>
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-4 justify-content-center">
                            <c:forEach items="${performers}" var="performer">
                                <c:set var="performer" value="${performer}" scope="request" />
                                <jsp:include page="components/performerSimple.jsp" />
                            </c:forEach>
                        </div>

                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
    </body>
</html>