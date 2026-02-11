<?php

    
    $noPedido = $_POST['noPedido'];
    settype($noPedido,"integer");
    //$noPedido2 = intval('$noPedido');
   
    

$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");
//$con = mysqli_connect("localhost", "id11564924_administrador", "admin", "id11564924_yamoeats");

$sql= "UPDATE pedidos SET status='finalizada' WHERE noPedido='$noPedido'";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";


   ?>