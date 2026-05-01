<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<script>

    const performers = ${performersJson};
    let lineupArr = [];

    function addBandToLineup() {
        let selectEl = document.querySelector("select");
        let lineupInput = document.querySelector("#lineup");
        let performerId = selectEl.value;
        let performer = performers.find(p => p.id === Number.parseInt(performerId));
        if (performer) {
            if (!lineupArr.some(p => p.id === performer.id)) {
                lineupArr.push(performer);
            }
            lineupInput.value = lineupArr.map(p => p.id).join(",");

            updateLineupDisplayed();
        }
    }

    function updateLineupDisplayed() {
        let lineupList = document.querySelector("#lineupList");
        lineupList.innerHTML = "";
        for (let performer of lineupArr) {
            let performerLi = document.createElement("li");
            let performerNameEl = document.createElement("span");
            performerNameEl.textContent = performer.name;

            let removeBtn = document.createElement("button");
            removeBtn.innerText = "X";
            removeBtn.addEventListener("click", () => {
                removePerformerFromLineup(performer.id);
                updateLineupDisplayed();
            })
            performerLi.append(performerNameEl, removeBtn);

            lineupList.appendChild(performerLi);
        }
    }

    function removePerformerFromLineup(performerId) {
        lineupArr = lineupArr.filter(p => p.id !== performerId);
    }

    window.onload = () => {
        let selectEl = document.querySelector("select");

        for (let p of performers) {
            let performerOption = document.createElement("option");
            performerOption.value = p.id;
            performerOption.innerText = p.name;
            selectEl.appendChild(performerOption);
        }
    }
</script>
<body>
<c:import url="nav.jsp" />
<c:choose>
<%--    NO VENUE WITH THIS ID--%>
    <c:when test="${empty venue}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1>${venue.name}</h1>
        <c:set var="isOwner" value="${venue.user.id == user.id}" />
        <c:choose>
            <c:when test="${isOwner}">
<%--        OWNER--%>
                <p>Upcoming events</p>
                <c:choose>
                    <c:when test="${venue.events.size() == 0}">
                        <p>No upcoming events.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${venue.events}" var="event">
                            <p>name: ${event.title}</p>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <p>New Event</p>
                <form action="${context}/newEvent" method="post">
                    <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                    <input type="hidden" name="lineup" id="lineup" value="">
                    <label for="title">Event Title</label>
                    <input type="text" name="title" id="title" required>
                    <label for="event_start">Event start</label>
                    <input
                            type="datetime-local"
                            name="event_start"
                            id="event_start"
                            required
                    >
                    <input type="submit" value="Create Event">
                </form>
                <div>
                    <p>Lineup</p>
                    <ul id="lineupList">

                    </ul>

                </div>

                <label for="selectBand">Select a band to add</label>
                <select name="selectBand" id="selectBand">
                    <option value="">--Select a performer and click add</option>
                </select>
                <button onclick="addBandToLineup()">Add</button>
            </c:when>
            <c:otherwise>
<%--                NOT OWNER--%>
                <c:set var="isFollowed" value="false" />
                <c:choose>
                    <c:when test="${not empty user}">
                        <c:forEach items="${user.venueFollows}" var="venueFollow">
                            <c:if test="${venueFollow.venue.id == venue.id}">
                                <c:set var="isFollowed" value="true" />
                            </c:if>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${isFollowed}">
                        <p>Followed</p>
                        <form action="${context}/removeVenueFollow" method="POST">
                            <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                            <input type="submit" value="Remove" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${context}/addVenueFollow" method="POST">
                            <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                            <input type="submit" value="Follow" />
                        </form>
                    </c:otherwise>
                </c:choose>
                <p>managed by: <a href="${context}/users/${venue.user.username}">${venue.user.username}</a></p>
                <p>Fans: ${venue.followers.size()}</p>
                <p>events</p>
                <ul>
                    <c:forEach items="${venue.events}" var="event">
                        <li>
                            <h3><a href="${context}/events/${event.id}">${event.title}</a></h3>
                            <p>lineup</p>
                            <ul>
                                <c:forEach items="${event.performers}" var="eventPerformer">
                                    <li>${eventPerformer.performer.name}</li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>