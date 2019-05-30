<?php
$response = array();

if(isset ($_POST['id']) && isset ($_POST['name'])){
	$id = $_POST['id'];
	$name = $_POST['name'];

	require_once('db_connect.php');

	$db = new CONNECT();

	$result = mysql_query("INSERT INTO manufacturers(id , name) VALUES ('$id' , '$name')");

	if ($result) {
		# code..
		$response["succes"] = 1;
		$response["message "] = "berhasil coy";
		echo json_encode($response);
	}else{
		$response["succes"] = 0;
		$response["message"] = "gagal uy";
		echo json_encode($response);
	}
}



?>