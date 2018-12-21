<?php 

/*define('hostname', 'localhost');
define('user', 'root');
define('password', '');
define('databaseName', 'my_wallet');

$connect = mysqli_connect(hostname,user,password,databaseName);*/
$connect = mysqli_connect("localhost","root","","push_notification") 
			or die("cannot connected");
// mysql_select_db("database-name", "connection-link-identifier")
//mysqli_select_db("my_wallet",$connect);
?>