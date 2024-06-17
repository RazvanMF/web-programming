<?php

header("Access-Control-Allow-Origin: http://localhost:4200");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "lab06";

$connection = new mysqli($servername, $username, $password, $dbname);

if ($connection->connect_error) {
    die("Connection failed: " . $connection->connect_error);
}

$response = array();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST['id'];
    $author = htmlspecialchars($_POST['author']);
    $title = htmlspecialchars($_POST['title']);
    $pages = $_POST['pages'];
    $genre = htmlspecialchars($_POST['genre']);
    $lended_to = htmlspecialchars($_POST['lended_to']);

    $can_continue = true;
    if (empty($author) || empty($title) || empty($pages) || empty($genre)) {
        $response["success"] = false;
        $response["message"] = "You forgot to complete the fields, dum dum ;)";
        $can_continue = false;
    }

    if (is_numeric($pages) == false) {
        $response["success"] = false;
        $response["message"] = "'$pages' is not a number, dum dum ;)";
        $can_continue = false;
    }

    if ($can_continue == true) {
        $sql = "";
        if ($lended_to == "null") {
            $sql = "UPDATE books SET author='$author', title='$title', pages=$pages, genre='$genre', lended_to=NULL WHERE id=$id";
        }
        else {
            $sql = "UPDATE books SET author='$author', title='$title', pages=$pages, genre='$genre', lended_to='$lended_to' WHERE id=$id";
        }
        $result = $connection->query($sql);
    
        if ($result == true) {
            $response["success"] = true;
            $response["message"] = "nice. book updated :D";
        }
        else {
            $response["success"] = false;
            $response["message"] = "not nice. book not updated :(";
        }
    }
}
else {
    $response["success"] = false;
    $response["message"] = "Why are you being naughty and not request via POST? >:(";
}

header("Content-Type: application/json");
echo json_encode($response);  // output the response, JSON-encoded

?>