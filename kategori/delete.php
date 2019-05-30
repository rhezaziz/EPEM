<?php

$response = array();

if(isset ($_POST['id'])){
	$id = $_POST['id'];

	require_once('db_connect.php');

	$db = new CONNECT();

	$result = mysql_query("DELETE FROM manufacturers WHERE id = $id");

	if (mysql_affected_rows() > 0 ) {
		$response["succes"] = 1;
		$response["message"] = "berhasil";
		# code...
		echo json_encode($response);
	}else{
		$response["succes"] = 0;
		$response["message"] = "gagal" ;

		echo json_encode($response);
	}
}

?>