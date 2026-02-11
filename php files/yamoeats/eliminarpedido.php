<?php 

$noPedido = $_GET['noPedido'];
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

    <form action=<?php echo"eliminarpedidoquery.php?noPedido=$noPedido" ?> method="post" enctype="multipart/form-data">
      <dl>
        <dt>¿Seguro que desea borrar ese registro?</dt>
        <input type="submit" value="SI" />
        
      </dl>

    </form>
    
     <form action="mostrarpedidos.php" method="post" enctype="multipart/form-data">
      <dl>
        <input type="submit" value="NO" />
        
      </dl>

    </form>

  </div>

</div>





</body>

</html>