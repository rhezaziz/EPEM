<?php
//Import File Koneksi Database
	require_once('db_config.php');
	
	//Membuat SQL Query
	if ($_SERVER ['REQUEST_METHOD'] == 'GET') {
		# code...
	
	$sql = "SELECT * FROM manufacturers ORDER BY id ASC";
	
	
	//Mendapatkan Hasil
	$res = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($res)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			'id'=>$row[0],
			'name'=>$row[1]
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array("value" => 1 , 'result'=>$result));
	
	mysqli_close($con);


}
?>