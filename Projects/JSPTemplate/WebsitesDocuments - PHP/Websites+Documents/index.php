<?php
    session_start();
    $mysqli = new mysqli('localhost', 'root', '', 'forest1');

    if(isset($_POST['edit'])){
        header("location: update.php");
    }
?>

<!DOCTYPE html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <h1>Hello 4est!</h1>
    <form method="POST" action="index.php">
        <button type="submit" class="edit" name="edit"> Edit keywords of a document!</button>
    </form>

    <div>
        <table>
            <tr>
                <th>Name</th>
                <th>Count</th>
            </tr>
                <?php
          
                    $sql = "SELECT * FROM websites";
                    $response = mysqli_query($mysqli, $sql);

                    $sql = "SELECT count(documents.websiteId) as nodocs        
                    from websites
                    left join documents
                    on (websites.id = documents.websiteId)
                    group by
                        websites.id";
                    $response2 = mysqli_query($mysqli, $sql);

                    while(($row = mysqli_fetch_array($response)) && ($row2 = mysqli_fetch_array($response2))){
                        $url = ''. $row['URL'] .'';

                        echo '<tr>';
                        echo '<td>'. $url . '</td>';

                        $count = ''. $row2['nodocs'] .'';

                        
                        echo '<td>' . $count . '</td>';
                        echo '</tr>';
                    }
                ?>
        </table>
    </div>

    <form method="POST" action="search.php">
        <input type="text" name="keywords" placeholder="keywords" required/>
        <button type="submit" class="find" name="find"> Find documents!</button>
    </form>
</head>