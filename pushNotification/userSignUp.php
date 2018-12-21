<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
		require 'config.php';
	
		createUser();
		
	}
	
	function createUser(){
		global $connect;
		// $code = "success";
		// 		$message = "Thank you for signing up. Now please login ";
		// 		array_push($response,array("code"=>$code,"message"=>$message));
		// 		echo json_encode($response);
			
			$firstName = $_POST["firstName"];
			$lastName = $_POST["lastName"];
			$email = $_POST["email"];
			$token = $_POST["token"];
			$query = "Select * from users where email like '".$email."' AND firstName like '".$firstName."' ;";
			$result2 = mysqli_query($connect, $query);
			$response = array();
			// if(mysqli_num_rows($result1)>0)
			// {
			// 	$code = "error";
			// 	$message = "User already exist.....";
			// 	array_push($response,array("code"=>$code,"message"=>$message));
			// 	echo json_encode($response);
			// }
			// else
			// {
				$query = "INSERT INTO users(`firstName`, `lastName`, `email`,`token`) VALUES('$firstName','$lastName','$email','$token')";
				$result =mysqli_query($connect, $query);
				//echo print_r($result);
				$code = "success";
				$message = "Thank you for signing up. Now please login ";
				array_push($response,array("code"=>$code,"message"=>$message));
				echo json_encode($response);
				
			//}
			mysqli_close($connect);
	}
?>