<?php

    $noPedido = $_GET['noPedido'];
    settype($noPedido,"integer");
    
    $status = $_POST['status'];
   //var_dump("$status");
    
   
    

$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");


$sql= "UPDATE pedidos SET status='$status' WHERE noPedido='$noPedido'";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";
header('Location: mostrarpedidos.php');
   ?>