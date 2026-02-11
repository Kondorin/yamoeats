<?php

include 'conexion.php';
$noPedido = $_GET['noPedido'];
settype($noPedido,"integer");

$consulta = "select * from pedidos where noPedido = '$noPedido'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();

?>