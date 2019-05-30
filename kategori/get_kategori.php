<?php
$response =  array();

 require_once('db_connect.php');

$db = new CONNECT;

$result = MySQL_query ("SELECT * FROM manufacturers");

if(MySQL_num_rows($result) > 0){
	$response["manufacturers"] = array();
	
	while ($row = MySQL_fetch_array($result)){

		$Manufacturers = array();
		$Manufacturers ["id"] = $row["id"];
		$Manufacturers ["name"] = $row["name"];

		array_push ($response["manufacturers"] , $Manufacturers);
}
		//succes 
		$response["succes"] = 1;

		echo json_encode ($response);
	}else{
	 //no found data
		$response["succes"] = 0;
		$response["message "] = "Data tidak ditemukan";

		echo json_encode($response);
	}


?>