<?php

header("Access-Control-Allow-Origin: http://localhost:4200");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "lab06";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("conn failed: " . $conn->connect_error);
}

$response = array();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $author = htmlspecialchars($_POST['author']) ?? '';
    $genre = htmlspecialchars($_POST['genre']) ?? '';
    $username = htmlspecialchars($_POST['username']) ?? '';

    $sql = "SELECT * FROM books";
    if ($author || $genre || $username) {
        $sql .= " WHERE TRUE";

        if ($author) {
            $sql .= " AND author = '$author'";
        }
        if ($genre) {
            $sql .= " AND genre = '$genre'";
        }
        if ($username) {
            $sql .= " AND (lended_to IS NULL OR lended_to = '$username')";
        }
    }

    $result = $conn->query($sql);
    while ($row = $result->fetch_assoc()) {
        $response[] = $row;
    }
}

header("Content-Type: application/json");
echo json_encode($response);  // output the response, JSON-encoded

?>