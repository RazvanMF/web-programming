<?php
    session_start();
    $mysqli = new mysqli('localhost', 'root', '', 'forest1');

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $name = $mysqli->real_escape_string($_POST['docname']);
        $keyword1 = $mysqli->real_escape_string($_POST['keyword1']);
        $keyword2 = $mysqli->real_escape_string($_POST['keyword2']);
        $keyword3 = $mysqli->real_escape_string($_POST['keyword3']);
        $keyword4 = $mysqli->real_escape_string($_POST['keyword4']);
        $keyword5 = $mysqli->real_escape_string($_POST['keyword5']);
    
        $sql = "UPDATE documents SET keyword1 = '$keyword1', keyword2 = '$keyword2', keyword3 = '$keyword3', keyword4 = '$keyword4', keyword5 = '$keyword5' WHERE name = '$name'";
    
        if ($mysqli->query($sql) === true) {
          header("location: index.php");
        } else {
          header("location: update.php");
        }
    
      }
?>
<!DOCTYPE html>
<head>
    <h1>HAIII :3</h1>
    <form method="POST" action="update.php">
        <input type="text" name="docname" placeholder="name" required/>
        <input type="text" name="keyword1" placeholder="keyword1" required/>
        <input type="text" name="keyword2" placeholder="keyword2" required/>
        <input type="text" name="keyword3" placeholder="keyword3" required/>
        <input type="text" name="keyword4" placeholder="keyword4" required/>
        <input type="text" name="keyword5" placeholder="keyword5" required/>

        <input class="submit" type="submit" name="update" value="Update this document">
    </form>
</head>