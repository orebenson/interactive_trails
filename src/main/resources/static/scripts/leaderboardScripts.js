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