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

//think of this as the C# SqlConnection class
$connection = new mysqli($servername, $username, $password, $dbname);

if ($connection->connect_error) {
    die("Connection failed: " . $connection->connect_error);
}

//this will be the JSON response for our request
$response = array();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    //from our html body, take 'username' and 'password'
    $username = htmlspecialchars($_POST['username']);
    $password = htmlspecialchars($_POST['password']); 

    if (empty($username) || empty($password)) {
        //create fields for "success" and "message" inside our JSON response array
        $response["success"] = false;
        $response["message"] = "You forgot to complete the fields, dum dum ;)";
    }

    else {
        //command to use on the mysql database, and to be execute via the query function
        $sql = "SELECT * FROM login WHERE username = '$username' AND password = '$password'";
        $result = $connection->query($sql);

        //result holds the number of rows fetched by the select query
        if ($result->num_rows == 1) {
            $row = $result->fetch_assoc();  // fetches current row, moves cursor to next
            if ($row["username"] == "admin" && $row["password"] == "admin") {
                $response["success"] = true;
                $response["message"] = "Login successful, dear admin ;)";
                $response["redirect"] = "admin.html";
            }
            else {
                $response["success"] = true;
                $response["message"] = "Login successful, dear client :)";
                $response["redirect"] = "client.html";
            }
        }
        else {
            $response["success"] = false;
            $response["message"] = "We can't find you inside our gigantic database :(\nPlease try again! >\\\\\\<";
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