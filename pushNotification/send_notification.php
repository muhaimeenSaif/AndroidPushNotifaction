<?php

require"config.php";

$token = $_POST['token'];
//$token = "eWaMXZhI7GI:APA91bFzfZsDamrnHon9zCwcysqOrNZwOoIBE85eIxte67f4jK1I9l9ther7vfznEik9Sj6F8ndjTIlO2nuU-stIJycdm8WKhL3FuyKlPB_sejWzuRDyVVHAlETsziQojF8Vbr31JwVl";
$title = "House Rent";

$messagee = "Please pay your due monthly rent as soon as possible";

$path = 'https://fcm.googleapis.com/fcm/send';
$server_key = "AAAAUOxV0j8:APA91bGspcBaB_ehzNjneKPc_uZO3iDrk-fxsMXdZiY9gd6LMlwl7d83p5VNLglja0tPpuK1uK_8kGMjfnLwyABE4QxRpXdYGAQf2qSt3rZpjd7YTPazeKjEnPtYS0F3B30ODjogdeZr";
//$sql = "UPDATE `flatinfo` SET `status`= 1 WHERE `status` LIKE 0;";
//$result = mysqli_query($connect,$sql);
//$row = mysqli_fetch_row($result);
$key = $token;
$temp_array = array( );
$response = array();
$code = "Yeah";
array_push($temp_array,array("flatNo"=>$token));
				

$header = array(
				'Authorization:key='.$server_key,
				'Content-Type:application/json'
				);
$field = array('to'=>$key,
				'notification'=>array('title'=>$title,'body'=>$messagee)
				);
$playLoad = json_encode($field);

$curl_session = curl_init();
curl_setopt($curl_session, CURLOPT_URL, $path);
curl_setopt($curl_session, CURLOPT_POST, true);
curl_setopt($curl_session, CURLOPT_HTTPHEADER, $header);
curl_setopt($curl_session, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($curl_session, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
curl_setopt($curl_session, CURLOPT_POSTFIELDS, $playLoad);

$result = curl_exec($curl_session);
curl_close($curl_session);
array_push($response,array("code"=>$code,"message"=>$temp_array));
echo json_encode($response);
mysqli_close($connect);



?>