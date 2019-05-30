<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		// $name = $_POST['name'];
		// $id = $_POST['id'];
		
		// //Pembuatan Syntax SQL
		// $sql = "INSERT INTO manufacturers (id,name) VALUES ('$id','$name')";
		
		// //Import File Koneksi database
		// require_once('db_config.php');
		
		// //Eksekusi Query database
		// if(mysqli_query($con,$sql)){
		// 	echo 'Berhasil Menambahkan kategori';
		// }else{
		// 	echo 'Gagal Menambahkan kategori';
		// }
		
		// mysqli_close($con);


	$response = array();
	//mendapatkan data
	$id = $_POST['id'];
	$name = $_POST['name'];

	require_once('db_config.php');
	//cek id sudah terdaftar atau belum

	$sql = "SELECT * FROM manufacturers WHERE id = '$id' ";
	$check = mysqli_fetch_array(mysqli_query($con , $sql));


	if (isset($check)) {
		$response["value"] = 0 ;
		$response["message"] = "ops id sudah terdaftar";

		echo json_encode($response);
	}else{
	$sql = "INSERT INTO manufacturers (id , name) VALUES ('$id','$name')";

	if (mysqli_query($con , $sql)) {
		$response["value"] = 1 ;
		$response["message"] = "sukses mendaftar";

		echo json_encode($response);
	}else{
		$response["value"] = 0 ;
		$response["message"] = "ops! gagal mendaftar";

		echo json_encode($response);
	}
}
mysqli_close($con);
}else{
	$response["value"] = 0 ;
		$response["message"] = "opps! coba lagi";

		echo json_encode($response);
}

?>