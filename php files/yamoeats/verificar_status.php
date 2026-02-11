<?php

include 'conexion.php';
$email = $_GET['email'];

$consulta = "select * from pedidos where (status = 'pendiente') and (usuario = '$email')";
$resultado = $conexion -> query($consulta);

    while($fila=$resultado -> fetch_array()){
        $producto[] = array_map('utf8_encode', $fila);
    }



echo json_encode($producto);

$resultado -> close();

?>