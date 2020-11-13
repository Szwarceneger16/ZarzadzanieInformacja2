<?php
require_once __DIR__ . '/vendor/autoload.php';
use Doctrine\DBAL\DriverManager;

$connectionParams = array(
    'dbname' => 'nowa_pusta_php',
    'user' => 'root',
    'password' => 'Jajeczko777',
    'host' => 'localhost',
    'driver' => 'pdo_mysql',
);

$conn = \Doctrine\DBAL\DriverManager::getConnection($connectionParams);

// polecenie 1
$queryBuilder = $conn->createQueryBuilder();
$queryBuilder->select('*')
    ->from('customers')
    ->where('country = ?');

try {
    $request = $conn->executeQuery($queryBuilder->getSQL(),array(1 => 'USA'),array(1 => 'string'));
} catch (\Doctrine\DBAL\Exception $e) {
}
$response = $request->fetchAllAssociative();
foreach ($response as $item) {
    foreach ($item as $value) {
        echo $value . " | ";
    }
    echo "\n";
}
echo "\n";
echo "\n";

// polecenie 2
$queryBuilder = $conn->createQueryBuilder();
$queryBuilder->select('c.customerNumber','c.customerName','e.firstName','e.lastName')
    ->from('customers','c')
    ->innerJoin('c','employees','e','c.salesRepEmployeeNumber = e.employeeNumber');
//$probnysql = "SELECT c.customerNumber,c.customerName,e.firstName,e.lastName from customers as c inner join employees as e on c.salesRepEmployeeNumber=e.employeeNumber";
try {
    $request = $conn->executeQuery($queryBuilder->getSQL());
    $response = $request->fetchAllAssociative();
    foreach ($response as $item) {
        foreach ($item as $value) {
            echo $value . " | ";
        }
        echo "\n";
    }
} catch (\Doctrine\DBAL\Exception $e) {
    echo $e;
}

//polecenie 3
$schema = new \Doctrine\DBAL\Schema\Schema();
$newTable = $schema->createTable("wlasna");
$id = $newTable->addColumn("id",'integer',array("unsigned" => true));
$id->setAutoincrement(true);
$newTable->addColumn('napis',"string",array("length" => 255));
$newTable->addColumn("liczba","integer");
$newTable->setPrimaryKey(array("id"));
$newTable->addUniqueIndex(array("napis"));

//specyfikacja wksazuje na porzucenie metody executeUpdate, status depreceted
$conn->executeUpdate($schema->toSql($conn->getDatabasePlatform())[0]);

//poelcenie 4
$napis = 'napis';
$liczba = 1;

$queryBuilder4 = $conn->createQueryBuilder();
$queryBuilder4->insert('wlasna')
    ->setValue('napis','?')
    ->setValue('liczba','?')
    ->setParameter(0,'napis1')
    ->setParameter(1,1);
$queryBuilder4->execute();

$queryBuilder4->insert('wlasna')
    ->setValue('napis','?')
    ->setValue('liczba','?')
    ->setParameter(0,'napis2')
    ->setParameter(1,2);

$queryBuilder4->execute();

