<?php

    //$celular = $_POST['celular'];
    $nombre = $_POST['nombre'];
    $apellido = $_POST['apellido'];
    $contrasena = $_POST['contrasena'];
    $email = $_POST['email'];
    $celular = $_POST['celular'];
    $direccion = $_POST['direccion'];
    

$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");
//$con = mysqli_connect("localhost", "id11564924_administrador", "admin", "id11564924_yamoeats");

$sql= "INSERT INTO usuarios(nombre,apellido,contrasena,email,celular,direccion,tipoUsuario) VALUES ('$nombre','$apellido','$contrasena','$email','$celular','$direccion','cliente')";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";


   ?>
   
   
  