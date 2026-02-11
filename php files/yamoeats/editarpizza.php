<?php 

$ingredientes2 = $_GET['ingredientes'];
$ingredientes = str_replace("%"," ","$ingredientes2");
$size = $_POST['size'];
$precio = $_POST['precio'];
$codigo = $_GET['codigo'];
//var_dump($noPedido);




?>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">

  <title>Maestro</title>
  <b><font size="10"><p style="text-align:center; margin-top:0; margin-bottom:0">PROYECTO TAP</p></font></b>
  <link rel="stylesheet" type="text/css" href="style.css">
	<div class="navbar">
  <a href="index.html">INICIO</a>
  <a href="mostrarpedidos.php">PEDIDOS</a>
  <a href="mostrarpizzas.php">PIZZAS</a>
  <a href="mostrarusuarios.php">USUARIOS</a>
  
	</div>

</head>

<body>


  </br>
<b><font size="5"><p style="text-align:center; margin-top:0; margin-bottom:0">Editar pedido</p></font></b>
    
  </br>
  
  

  <div id="content">

  

  <div class="subject new">
    <h1>Pedido</h1>

    <form action=editarpizzaquery.php?codigo=<?php echo"$codigo" ?> method="post" enctype="multipart/form-data">
      
        
      <dl><dt>Ingredientes</dt> <dd><input type="text" name="ingredientes" value="<?php echo"$ingredientes" ?>" ></dd></dl>
        
       
      
      <dl><dt>Tamaño</dt><dd><input type="text" name="size" value=<?php echo"$size" ?> ></dd> </dl>
      <dl><dt>Precio</dt><dd><input type="text" name="precio" value=<?php echo"$precio" ?> ></dd></dl>

      <div id="operations">
        <input type="submit" value="Editar" />
      </div>
    </form>

  </div>

</div>





</body>

</html>