<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<head>
    <script src="${context}/js/fetchStates.js"></script>
</head>
<body data-context="${context}">
<div class="container">

    <c:import url="nav.jsp" />
    <c:choose>
        <c:when test="${empty citySearched && empty stateSearched}">
            <h1 class="text-center">Search for music venues</h1>
            <form action="${context}/venues" method="GET" class="text-center">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="state">State</label>
                <select name="state" id="stateSelect" required>
                    <option value="">State</option>
                </select>
                <input type="submit">
            </form>
        </c:when>
        <c:otherwise>
            <h1 class="text-center">Venues in ${citySearched}, ${stateSearched}</h1>
            <form action="${context}/venues" method="GET" class="text-center">
                <label for="city">City</label>
                <input type="text" name="city" id="city" value="${citySearched}"required>
                <label for="stateSelect">State</label>
                <select name="state" id="stateSelect" required>
                    <option value="">State</option>
                </select>
<%--                <input type="text" name="state" id="state" value="${stateSearched}" required>--%>
                <input type="submit" value="Search">
            </form>
            <c:choose>
                <c:when test="${allVenues.size() > 0}">
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-3 justify-content-center my-3">
                        <c:forEach items="${allVenues}" var="venue">
                            <c:set var="venue" value="${venue}" scope="request"/>
                            <jsp:include page="components/venueSimple.jsp"/>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-center mt-3">No results for ${citySearched}, ${stateSearched}</p>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
