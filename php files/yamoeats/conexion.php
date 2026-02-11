<?php
$hostname='localhost';
$database='id11754299_yamoeats';
$username='id11754299_admin';
$password='admin';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "El sitio web está experimentado problemas";
}
?>