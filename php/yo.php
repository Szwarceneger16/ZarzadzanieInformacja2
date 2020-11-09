<?php
require "passw.php";
$dbname = "sg44522";

$sql = "SELECT * FROM `offices`";

// MySQLi Object-oriented
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $count = 0;
    while ($row = $result->fetch_assoc()) {
        echo "Wiersz= " . $count . " | officeCode= " . $row["officeCode"] . " , city= " . $row["city"] . " , country= " . $row["country"] . "\n" ;
        $count++;
    }
} else {
    echo "0 results";
}
$conn->close();

echo "\n\n";
// MySQLi Procedural

$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    $count = 0;
    while($row = mysqli_fetch_assoc($result)) {
        echo "Wiersz= " . $count . " | officeCode= " . $row["officeCode"] . " , city= " . $row["city"] . " , country= " . $row["country"] . "\n" ;
        $count++;
    }
} else {
    echo "0 results";
}

mysqli_close($conn);

echo "\n\n";
// MySQLi Procedural

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $conn->query($sql);

    // set the resulting array to associative
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    $count = 0;
    foreach($result as $row) {
        echo "Wiersz= " . $count . " | officeCode= " . $row["officeCode"] . " , city= " . $row["city"] . " , country= " . $row["country"] . "\n" ;
        $count++;
    }
} catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
}
$conn = null;

?>