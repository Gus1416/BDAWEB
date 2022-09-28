package Controlador;

import Modelo.Clases.Operaciones;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {
	Operaciones dao;
	
	public StudentController() throws MalformedURLException{
		this.dao = new Operaciones();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		String accion;
		RequestDispatcher dispatcher = null;
		accion = request.getParameter("accion");
		
		if (accion == null || accion.isEmpty()){
			dispatcher = request.getRequestDispatcher("Frontend/createAccount.jsp");
			dispatcher.forward(request, response);
			
		} else if (accion.equals("registrarUsuario")){
			String nombre = request.getParameter("nombre");
			String seccion = request.getParameter("seccion");
			String nombreUsuario = request.getParameter("nombreUsuario");
			String password = request.getParameter("password");
			String role = request.getParameter("usuarios");
			dao.RegisterNewUser(nombre, seccion, nombreUsuario, password, role);
			dispatcher = request.getRequestDispatcher("Frontend/index.jsp");
			dispatcher.forward(request, response);

		} else if (accion.equals("apoyarSolicitud")) {
			String nombreClub = request.getParameter("club");
			String categoria = request.getParameter("categoria");
			String selected = request.getParameter("selected");
			String nombreUsuario = request.getParameter("nombreEstudiante");
			String username = request.getParameter("username");
			dao.RegistrerClass(nombreClub, categoria, 1);
			dao.StudentClassRelationship(nombreUsuario, nombreClub);
			request.setAttribute("clubes", dao.AllGroups());
			request.setAttribute("clubesEstudiante", dao.RegisteredStudent(dao.getUser(username).getName()));
			request.setAttribute("estudiante", dao.getUser(username));
			dispatcher = request.getRequestDispatcher("Frontend/userHome.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		String accion;
		RequestDispatcher dispatcher = null;
		accion = request.getParameter("accion");
		
		if (accion.equals("registrarUsuario")){
			String nombre = request.getParameter("nombre");
			String seccion = request.getParameter("seccion");
			String nombreUsuario = request.getParameter("nombreUsuario");
			String password = request.getParameter("password");
			String role = request.getParameter("usuarios");
			dao.RegisterNewUser(nombre, seccion, nombreUsuario, password, role);
			dispatcher = request.getRequestDispatcher("Frontend/index.jsp");
			dispatcher.forward(request, response);
			
		} else if (accion.equals("registrarClub")){
			String nombreClub = request.getParameter("nombreClub");
			String categoria = request.getParameter("categoria");
			String nombreUsuario = request.getParameter("nombreUsuario");
			String username = request.getParameter("username");
			dao.RegistrerClass(nombreClub, categoria, 1);
			dao.StudentClassRelationship(nombreUsuario, nombreClub);
			request.setAttribute("clubes", dao.AllGroups());
			request.setAttribute("clubesEstudiante", dao.RegisteredStudent(dao.getUser(username).getName()));
			request.setAttribute("estudiante", dao.getUser(username));
			dispatcher = request.getRequestDispatcher("Frontend/userHome.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}