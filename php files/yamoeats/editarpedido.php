<?php 

$hostname='localhost';
$database='id11754299_yamoeats';
$username='id11754299_admin';
$password='admin';

$status = $_POST['status'];
$noPedido = $_GET['noPedido'];
//var_dump($noPedido);


$conexion=new mysqli($hostname,$username,$password,$database);
$sql = "SELECT * FROM pizzas";
$result = mysqli_query($conexion, $sql);
$noRegistros = mysqli_num_rows($result);


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

    <form action=editarpedidoquery.php?noPedido=<?php echo"$noPedido" ?> method="post" enctype="multipart/form-data">
      <dl>
        <dt>Status</dt>
        <dd><input type="text" name="status" value=<?php echo"$status" ?> /></dd>
      </dl>

      <div id="operations">
        <input type="submit" value="Editar" />
      </div>
    </form>

  </div>

</div>





</body>

</html>