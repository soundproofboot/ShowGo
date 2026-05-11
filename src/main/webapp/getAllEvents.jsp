<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<div class="container">

    <c:import url="nav.jsp" />
    <c:choose>
        <c:when test="${empty citySearched && empty stateSearched}">
            <h1 class="text-center">Search for events</h1>
            <form action="${context}/events" method="GET" class="text-center">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <input type="text" name="state" id="state" value="${stateSearched}" required>
                <input type="submit">
            </form>
        </c:when>
        <c:otherwise>
            <h1 class="text-center">Events in ${citySearched}, ${stateSearched}</h1>
            <form action="${context}/events" method="GET" class="text-center">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <input type="text" name="state" id="state" value="${stateSearched}" required>
                <input type="submit">
            </form>
            <c:choose>
                <c:when test="${allEvents.size() > 0}">
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-4 justify-content-center">

                    <c:forEach items="${allEvents}" var="event">
                        <c:set var="event" value="${event}" scope="request"/>
                        <jsp:include page="components/eventSimple.jsp"/>
                    </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-center">No results for ${citySearched}, ${stateSearched}</p>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>