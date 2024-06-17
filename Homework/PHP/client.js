function loadAuthors() {
    $.ajax({
        url: 'client.php',
        data: { author: true },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var authorFilter = $('#author-filter')
            authorFilter.empty()
            authorFilter.append($('<option>', {
                value: '',
                text: 'All Authors'
            }))
            $.each(data.authors, function (i, author) {
                authorFilter.append($('<option>', {
                    value: author,
                    text: author
                }))
            })
        }
    })
}

function loadGenres() {
    $.ajax({
        url: 'client.php',
        data: { genre: true },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var genreFilter = $('#genre-filter')
            genreFilter.empty()
            genreFilter.append($('<option>', {
                value: '',
                text: 'All Genres'
            }))
            $.each(data.genres, function (i, genre) {
                genreFilter.append($('<option>', {
                    value: genre,
                    text: genre
                }))
            })
        }
    })
}

function applyFilters(appendToLalaLand = true) {
    let author = $('#author-filter').val();
    let genre = $('#genre-filter').val();

    let history = $('#old-filters');
    let results = $('#book-container');
    results.empty();

    $.ajax({
        url: 'client.php',
        data: { author: author, genre: genre, username: localStorage.getItem('username') },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let entries = data.entries
            if (entries.length > 0) {
                $.each(entries, function (i, entry) {
                    let div = $('<div>').addClass('booklet-container').addClass('text-element-comic');
                    div.append($('<div>').text("Author: " + entry.author))
                    div.append($('<div>').text('Title: ' + entry.title))
                    div.append($('<div>').text('Pages: ' + entry.pages))
                    div.append($('<div>').text('Genre: ' + entry.genre))
                    div.append($('<div>').text('Lended to: ' + entry.lended_to))
                    if (entry.lended_to === null) {
                        div.append($('<button>').text('GET THIS BOOK :3').on('click', function() {
                            lendBook(entry.id, localStorage.getItem('username'));
                        }));
                    }
                    else {
                        div.append($('<button>').text('GIVE BACK THIS BOOK :o').on('click', function() {
                            lendBook(entry.id, "null");
                        }));
                    }
                    results.append(div);
                })
            } else {
                results.append($('<div>').text('No entries found').addClass('text-element-comic'));
            }

            if (appendToLalaLand == true) {
                let historylette = $('<div>').addClass("historylette-displayer");
                historylette.append($('<div>').text("\tOPERATION PERFORMED :* (" + (author == "" ? "ALL" : author) + ", " + (genre == "" ? "ALL" : genre) + ")"));
                historylette.append($('<button>').text("GO BACK IN TIME (hehe)").on("click", function() {
                    let gotoAuthor = (author == "" ? "" : author);
                    let gotoGenre = (genre == "" ? "" : genre);
                    daisanNoBakudanbitesZaDusto(gotoAuthor, gotoGenre);
                }));
                history.append(historylette);
            }
        }
    })
}

function lendBook(bookID, name) {
    $.ajax({
        url: 'lenderfunctionality.php?username=' + name + '&id=' + bookID,
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            alert("YOU'VE SUCCESSFULLY COMPLETED THIS OPERATION, nyan :3");
            applyFilters(false);
        },
        error: function(data) {
            console.log(data);
        }
    });
}

function goBack() {
    localStorage.setItem('username', "");
    window.location.href = 'index.html';
}

function daisanNoBakudanbitesZaDusto(author, genre) {
    authorFilter = $('#author-filter');
    genreFilter = $('#genre-filter');
    authorFilter.val(author).change();
    genreFilter.val(genre).change();
    applyFilters(false);
}

document.addEventListener("DOMContentLoaded", function() {
    loadAuthors();
    loadGenres();
});