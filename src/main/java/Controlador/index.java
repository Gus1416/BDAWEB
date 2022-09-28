package Controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Clases.Operaciones;
import Modelo.Clases.groups;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebcor
 */
@WebServlet(urlPatterns = {"/index"})
public class index extends HttpServlet {

	Operaciones dao;

	public index() throws MalformedURLException {
		this.dao = new Operaciones();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion;
		boolean logger;
		RequestDispatcher dispatcher = null;
		accion = request.getParameter("accion");

		String name;
		String password;
		String role;

		String query1;
		String query2;
		String query3;
		String query4;

		if (accion == null || accion.isEmpty()) {
			dispatcher = request.getRequestDispatcher("Frontend/index.jsp");
			dispatcher.forward(request, response);

		} else if (accion.equals("Login")) {
			name = request.getParameter("nombre");
			password = request.getParameter("password");
			role = request.getParameter("role");

			//Acá le paso los parámetros obtenidos al método del DAO en la clase operaciones
			logger = dao.Login(name, password, role);

			if (logger == true && role.equals("Student")) {
				request.setAttribute("estudiante", dao.getUser(name));
				request.setAttribute("clubesEstudiante", dao.RegisteredStudent(dao.getUser(name).getName()));
				request.setAttribute("clubes", dao.AllGroups());

				request.getRequestDispatcher("Frontend/userHome.jsp").forward(request, response);

			} else if (logger == true && role.equals("Administrator")) {
				request.getRequestDispatcher("Frontend/adminHome.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("Frontend/index.jsp").forward(request, response);
			}

		} else if (accion.equals("back")) {
			dispatcher = request.getRequestDispatcher("Frontend/index.jsp");
			dispatcher.forward(request, response);

		} else if (accion.equals("Total")) {
			query1 = dao.ClassPerCategory();
			request.setAttribute("Q1", query1);
			dispatcher = request.getRequestDispatcher("Frontend/Consultas/Total.jsp");
			dispatcher.forward(request, response);

		} else if (accion.equals("Three")) {
			query2 = dao.TopStudents();
			request.setAttribute("Q2", query2);
			dispatcher = request.getRequestDispatcher("Frontend/Consultas/Three.jsp");
			dispatcher.forward(request, response);
			
		} else if (accion.equals("Top")) {
			query3 = dao.TopClubs();
			request.setAttribute("Q3", query3);
			dispatcher = request.getRequestDispatcher("Frontend/Consultas/Top.jsp");
			dispatcher.forward(request, response);

		} else if (accion.equals("Bottom")) {
			query4 = dao.BottomClubs();
			request.setAttribute("Q4", query4);
			dispatcher = request.getRequestDispatcher("Frontend/Consultas/Bottom.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
