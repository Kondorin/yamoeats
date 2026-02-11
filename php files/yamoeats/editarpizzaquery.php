<?php

    $codigo = $_GET['codigo'];
    settype($codigo,"integer");
    $ingredientes = $_POST['ingredientes'];
    $size = $_POST['size'];
    $precio = $_POST['precio'];
  
$conexion = mysqli_connect("localhost", "id11754299_admin", "admin") or die ("error en la conexion");
mysqli_select_db($conexion,"id11754299_yamoeats") or die ("error en la base de datos");


$sql= "UPDATE pizzas SET ingredientes='$ingredientes',size='$size',precio='$precio' where codigo='$codigo'";

mysqli_query($conexion,$sql) or die ("Error de sql");
mysqli_close($conexion);
$echo="los datos se insertaron";
header('Location: mostrarpizzas.php');
   ?>