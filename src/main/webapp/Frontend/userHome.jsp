<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>tecVegetal</title>
    </head>
    <body>

        <nav class="navbar navbar-dark bg-dark">
            <a style="color:white" class="navbar-toggler"><span class="navbar-toggler-icon"> </span> Home</a>

            <div class="dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" style="color:white" >Cerrar Sesión</a>


                <div class="dropdown-menu text-center">
                    <a> <img src="imgs/user.png" height="80" width="80"></a><br>
                    <a>User</a>
                    <a>example@gmail.com</a>
                    <div class="dropdown-divider"></div>
                    <a href="index?accion=back" class="dropdown-item">Salir</a>
                </div>
        </nav>

        <div class="4" style="padding: 40px; background-color: lightgray; height: 100%;">
            <h1>Sistema de solicitud para apertura de clubes</h1>
           
            <div class="table_container" style="margin-bottom: 50px; margin-top: 30px; border-bottom: 1px solid black">

                <p><strong>Sugerencias de clubes por otros estudiantes</strong></p>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nombre del club</th>
                            <th scope="col">Categoría</th>
                            <th scope="col">Cantidad de solicitudes</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="club" items="${clubes}">
                            <tr>
                                <td ><c:out value="${club.getGroupName()}" default="---"></c:out></td>
                                <td><c:out value="${club.getGroupCategory()}" default="---"></c:out></td>
                                <td><c:out value="${club.getGroupSelected()}"></c:out></td>
                                    <td class="apoyar">
                                        <a href="StudentController?accion=apoyarSolicitud&club=${club.getGroupName()}&categoria=${club.getGroupCategory()}&selected=${club.getGroupSelected()}&nombreEstudiante=${estudiante.getName()}&username=${estudiante.getUsername()}" 
                                           class="apoyar">Apoyar solicitud</a>
                                    </td>
                                </tr>
                        </c:forEach> 
                                
                    </tbody>
                </table>
            </div>

            <div class="newClub_container" style="display: flex; border-rigth: 1px solid black">
                <div class="contenedor" style=" width: 45%; padding: 20px; margin-right: 100px">
                    <p><strong>Hacer una nueva sugerencia</strong></p>
                    <form action="StudentController?accion=registrarClub" method="post"> 
                        
                        <div class="form-group">
                            <label>Nombre completo: </label>
                            <input id="nombreUsuario" class="form-control" type="text" name="nombreUsuario" 
                                   class="form-control" value="${estudiante.getName()}" readonly>
                        </div>
                        
                        <div class="form-group">
                            <label>Nombre de usuario: </label>
                            <input id="username" class="form-control" type="text" name="username" 
                                   class="form-control" value="${estudiante.getUsername()}" readonly>
                        </div>

                        <div class="form-group"> 
                            <label>Nombre del club: </label>
                            <input id="nombreClub" class="form-control" type="text" name="nombreClub"  >
                        </div>

                        <div class="form-group">
                            <label>Categoría: </label>
                            <input id="categoria" class="form-control" type="text" name="categoria" class="form-control" >
                        </div>
                        <button type="submit" id="register" name="register">Registrar</button>          
                    </form>    

                </div>
                        
                <div class="table_container" style="margin-bottom: 20px; width: 45%">

                    <p><strong>Solicitudes realizadas</strong></p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nombre del club</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="club" items="${clubesEstudiante}">
                                <tr>
                                    <td ><c:out value="${club}" default="---"></c:out></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
