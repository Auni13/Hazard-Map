<?php

$con=new mysqli('localhost', 'root', "",'test_db');

if(!$con){
 
    die(mysqli_error($con));
}

?>