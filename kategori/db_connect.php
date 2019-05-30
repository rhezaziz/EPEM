<?php
class CONNECT {
		function constructor(){
		$this->connect();

	}
	function destruct(){
		$this->close();
	}

	function connect(){
		require_once('db_config.php');
		//connect to mysql server 
		$con = MySQL_connect(HOST , USER , PASS) or die (MySQL_error());
		// pilih database 
		$db = MySQL_select_db(DB) or die (MySQL_error());
		//return connection cursor
		return $con;
	}
	function close(){
		//closing db connection
		MySQL_close();
	}

}
?>