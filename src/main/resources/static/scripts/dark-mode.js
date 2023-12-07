// const darkModeToggle = document.getElementById('darkModeToggle');
// const body = document.body;
//
// darkModeToggle.addEventListener('change', () => {
//     body.classList.toggle('dark-mode', darkModeToggle.checked);
// });
//
//
// // Save user preference
// localStorage.setItem('darkMode', darkModeToggle.checked);
//
// // Retrieve user preference on page load
// const savedDarkMode = JSON.parse(localStorage.getItem('darkMode'));
// body.classList.toggle('dark-mode', savedDarkMode);
// darkModeToggle.checked = savedDarkMode;


function myFunction() {
    var element = document.body;
    element.dataset.bsTheme =
        element.dataset.bsTheme == "light" ? "dark" : "light";
}