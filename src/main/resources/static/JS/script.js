console.log("Script loaded");

// Initially get theme from localStorage or default to 'light'
function getTheme() {
    return localStorage.getItem("theme") ||
           (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light');
}

// Save theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Update the theme on the page
function updatePageTheme(theme) {
    const html = document.querySelector("html");

    // Remove any existing theme classes
    html.classList.remove("light", "dark");

    // Add the new theme class
    html.classList.add(theme);

    // Update local storage
    setTheme(theme);

    // Update button text
    const themeButtonText = document.querySelector("#theme_change_button span");
    if (themeButtonText) {
        themeButtonText.textContent = theme === "light" ? "dark" : "light";
    }
}

// Initialize theme functionality
function initTheme() {
    const currentTheme = getTheme();
    console.log("Initial theme:", currentTheme);

    // Apply the theme to the page
    updatePageTheme(currentTheme);

    // Set up theme toggle button
    const changeThemeButton = document.querySelector("#theme_change_button");
    const sidebarThemeButton = document.querySelector("#theme_change_button_sidebar");

    if (changeThemeButton) {
        changeThemeButton.addEventListener("click", toggleTheme);
    }

    if (sidebarThemeButton) {
        sidebarThemeButton.addEventListener("click", toggleTheme);
    }
}

// Toggle between dark and light themes
function toggleTheme() {
    console.log("Theme toggle clicked");
    const currentTheme = document.querySelector("html").classList.contains("dark") ? "dark" : "light";
    const newTheme = currentTheme === "dark" ? "light" : "dark";

    console.log(`Switching from ${currentTheme} to ${newTheme}`);
    updatePageTheme(newTheme);
}

// Run when DOM is fully loaded
document.addEventListener("DOMContentLoaded", initTheme);

// Handle theme changes even if script runs after DOM is loaded
if (document.readyState === 'complete' || document.readyState === 'interactive') {
    initTheme();
}