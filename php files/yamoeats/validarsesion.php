<?php
/*
$usuario = $_POST['usuario'];
$clave = $_POST['clave'];

if($usuario != "admin" or $clave != "admin"){
	
	$_SESSION['fallo_login'] = 'fallo inicio de sesion, usuario o contraseña incorrectos';
	header('Location: index.html?login=failed');



}else{
	session_start(); 
     $_SESSION['usuario'] = "admin"; 
     $_SESSION['estado'] = 'Autenticado';
	//redirect_to(url_for('/web/indexvalidado.php')); 
    header("Location: inicio.php");
   
	
}
*/


include 'conexion.php';
$usu_usuario=$_POST['usuario'];
$usu_password=$_POST['clave'];

//$usu_usuario="acm.96@hotmail.com";
//$usu_password="pajaritos";

$sentencia=$conexion->prepare("SELECT * FROM usuarios WHERE email=? AND contrasena=?");
$sentencia->bind_param('ss',$usu_usuario,$usu_password);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         if($fila['tipoUsuario']=="administrador"){
             header("Location: inicio.php");
         }else{
             header("Location: index.html?login=failed");
         }
}else{
     header("Location: index.html?login=failed");
}
$sentencia->close();
$conexion->close();


?>