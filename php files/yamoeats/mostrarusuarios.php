<?php 
$hostname='localhost';
$database='id11754299_yamoeats';
$username='id11754299_admin';
$password='admin';

$conexion=new mysqli($hostname,$username,$password,$database);
$sql = "SELECT * FROM usuarios";
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
    
    <form align="center" action="crearusuario.php" method="post">
 
 <input type="image" src="images/newuser.PNG" height="50" width="150"/>
 
 
</form>


  </br>
<b><font size="5"><p style="text-align:center; margin-top:0; margin-bottom:0">Usuarios</p></font></b>
      <table border="1" width="100%" align="left" > 
 <tr bgcolor="#E9EAEA"> 
  <th>Id</th>
  <th>Nombre</th>
    <th>Apellido</th> 
    <th>Email</th>
  <th>Contraseña</th>
  <th>Celular</th>
  <th>Direccion</th>
  <th>Tipo</th>
  <th bgcolor='orange'>&nbsp;</th>
        
        
  </tr>
    <!-- Inicio tabla 2(noPedido) dentro de tabla1-->


 <?php while($row = mysqli_fetch_array($result)) { ?>
 <tr>
      <td align='center'><?php $id = $row['id']; echo $row['id']; ?></td>  
      <td><?php $nombre = $row['nombre']; echo $row['nombre']; ?> </td>
      <td><?php $apellido = $row['apellido']; echo $row['apellido']; ?> </td>
      <td><?php $email = $row['email']; echo $row['email']; ?> </td>
      <td align='center'><?php $contrasena = $row['contrasena']; echo $row['contrasena']; ?> </td>
      <td align='center'><?php $celular = $row['celular']; echo $row['celular']; ?> </td>
      <td align='center'><?php $direccion = $row['direccion']; echo $row['direccion']; ?> </td>
      <td align='center'><?php $tipoUsuario = $row['tipoUsuario']; echo $row['tipoUsuario']; ?> </td>
      <?php $direccion2 =str_replace(' ', '%', $direccion);  ?>
      <td align='center'><form action=<?php echo"editarusuario.php?id=$id&direccion=". $direccion2 ?> method="post">
          
 <input type="hidden" name="celular" value=<?php echo"$celular" ?> />
  <input type="hidden" name="direccion" value= />
 <input type="submit" value="editar"/>
 
 
</form> </td>
      
      
  </tr>
    <?php } ?> 
  <!-- Fin tabla 2(noPedido) dentro de tabla1-->
</table>

  
  </br>

  





</body>

</html>