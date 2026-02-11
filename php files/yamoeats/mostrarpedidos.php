<?php 
$hostname='localhost';
$database='id11754299_yamoeats';
$username='id11754299_admin';
$password='admin';

$conexion=new mysqli($hostname,$username,$password,$database);
$sql = "SELECT * FROM pedidos";
//$sql .= "ORDER BY nombre ASC";
$result = mysqli_query($conexion, $sql);
//var_dump($result);
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
  <a href="inicio.php">INICIO</a>
  <a href="mostrarpedidos.php">PEDIDOS</a>
  <a href="mostrarpizzas.php">PIZZAS</a>
  <a href="mostrarusuarios.php">USUARIOS</a>
  
	</div>
	<div class="navbarleft">
            <form action="index.html" method="post" >
    
    <input type="submit" value="Cerrar sesion">
  </form>
        </div>

</head>

<body>


  </br>
<b><font size="5"><p style="text-align:center; margin-top:0; margin-bottom:0">Pedidos</p></font></b>
      <table border="1" width="100%" align="left" > 
 <tr bgcolor="#E9EAEA"> 
  <th>Pedido no.</th>
  <th>Usuario</th>
  <th>Direccion</th>
  <th>Celular</th>
    <th>Codigo Pizza</th> 
    <th>Pizza</th>
  <th>Fecha Compra</th>
  <th>Status</th>
  <th bgcolor='orange'>&nbsp;</th>
        <th bgcolor='orange'>&nbsp;</th>
  </tr>
    <!-- Inicio tabla 2(noPedido) dentro de tabla1-->


 <?php while($row = mysqli_fetch_array($result)) {  ?>
 <tr>
      <td align='center'><?php $noPedido = $row['noPedido']; echo $row['noPedido']; ?></td>  
      <td><?php $usuario = $row['usuario']; echo $row['usuario']; ?> </td>
      <td><?php $direccion = $row['direccion']; echo $row['direccion']; ?> </td>
      <td><?php $celular = $row['celular']; echo $row['celular']; ?> </td>
      <td><?php $codigoPizza = $row['codigoPizza']; echo $row['codigoPizza']; ?> </td>
      <td><?php $nombrePizza = $row['nombrePizza']; echo $row['nombrePizza']; ?> </td>
      <td align='center'><?php $fechaCompra = $row['fechaCompra']; echo $row['fechaCompra']; ?> </td>
      <td align='center'><?php $status = $row['status']; echo $row['status']; ?> </td>
      
      <td align='center'><form action=editarpedido.php?noPedido=<?php echo"$noPedido" ?> method="post">
 <input type="hidden" name="status" value=<?php echo"$status" ?> />
 <input type="submit" value="editar"/>
</form></td>

      <td align='center'><?php if($status=='finalizada' or $status=='cancelada'){ ?><form action=<?php echo"eliminarpedido.php?noPedido=$noPedido" ?> method="post">
 <input type="hidden" name="status" value="" />
 <input type="submit" value="borrar"/>
 
</form><?php }else{ echo""; } ?></td>
      
  </tr>
    <?php } ?> 
  <!-- Fin tabla 2(noPedido) dentro de tabla1-->
</table>

  
  </br>

  





</body>

</html>