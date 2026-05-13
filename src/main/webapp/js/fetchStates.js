async function fetchStates() {
    let context = document.body.dataset.context;
    let stateResponse = await fetch(`${context}/api/states`);
    return await stateResponse.json();
}

function populateStateOptions(stateList) {
    let selectEl = document.querySelector("#stateSelect");
    if (selectEl) {
        for (let state of stateList) {
            let optionEl = document.createElement("option");
            optionEl.setAttribute("value", state);
            optionEl.textContent = state;
            selectEl.appendChild(optionEl);
        }
    }
}

window.onload = async () => {
    let stateList = await fetchStates();
    if (stateList) {
        populateStateOptions(stateList);
    }
}