<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="col">
    <div class="card h-100 bg-warning">
        <div class="card-body">
            <div class="h5 card-title text-center"><a href="${context}/venues/${venue.id}">${venue.name}</a></div>
            <div class="card-subtitle">
                    <div class="text-center h6">
                        <strong>${venue.streetAddress}</strong>
                    </div>
                    <div class="text-center h6">
                        <strong>${venue.city}, ${venue.state}</strong>
                    </div>
            </div>
            <div class="card-text">
                <div>
                    ${venue.description}
                </div>
            </div>
        </div>
        <div class="card-footer text-center">${venue.followers.size()} following</div>
    </div>
</div>