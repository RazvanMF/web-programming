<?php

//CORS-related stuff
header("Access-Control-Allow-Origin: http://localhost:4200");  // (Origin will be useful for Angular, as that will be also hosted on a localhost port)
header("Access-Control-Allow-Methods: GET");  // (Method is just so only POSTs can be sent)
header("Access-Control-Allow-Headers: Content-Type");  // (Headers is so we only accept requests of the "Content-Type header")
header("Content-Type: application/json");  // What we expect from a Content-Type

//variables for server name (always localhost), username (always root), password (always nothing) and database name (always lab06)
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "lab06";

//think of this as the C# SqlConnection class
$connection = new mysqli($servername, $username, $password, $dbname);

if ($connection->connect_error) {
    die("Connection failed: " . $connection->connect_error);
}

//this will be the JSON response for our request
$response = array();

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $sql = "SELECT * FROM books";
    $result = $connection->query($sql);

    //result holds the number of rows fetched by the select query
    $id = 0;
    while ($row = $result->fetch_assoc()) {
        foreach($row as $key => $val) {
            $response[$id][$key] = $val;
        }
        $id = $id + 1;
    }
}
else {
    $response["success"] = false;
    $response["message"] = "Why are you being naughty and not request via GET? >:(";
}

header("Content-Type: application/json");
echo json_encode($response);  // output the response, JSON-encoded

?>