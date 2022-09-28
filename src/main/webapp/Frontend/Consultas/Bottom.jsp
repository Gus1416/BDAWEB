<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>tecVegetal</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-dark">
            
            <div class="dropdown">
                <a href="#" style="color:white" class="nav-link dropdown-toggle" data-toggle="dropdown">  <span class="navbar-toggler-icon">    </span>Módulo de Consultas</a>
                <div class="dropdown-menu text-left">
                    <div class="dropdown-divider"></div>
                    <a href="index?accion=Total" class="dropdown-item">Cantidad total de clubes distintos sugeridos por los estudiantes, según la categoría</a>
                    <div class="dropdown-divider"></div>
                     <a href="index?accion=Three" class="dropdown-item">Nombre y cantidad de clubes sugeridos para los 3 estudiantes que más sugerencias hayan realizado</a>
                    <div class="dropdown-divider"></div>
                     <a href="index?accion=Top" class="dropdown-item">Top 5 de clubes sugeridos</a>
                    <div class="dropdown-divider"></div>
                     <a href="index?accion=Bottom" class="dropdown-item">Bottom 3 de clubes sugeridos</a>
                    <div class="dropdown-divider"></div>
                </div>
            </div>

            
            <div class="dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" style="color:white" >Cerrar Sesión</a>
                <div class="dropdown-menu text-center">
                    <a> <img src="imgs/admin.png" height="80" width="80"></a><br>
                    <a>Admin</a>
                    <a>example@gmail.com</a>
                    <div class="dropdown-divider"></div>
                    <a href="index?accion=back" class="dropdown-item">Salir</a>
            </div>
            
        </nav>
        
        <div class="container mt-1">
            <table> 
              <tr>  
                <th>Bottom 3 de clubes sugeridos</th>
              </tr>
              
              <tr>
                 <td style="color: black" vertical-align: "left"> <%= request.getAttribute("Q4") %>  </td> 
              </tr>
                    
            </table>
            
        </div>
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
