document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("login-form");

    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const htmlbody = {
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        };

        let request = new XMLHttpRequest();
        request.open("POST", "login.php", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                let response = JSON.parse(request.responseText)
                if (response.success) {
                    alert(response.message);
                    localStorage.setItem('username', htmlbody.username);
                    window.location.href = response.redirect;
                }
                else {
                    alert(response.message);
                }
            }
        }

        request.send("username=" + encodeURIComponent(htmlbody.username) + "&password=" + encodeURIComponent(htmlbody.password));
        alert("RAAAAAAH! SUCCESSFUL ENTRY :X");
    });
});