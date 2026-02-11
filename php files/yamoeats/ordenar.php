<?php
    
    $usuario = $_POST['usuario'];
    $codigoPizza = $_POST['codigoPizza'];
    //settype($codigoPizza,"integer");
    strval($codigoPizza); 
    //$codigoPizza2 = intval('$codigopizza');
    $nombrePizza = $_POST['nombrePizza'];
    $fechaCompra = $_POST['fechaCompra'];
    $status = $_POST['status'];
    $direccion = $_POST['direccion'];
    $celular = $_POST['celular'];
    $size = $_POST['size'];
    $cantidad = $_POST['cantidad'];
    $total = $_POST['total'];
   
    

$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");
//$con = mysqli_connect("localhost", "id11564924_administrador", "admin", "id11564924_yamoeats");

$sql= "INSERT INTO pedidos(usuario,direccion,celular,codigoPizza,nombrePizza,size,cantidad,total,fechaCompra,status) VALUES ('$usuario','$direccion','$celular','$codigoPizza','$nombrePizza','$size','$cantidad','$total','$fechaCompra','$status')";

//$sql= "INSERT INTO pedidos2(usuario,direccion,celular,codigoPizza,nombrePizza,fechaCompra,status) VALUES ('$usuario','$direccion','$celular','$codigoPizza','$nombrePizza','$fechaCompra','$status')";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);

$echo="los datos se insertaron";



   ?>