<?php
include 'connect.php';
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Report</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
   
</head>
<body>
<header>
     
        <a class="cta" href="logout.php"><button>Logout</button></a>
    </header>
    <div class="container">
        <button class="btn btn-primary my-5"><a href="add_report.php" class="text-light">Add Participant</a></button>
        <table class="table">
  <thead class="thead-dark">
  <tr>
                    <th>No</th>
                    <th>LOCATION</th>
                    <th>STATE</th>
                    <th>REPORT NAME</th>
                    <th>TYPE OF HAZARD</th>
                    <th>DESCRIPTION</th>
					<th>TIME</th>
					<th>DATE</th>
					<th>LATITUDE</th>
					<th>LONGITUDE</th>
					
</tr>
  </thead>

  <tbody>
  <?php
   $sql="Select * From `information`";
   $result=mysqli_query($con,$sql);
   if($result){
    while($row=mysqli_fetch_assoc($result)){
        $hz_id=$row['hz_id'];
        $hz_location=$row['hz_location'];
        $hz_state=$row['hz_state'];
        $hz_repname=$row['hz_repname'];
        $hz_type=$row['hz_type'];
        $hz_desc=$row['hz_desc'];
        $hz_time=$row['hz_time'];
        $hz_date=$row['hz_date'];
        $hz_lat=$row['hz_lat'];
        $hz_lng=$row['hz_lng'];

        echo '<tr>
        <th scope="row">'.$hz_id.'</th>
        <td>'.$hz_location.'</td>
        <td>'.$hz_state.'</td>
        <td>'.$hz_repname.'</td>
        <td>'.$hz_type.'</td>
        <td>'.$hz_desc.'</td>
        <td>'.$hz_time.'</td>
        <td>'.$hz_date.'</td>
        <td>'.$hz_lat.'</td>
        <td>'.$hz_lng.'</td>
        
       
      </tr>';
    }
   }
  ?>
    
  </tbody>
</table>
</div>
</body>
</html>