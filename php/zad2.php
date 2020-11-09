<?php
require "passw.php";
$dbname = "zi2";

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password,array(PDO::ATTR_PERSISTENT => true));
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $e) {
    $conn->rollBack();
    print $e->getMessage() . $e->getLine();
}
try {
    $sql = "CREATE TABLE Persons (
        id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        Name varchar(255) NOT NULL,
        Age int(10) NOT NULL);";
    $conn->exec($sql);

    $conn->beginTransaction();

    // poelcenie 2
    $conn->exec("INSERT INTO Persons(Name,Age) 
        VALUES ('jan',30)");
    $conn->exec("INSERT INTO Persons(Name,Age) 
        VALUES ('jan',30)");
    $conn->exec("INSERT INTO Persons(Name,Age) 
        VALUES ('jan',30)");

    // poelcenie 3
    $sql = $conn->prepare("INSERT INTO Persons(Name,Age) 
        VALUES (?,?)");

    $names = array("agnieszka","kasia","bartosz","jaroslaw","marta","zbyszek","grzegorz","piotr","mateusz","kacper");

    for ($i = 0; $i < count($names); $i++) {
        $sql->bindValue(1,$names[$i], PDO::PARAM_STR);
        $sql->bindValue(2, rand(0, 60), PDO::PARAM_INT);
        $sql->execute();
    }

    //$conn->commit();
    $conn->rollBack();
} catch(PDOException $e) {
    $conn->rollBack();
    print $e->getMessage() . $e->getLine();
}

$conn = null;
?>