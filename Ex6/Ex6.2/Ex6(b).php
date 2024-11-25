<?php
// Database connection
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "PhoneDB";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// SQL query to fetch phone details
$sql = "SELECT id, brand, model, price FROM PhoneDetails";
$result = $conn->query($sql);

// Create a new XML document
$xml = new SimpleXMLElement('<PhoneDetails/>');

// Fetch the data and add it to the XML
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        // Add each phone as an XML element
        $phone = $xml->addChild('Phone');
        $phone->addChild('ID', $row['id']);
        $phone->addChild('Brand', $row['brand']);
        $phone->addChild('Model', $row['model']);
        $phone->addChild('Price', $row['price']);
    }
} else {
    echo "0 results found";
}

// Set the correct header to output XML
header('Content-Type: text/xml');

// Output the XML
echo $xml->asXML('output.xml');

// Close the connection
$conn->close();
?>
