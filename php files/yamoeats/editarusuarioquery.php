<?php

    $id = $_GET['id'];
    settype($id,"integer");
    $celular = $_POST['celular'];
    $direccion = $_POST['direccion'];
  
$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");


$sql= "UPDATE usuarios SET celular='$celular',direccion='$direccion' where id='$id'";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";
header('Location: mostrarusuarios.php');
   ?>