function filterTable() { // filter the table by username, using search bar
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchbar");
    filter = input.value.toUpperCase();
    table = document.getElementById("leaderboardTable");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) { // get the username row, and hide rows that do not match the search string
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function sortTable() {
    const table = document.getElementById("leaderboardTable");
    const tbody = table.getElementsByTagName("tbody")[0];
    const rows = Array.from(tbody.getElementsByTagName("tr"));

    const sortOption = document.getElementById("sort").value;

    const compareFunction = (a, b) => {
        if (sortOption === "username") {
            const usernameA = a.getElementsByTagName("td")[1].innerText.toLowerCase();
            const usernameB = b.getElementsByTagName("td")[1].innerText.toLowerCase();
            return usernameA.localeCompare(usernameB);
        } else if (sortOption === "position") {
            const positionA = parseInt(a.getElementsByTagName("td")[0].innerText);
            const positionB = parseInt(b.getElementsByTagName("td")[0].innerText);
            return positionA - positionB;
        }
        return 0;
    };

    const sortedRows = rows.sort(compareFunction);

    // Remove existing rows
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }

    // Append sorted rows to the tbody
    sortedRows.forEach(row => {
        tbody.appendChild(row);
    });
}

