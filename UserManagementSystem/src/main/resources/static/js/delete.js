document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");

  if (form) {
    form.addEventListener("submit", function (event) {
      const idInput = document.getElementById("id");
      const idValue = Number(idInput.value.trim());

      if (idValue <= 0) {
        alert("員工編號不得小於 0 !");
        event.preventDefault();
      }
    });
  }
});
