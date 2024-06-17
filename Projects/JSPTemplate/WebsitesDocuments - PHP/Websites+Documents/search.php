<?php
session_start();
$conn = new mysqli('localhost', 'root', '', 'forest1');


if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $keywords = isset($_POST['keywords']) ? $_POST['keywords'] : '';

    $keywordsArray = preg_split('/\s+/', trim($keywords));

    if (count($keywordsArray) < 3) {
        echo "Please enter at least 3 keywords.";
        exit;
    }

    $placeholders = implode(',', array_fill(0, count($keywordsArray), '?'));

    $sql = "SELECT * FROM Documents WHERE (
                (keyword1 IN ($placeholders)) +
                (keyword2 IN ($placeholders)) +
                (keyword3 IN ($placeholders)) +
                (keyword4 IN ($placeholders)) +
                (keyword5 IN ($placeholders))
            ) = 3";

    $stmt = $conn->prepare($sql);

    $types = str_repeat('s', count($keywordsArray));
    $stmt->bind_param($types . $types . $types . $types . $types, ...$keywordsArray, ...$keywordsArray, ...$keywordsArray, ...$keywordsArray, ...$keywordsArray);

    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        echo "<h1>Search Results</h1>";
        echo "<table border='1'>";
        echo "<tr><th>ID</th><th>Name</th><th>Keywords</th></tr>";
        while ($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td>" . $row['docId'] . "</td>";
            echo "<td>" . $row['name'] . "</td>";
            echo "<td>" . implode(', ', array_filter([$row['keyword1'], $row['keyword2'], $row['keyword3'], $row['keyword4'], $row['keyword5']])) . "</td>";
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "No documents found with exactly 3 keyword matches.";
    }

    $stmt->close();
}

$conn->close();
?>
