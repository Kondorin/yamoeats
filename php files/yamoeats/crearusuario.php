<?php 

//var_dump($direccion);




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
<b><font size="5"><p style="text-align:center; margin-top:0; margin-bottom:0">Crear Usuario</p></font></b>
    
  </br>
  
  

  <div id="content">

  

  <div class="subject new">
    <h1>Creando usuario</h1>

    <form action=crearusuarioquery.php method="post" enctype="multipart/form-data">
      
        
      <dl><dt>Nombre</dt> <dd><input type="text" name="nombre" value="" ></dd></dl>
      <dl><dt>Apellido</dt><dd><input type="text" name="apellido" value="" ></dd> </dl>
      <dl><dt>Email</dt><dd><input type="text" name="email" value="" ></dd> </dl>
      <dl><dt>Contraseña</dt><dd><input type="password" name="contrasena" value="" ></dd> </dl>
      <dl><dt>Celular</dt><dd><input type="text" name="celular" value="" ></dd> </dl>
      <dl><dt>Direccion</dt><dd><input type="text" name="direccion" value="" ></dd> </dl>
      <dl><dt>Tipo de usuario</dt><dd><input type="text" name="tipoUsuario" value="" ></dd> </dl>
    

      <div id="operations">
        <input type="submit" value="Crear" />
      </div>
    </form>

  </div>

</div>





</body>

</html>