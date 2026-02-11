<?php 

$celular = $_POST['celular'];
$direccion2 = $_GET['direccion'];
$direccion = str_replace("%"," ","$direccion2");
$id = $_GET['id'];
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
<b><font size="5"><p style="text-align:center; margin-top:0; margin-bottom:0">Editar Usuario</p></font></b>
    
  </br>
  
  

  <div id="content">

  

  <div class="subject new">
    <h1>Usuario</h1>

    <form action=editarusuarioquery.php?id=<?php echo"$id" ?> method="post" enctype="multipart/form-data">
      
        
      <dl><dt>Celular</dt> <dd><input type="text" name="celular" value=<?php echo"$celular" ?> ></dd></dl>
      
      <dl><dt>Direccion</dt><dd><input type="text" name="direccion" value="<?php echo"$direccion" ?>" ></dd> </dl>
    

      <div id="operations">
        <input type="submit" value="Editar" />
      </div>
    </form>

  </div>

</div>





</body>

</html>