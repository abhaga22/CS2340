<?php
    $con=mysqli_connect("cs2430.000webhostapp.com","id804804_kamajjb","kama1122","id804804_2430");
    //"localhost","my_user","my_password","my_db"
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "INSERT INTO User (name, age, username, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $age, $username, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;
    mysqli_stmt_close($statement);

    echo json_encode(@response);
?>