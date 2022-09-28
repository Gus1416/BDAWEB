<!DOCTYPE html>

<html>
    
        <title>tecVegetal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link href="css\estilos.css" rel="stylesheet" type="text/css"/>
    

        <body >
            <div style="background-color: lightgray; height: 100vh">

                <div class="contenedor" style=" width: 50%; padding: 20px">

                    <form action="index?accion=Login" method="post"> 

                        <div class="form-group">
                            <img src="imgs/logoTecDigital.png" alt="" lenght="200" width="200"/>
                            <p><strong>Bienvenido al sistema de Login</strong></p>
                        </div>

                        <div class="form-group"> 
                            <label>Nombre de usuario: </label>
                            <input id="nombre" class="form-control" type="text" name="nombre" placeholder="Ingrese nombre" >
                        </div>

                        <div class="form-group">
                            <label>Contraseña: </label>
                            <input id="password" class="form-control" type="password" name="password" placeholder="Ingrese contraseña" class="form-control" >
                        </div>

                        <div class="form-group">
                            <label>Tipo de usuario: </label>
                            <select id="role" name="role" class="form-control">
                                <option value="Student">Estudiante</option>
                                <option value="Administrator">Administrador</option>
                            </select>
                        </div>

                        <div class="index_buttons" style="font-size: 17px" >
                            <button type="submit" id="values" name="values">Ingresar</button>          
                            <a href="StudentController?accion=" style="margin-left: 440px">Registrar nueva cuenta</a>
                        </div>
                        
                    </form>    

                </div>
            </div>


            
            
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
