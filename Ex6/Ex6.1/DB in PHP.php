<?php
// Database connection variables
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

// If form is submitted to add a new phone
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['brand'])) {
    $brand = $_POST['brand'];
    $model = $_POST['model'];
    $price = $_POST['price'];

    $sql = "INSERT INTO PhoneDetails (brand, model, price) VALUES ('$brand', '$model', '$price')";

    if ($conn->query($sql) === TRUE) {
        echo "<p>New record created successfully.</p>";
    } else {
        echo "<p>Error: " . $sql . "<br>" . $conn->error . "</p>";
    }
}

// If form is submitted to update phone details
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['update_id'])) {
    $id = $_POST['update_id'];
    $brand = $_POST['update_brand'];
    $model = $_POST['update_model'];
    $price = $_POST['update_price'];

    $sql = "UPDATE PhoneDetails SET brand='$brand', model='$model', price='$price' WHERE id='$id'";

    if ($conn->query($sql) === TRUE) {
        echo "<p>Record updated successfully.</p>";
    } else {
        echo "<p>Error updating record: " . $conn->error . "</p>";
    }
}

// If form is submitted to delete a phone
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['delete_id'])) {
    $id = $_GET['delete_id'];

    $sql = "DELETE FROM PhoneDetails WHERE id='$id'";

    if ($conn->query($sql) === TRUE) {
        echo "<p>Record deleted successfully.</p>";
    } else {
        echo "<p>Error deleting record: " . $conn->error . "</p>";
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phone Details CRUD Operations</title>
</head>
<body>
    <h1>Phone Details CRUD Operations</h1>

    <!-- Form to insert new phone -->
    <h2>Add New Phone</h2>
    <form action="phones.php" method="POST">
        <label for="brand">Phone Brand:</label>
        <input type="text" id="brand" name="brand" required><br><br>

        <label for="model">Phone Model:</label>
        <input type="text" id="model" name="model" required><br><br>

        <label for="price">Price (USD):</label>
        <input type="number" step="0.01" id="price" name="price" required><br><br>

        <input type="submit" value="Add Phone">
    </form>

    <hr>

    <!-- Form to update phone details -->
    <h2>Update Phone Details</h2>
    <form action="phones.php" method="POST">
        <label for="update_id">Phone ID to Update:</label>
        <input type="number" id="update_id" name="update_id" required><br><br>

        <label for="update_brand">New Brand:</label>
        <input type="text" id="update_brand" name="update_brand" required><br><br>

        <label for="update_model">New Model:</label>
        <input type="text" id="update_model" name="update_model" required><br><br>

        <label for="update_price">New Price (USD):</label>
        <input type="number" step="0.01" id="update_price" name="update_price" required><br><br>

        <input type="submit" value="Update Phone">
    </form>

    <hr>

    <!-- Form to delete a phone record -->
    <h2>Delete Phone</h2>
    <form action="phones.php" method="GET">
        <label for="delete_id">Phone ID to Delete:</label>
        <input type="number" id="delete_id" name="delete_id" required><br><br>
        <input type="submit" value="Delete Phone">
    </form>

    <hr>

    <!-- Section to display all phone details -->
    <h2>Phone List</h2>
    <?php
    // Fetch all phone details from the database to display
    $sql = "SELECT id, brand, model, price FROM PhoneDetails";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "<table border='1'>
                <tr>
                    <th>ID</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>";
        while($row = $result->fetch_assoc()) {
            echo "<tr>
                    <td>" . $row["id"]. "</td>
                    <td>" . $row["brand"]. "</td>
                    <td>" . $row["model"]. "</td>
                    <td>" . $row["price"]. "</td>
                    <td>
                        <a href='phones.php?delete_id=" . $row["id"] . "'>Delete</a> |
                        <a href='phones.php?update_id=" . $row["id"] . "'>Update</a>
                    </td>
                </tr>";
        }
        echo "</table>";
    } else {
        echo "<p>No results found</p>";
    }

    // Close the connection
    $conn->close();
    ?>

</body>
</html>
