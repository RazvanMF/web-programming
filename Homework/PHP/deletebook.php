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

    $can_continue = true;
    if (empty($id)) {
        $response["success"] = false;
        $response["message"] = "this should not happen.\ni am the dum dum :(";
        $can_continue = false;
    }

    if ($can_continue == true) {
        $sql = "DELETE FROM books WHERE id='$id'";
        $result = $connection->query($sql);
    
        if ($result == true) {
            $response["success"] = true;
            $response["message"] = "nice. book deleted :D";
        }
        else {
            $response["success"] = false;
            $response["message"] = "not nice. book not deleted :(";
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