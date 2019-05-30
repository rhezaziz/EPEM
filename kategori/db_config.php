<?php
define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','ci_sample');
 
 //membuat koneksi dengan database
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 // 192.168.42.199

?>