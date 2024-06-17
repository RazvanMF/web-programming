<?php

//CORS-related stuff
header("Access-Control-Allow-Origin: http://localhost:4200");  // (Origin will be useful for Angular, as that will be also hosted on a localhost port)
header("Access-Control-Allow-Methods: POST");  // (Method is just so only POSTs can be sent)
header("Access-Control-Allow-Headers: Content-Type");  // (Headers is so we only accept requests of the "Content-Type header")
header("Content-Type: application/json");  // What we expect from a Content-Type

//variables for server name (always localhost), username (always root), password (always nothing) and database name (always lab06)
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "lab06";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("conn failed: " . $conn->connect_error);
}

$lender_username = $_POST['username'];
$book_id = $_POST['id'];

$sql = "";
if ($lender_username != "null")
    $sql = "UPDATE books SET lended_to = '$lender_username' WHERE id = $book_id";
else
    $sql = "UPDATE books SET lended_to = NULL WHERE id = $book_id";

$result = $conn->query($sql);

$response['result'] = $result;

echo json_encode($response);
$conn->close();

?>