function insertIntoUpdate(id, author, title, pages, genre, userx) {
    document.getElementById("updateid").value = id;
    document.getElementById("updateauthor").value = author;
    document.getElementById("updatetitle").value = title;
    document.getElementById("updatepages").value = pages;
    document.getElementById("updategenre").value = genre;

    let user = $('#update-user-filter');
    user.val(userx).change();

    alert("RAAAAAAH! preparing to update a book :}");
}

function purgeFromUpdate() {
    document.getElementById("updateid").value = "";
    document.getElementById("updateauthor").value = "";
    document.getElementById("updatetitle").value = "";
    document.getElementById("updatepages").value = "";
    document.getElementById("updategenre").value = "";

    let user = $('#update-user-filter');
    user.val(null).change();

    alert("RAAAAAAH! deleted entries from the textboxes :*");
}

function gatherUsers() {
    $.ajax({
        url: 'gatherusers.php',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let addUserFilter = $('#add-user-filter')
            addUserFilter.empty()
            addUserFilter.append($('<option>', {
                value: null,
                text: 'null'
            }))
            $.each(data.users, function (i, user) {
                addUserFilter.append($('<option>', {
                    value: user,
                    text: user
                }))
            })

            let updateUserFilter = $('#update-user-filter')
            updateUserFilter.empty()
            updateUserFilter.append($('<option>', {
                value: null,
                text: 'null'
            }))
            $.each(data.users, function (i, user) {
                updateUserFilter.append($('<option>', {
                    value: user,
                    text: user
                }))
            })
        }
    })
}

function fetcher() {
    const book_container = document.getElementById("book-container");

    let request = new XMLHttpRequest();
    request.open("GET", "readbooks.php", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
        let innerhtml = "";
        if (request.readyState === 4 && request.status === 200) {
            let response = JSON.parse(request.responseText)
            response.forEach(index => {
                let modfunc = `insertIntoUpdate(${index.id}, '${index.author}', '${index.title}', ${index.pages}, '${index.genre}', '${index.lended_to}')`;

                innerhtml += "<div class=\"booklet-container\">";
                //innerhtml += "<div>" + "ID: " + index.id + "</div>";
                innerhtml += "<div>" + "Author: " + index.author + "</div>";
                innerhtml += "<div>" + "Title: " + index.title + "</div>";
                innerhtml += "<div>" + "Pages: " + index.pages + "</div>";
                innerhtml += "<div>" + "Genre: " + index.genre + "</div>";
                innerhtml += "<div>" + "Lended to: " + index.lended_to + "</div>";
                innerhtml += "<div class=\"button-bar\">"
                innerhtml += "<button onclick=\"deleteBook(" + index.id + ")\">DELETE</button>";
                innerhtml += "<button onclick=\"" + modfunc + "\">MODIFY</button>";
                innerhtml += "</div>"
                innerhtml += "</div>";
            });
            book_container.innerHTML = innerhtml;
        }
    }

    request.send();
    alert("RAAAAAAH! INFO GRABBED >///<");
}

function deleteBook(id) {
    if (confirm("are you sure you want to delete this? >_<\ndeleting is kinda permanent :P")) {
        let request = new XMLHttpRequest();
        request.open("POST", "deletebook.php", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                let response = JSON.parse(request.responseText)
                if (response.success) {
                    alert(response.message);
                    fetcher();
                }
                else {
                    alert(response.message);
                }
            }
        }

        request.send("id=" + encodeURIComponent(id));

        alert("RAAAAAAH! deleted a book :O");
    }
}

function goBack() {
    localStorage.setItem('username', "");
    window.location.href = "index.html";
}

document.addEventListener("DOMContentLoaded", function() {
    fetcher();
    gatherUsers();

    const addForm = document.getElementById("book-adder-form");
    const updateForm = document.getElementById("book-updater-form");

    addForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const htmlbody = {
            author: document.getElementById("addauthor").value,
            title: document.getElementById("addtitle").value,
            pages: document.getElementById("addpages").value,
            genre: document.getElementById("addgenre").value,
            lended_to: document.getElementById("add-user-filter").value
        };

        let request = new XMLHttpRequest();
        request.open("POST", "addbook.php", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                let response = JSON.parse(request.responseText)
                if (response.success) {
                    alert(response.message);
                    fetcher();
                }
                else {
                    alert(response.message);
                }
            }
        }

        request.send("author=" + encodeURIComponent(htmlbody.author) +
         "&title=" + encodeURIComponent(htmlbody.title) +
         "&pages=" + encodeURIComponent(htmlbody.pages) +
         "&genre=" + encodeURIComponent(htmlbody.genre) +
         "&lended_to=" + encodeURIComponent(htmlbody.lended_to));

        alert("RAAAAAAH! added a book >_<");
    });

    updateForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const htmlbody = {
            id: document.getElementById("updateid").value,
            author: document.getElementById("updateauthor").value,
            title: document.getElementById("updatetitle").value,
            pages: document.getElementById("updatepages").value,
            genre: document.getElementById("updategenre").value,
            lended_to: document.getElementById("update-user-filter").value
        };

        let request = new XMLHttpRequest();
        request.open("POST", "updatebook.php", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                let response = JSON.parse(request.responseText)
                if (response.success) {
                    alert(response.message);
                    fetcher();
                }
                else {
                    alert(response.message);
                }
            }
        }

        request.send("id=" + encodeURIComponent(htmlbody.id) +
        "&author=" + encodeURIComponent(htmlbody.author) +
        "&title=" + encodeURIComponent(htmlbody.title) +
        "&pages=" + encodeURIComponent(htmlbody.pages) +
        "&genre=" + encodeURIComponent(htmlbody.genre) +
        "&lended_to=" + encodeURIComponent(htmlbody.lended_to));

        alert("RAAAAAAH! updated a book >_<");
    });
});