
    function fetchUsers() {
    var batch = document.getElementById("batchSelect").value;
    fetch("/getUsers?batch=" + batch)
    .then(response => response.json())
    .then(data => {
    let userListDiv = document.getElementById("userList");
    userListDiv.innerHTML = "";  // Clear the current list

    if (data.length > 0) {
    // Group users by batch
    let groupedUsers = groupUsersByBatch(data);

    // Generate HTML for each batch
    Object.keys(groupedUsers).forEach(batchKey => {
    let batchEntry = groupedUsers[batchKey];
    let batchDiv = document.createElement("div");
    batchDiv.classList.add("batch-info-container");

    let batchCardDiv = document.createElement("div");
    batchCardDiv.classList.add("batch-card");

    let batchTitle = document.createElement("h2");
    batchTitle.classList.add("batch-title");
    batchTitle.innerHTML = "Batch " + batchKey;

    let table = document.createElement("table");
    table.classList.add("batch-table");

    // Table headers
    let thead = document.createElement("thead");
    let headerRow = document.createElement("tr");
    ["Student ID", "Name", "Session", "Email", "Mobile"].forEach(header => {
    let th = document.createElement("th");
    th.innerText = header;
    headerRow.appendChild(th);
});
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Table body
    let tbody = document.createElement("tbody");
    batchEntry.forEach(user => {
    let row = document.createElement("tr");

    let tdStudentId = document.createElement("td");
    tdStudentId.innerText = user.studentId;
    row.appendChild(tdStudentId);

    let tdName = document.createElement("td");
    tdName.innerText = user.name;
    row.appendChild(tdName);

    let tdSession = document.createElement("td");
    tdSession.innerText = user.session;
    row.appendChild(tdSession);

    let tdEmail = document.createElement("td");
    tdEmail.innerText = user.email;
    row.appendChild(tdEmail);

    let tdMobile = document.createElement("td");
    tdMobile.innerText = user.mobile;
    row.appendChild(tdMobile);

    tbody.appendChild(row);
});
    table.appendChild(tbody);

    batchCardDiv.appendChild(batchTitle);
    batchCardDiv.appendChild(table);
    batchDiv.appendChild(batchCardDiv);
    userListDiv.appendChild(batchDiv);
});
} else {
    userListDiv.innerHTML = "<p>No users found.</p>";
}
})
    .catch(error => console.error('Error fetching users:', error));
}

    // Helper function to group users by batch
    function groupUsersByBatch(users) {
    return users.reduce((groups, user) => {
    const batchName = user.batch.batch;
    if (!groups[batch]) {
    groups[batch] = [];
}
    groups[batch].push(user);
    return groups;
}, {});
}

