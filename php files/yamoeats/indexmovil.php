<?php

/**    $noCuenta = $_POST['nombre'];
    $noCuenta2 = intval($noCuenta);
    $nombre = $_POST['genero'];
    $apellido = $_POST['idioma'];

$conexion = mysqli_connect("localhost", "id11564924_admin", "administrador") or die ("error en la conexion");
mysqli_select_db($conexion,"id11564924_pedidos") or die ("error en la base de datos");
$sql= "INSERT INTO usuarios(noCuenta,nombre,apellido) VALUES ('$noCuenta2','$nombre','$apellido')";
mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";
**/


 //Recibimos el parametro des de Android
 $parametro = $_REQUEST['algo'];
 

 //En este caso simplemente imprimimos una respuesta
 //Aqui podríais introducir/obetener datos de SQL
 //O cualquier otra cosa 

 //imprimimos el mensaje de que ha llegado correctamente
 //añadiendo el parametro 'a' también
 echo "SERVER: ok, parametro recibido -> " . $parametro;

   ?>
   
   
  