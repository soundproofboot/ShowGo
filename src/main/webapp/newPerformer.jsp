<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<body>
<c:if test="${empty user}">
    <c:redirect url="/" />
</c:if>
<c:import url="nav.jsp" />
<h1>add a performer</h1>
</body>
</html>