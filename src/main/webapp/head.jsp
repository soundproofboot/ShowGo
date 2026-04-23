<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="stylesheet" href="${context}/styles/styles.css">
<c:choose>
    <c:when test="${title != null}" >
        <title>${title}</title>
    </c:when>
    <c:otherwise>
        <title>ShowGo</title>
    </c:otherwise>
</c:choose>
</head>
