<?php 
$hostname='localhost';
$database='id11754299_yamoeats';
$username='id11754299_admin';
$password='admin';

$conexion=new mysqli($hostname,$username,$password,$database);
$sql = "SELECT * FROM pizzas";
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
<b><font size="5"><p style="text-align:center; margin-top:0; margin-bottom:0">Pizzas</p></font></b>
      <table border="1" width="100%" align="left" > 
 <tr bgcolor="#E9EAEA"> 
  <th>Codigo</th>
  <th>Pizza</th>
    <th>Tamaño</th> 
    <th>Ingredientes</th>
  <th>Precio</th>
  <th bgcolor='orange'>&nbsp;</th>
        
    
  </tr>
    <!-- Inicio tabla 2(noPedido) dentro de tabla1-->


 <?php while($row = mysqli_fetch_array($result)) { ?>
 <tr>
      <td align='center'><?php $codigo = $row['codigo']; echo $row['codigo']; ?></td>  
      <td><?php $nombrePizza = $row['nombrePizza']; echo $row['nombrePizza']; ?> </td>
      <td align='center'><?php $size = $row['size']; echo $row['size']; ?> </td>
      <td><?php $ingredientes = $row['ingredientes']; echo $row['ingredientes']; ?> </td>
      <td align='center'><?php $precio = $row['precio']; echo $row['precio']; ?> </td>
      <?php $ingredientes2 =str_replace(' ', '%', $ingredientes); ?>
      
      <td align='center'><form action=<?php echo"editarpizza.php?codigo=$codigo&ingredientes=$ingredientes2" ?> method="post">
 <input type="hidden" name="size" value=<?php echo"$size" ?> />
 <input type="hidden" name="precio" value=<?php echo"$precio" ?> />
 <input type="submit" value="editar"/>
</form> </td>
      
      
  </tr>
    <?php } ?> 
  <!-- Fin tabla 2(noPedido) dentro de tabla1-->
</table>

  
  </br>

  





</body>

</html>