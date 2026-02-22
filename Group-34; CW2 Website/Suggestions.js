document.addEventListener("DOMContentLoaded", () => {

  const form = document.getElementById("suggestForm");

  form.addEventListener("submit", function (e) {
    e.preventDefault(); // Prevent form submit

    const isValid = form.checkValidity();

    if (!isValid) {
      // Triggers the browsers built-in validation UI / asks for correct format
      form.reportValidity(); // This shows the red outlines/tooltips
      return;
    }

    const formData = new FormData(form);
    const entries = Object.fromEntries(formData.entries());

    // Show the confirmation popup when user has correctly filled out the from
    alert(`Thank you for your suggestion!\n\nGame: ${entries.title}\nPlatform: ${entries.platform}`);

    form.reset();
  });

});