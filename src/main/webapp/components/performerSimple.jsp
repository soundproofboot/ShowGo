<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="col">
    <div class="card h-100">
        <div class="card-header text-center">
            <div class="card-title h4"><a href="${context}/performers/${performer.id}">${performer.name}</a></div>
            <div class="card-subtitle h5">${performer.genre}</div>
        </div>
        <div class="card-body">
            ${performer.description}
        </div>
    </div>
</div>