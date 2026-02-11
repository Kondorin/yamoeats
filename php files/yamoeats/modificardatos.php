<?php

    $celular = $_POST['celular'];
    $direccion = $_POST['direccion'];
    $email = $_POST['email'];
   
if($celular==""){
    
    $sql = "UPDATE usuarios SET direccion='$direccion' WHERE email='$email'";
    
}else if($direccion==""){
    
    $sql = "UPDATE usuarios SET celular='$celular' WHERE email='$email'";
}

$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");
//$con = mysqli_connect("localhost", "id11564924_administrador", "admin", "id11564924_yamoeats");


mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";
header('Location: mostrarusuarios.php');


   ?>