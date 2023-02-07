<?php
include 'connect.php';
if(isset($_POST['submit'])){
    $hz_id=$_POST ['hz_id'];
    $hz_location=$_POST	['hz_location'];
	$hz_state=$_POST	['hz_state'];
    $hz_repname=$_POST	['hz_repname'];
    $hz_type=$_POST		['hz_type'];
    $hz_desc=$_POST	['hz_desc'];
	$hz_time=$_POST	['hz_time'];
	$hz_date=$_POST	['hz_date'];
	$hz_lat=$_POST	['hz_lat'];
	$hz_lng=$_POST	['hz_lng'];
    

    $sql="insert into `information` (hz_id,hz_location,hz_state,hz_repname,hz_type,hz_desc,hz_time,hz_date,hz_lat,hz_lng) 
    values('$hz_id','$hz_location','$hz_state','$hz_repname','$hz_type','$hz_desc','$hz_time','$hz_date','$hz_lat','$hz_lng')";
    $result=mysqli_query($con,$sql);

    if($result){
        //echo "Data inserted successfully";
        header('location:display.php');
    }else{
        die(mysqli_error($con));
    }
}
?>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">

    <title>List Of Report</title>
  </head>
  <body>
    <div class="container my-5">
    <p></p>
    <p></p>
    <h4>ADD NEW REPORT</h4>
    <p></p>
    <p></p>
    <p></p>
    <p></p>
    <form method="post">
  <div class="form-group">
    <label>Location</label>
    <input type="text" class="form-control" placeholder="Enter location" name="hz_location" autocomplete="off">
</div> 
<div class="form-group">
    <label>State</label>
    <input type="text" class="form-control" placeholder="Enter state" name="hz_state" autocomplete="off">
</div> 
<div class="form-group">
    <label>Report's Name</label>
    <input type="text" class="form-control" placeholder="Enter report's name" name="hz_repname" autocomplete="off">
</div> 
<div class="form-group">
    <label>Type of Hazard</label>
    <input type="text" class="form-control" placeholder="Enter type of hazard" name="hz_type" autocomplete="off">
</div> 
<div class="form-group">
    <label>Description</label>
    <input type="text" class="form-control" placeholder="Enter description" name="hz_desc" autocomplete="off">
</div> 
<div class="form-group">
    <label>Time</label>
    <input type="time" class="form-control" placeholder="Enter time" name="hz_time" autocomplete="off">
</div> 
<div class="form-group">
    <label>Date</label>
    <input type="date" class="form-control" placeholder="Enter date" name="hz_date" autocomplete="off">
</div> 
<div class="form-group">
    <label>Latitude</label>
    <input type="decimal" class="form-control" placeholder="Enter latitude" name="hz_lat" autocomplete="off">
</div> 
<div class="form-group">
    <label>Longitude</label>
    <input type="decimal" class="form-control" placeholder="Enter longitude" name="hz_lng" autocomplete="off">
</div> 
  <button type="submit" class="btn btn-primary" name="submit">Submit</button>
</form>
    </div>
  </body>
</html>