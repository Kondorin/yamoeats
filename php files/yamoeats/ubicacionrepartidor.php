<?php

    $noPedido = $_GET['noPedido'];
    $lastPosition = $_POST['lastPosition'];

    

$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");
//$con = mysqli_connect("localhost", "id11564924_administrador", "admin", "id11564924_yamoeats");

$sql= "UPDATE pedidos SET lastPosition = '$lastPosition' WHERE noPedido='$noPedido'";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";


   ?>
   