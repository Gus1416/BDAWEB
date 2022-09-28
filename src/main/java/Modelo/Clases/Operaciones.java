package Modelo.Clases;

import Modelo.Clases.users;
import Modelo.Clases.groups;
import Modelo.Clases.StudentClass;
import Modelo.Connexion.Conexion;
import static Modelo.Connexion.Conexion.connection;

import java.net.MalformedURLException;
import java.util.*;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.ektorp.*;

public class Operaciones {

	private static Object limit(int i) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	String formated;

	CouchDbConnector conn = Conexion.connection();

	public Operaciones() throws MalformedURLException {
	}

	//Login
	public boolean Login(String pUsername, String pPassword, String pRole) {
		try {
			users Users = conn.get(users.class, pUsername);
			return Objects.equals(Users.password, pPassword) && Objects.equals(Users.role, pRole);
		} catch (DocumentNotFoundException e) {
			System.out.println("Igual presenté el error");
		}
		return false;
	}
	
	public users getUser(String pUsername){
		users User = conn.get(users.class, pUsername);
		return User;
	}
	
	public groups getClub(String pClubName){
		groups Club = conn.get(groups.class, pClubName);
		return Club;
	}

	//Registro de una nuevo usuario
	public boolean RegisterNewUser(String nombre, String group, String username, String password, String role) {
		try {
			users Users = new users(nombre, group, username, password, role);
			conn.create(Users.username, Users);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	//Almacena la relacion entre una clase y el estudiante
	public boolean StudentClassRelationship(String Student, String Class) {
		try {
			StudentClass studentClass = new StudentClass(Student, Class);
			conn.create(Student + Class, studentClass);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	//Registrar una clase
	public boolean RegistrerClass(String GroupName, String GroupCategory, int GroupSelected) {
		try {
			groups Groups = new groups(GroupName, GroupCategory, GroupSelected);
			if (AlreadyCreated(GroupName)) {
				groups ExistingGroup = conn.get(groups.class, GroupName);
				ExistingGroup.setGroupSelected(ExistingGroup.GroupSelected + 1);
				conn.update(ExistingGroup);
			} else {
				conn.create(Groups.GroupName, Groups);
			}
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	//Revisa si una clase ya fue creada
	public boolean AlreadyCreated(String GroupName) {
		return conn.contains(GroupName);
	}

	//Todos los grupos creados
	public List<groups> AllGroups() throws MalformedURLException {
		CouchDbConnector conn = Conexion.connection();
		ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("all-groups");
		List<groups> Groups = conn.queryView(query, groups.class);
		List<groups> validGroups = new ArrayList();
		for (groups test : Groups) {
			//System.out.println(test.toString());
			if (test.GroupName != null) {
				validGroups.add(test);
				//System.out.println(test.GroupName + " " + test.GroupCategory + " ");
			}
		}
		System.out.println(validGroups);
		return validGroups;
	}

	public List<String> RegisteredStudent(String pStudentName) throws MalformedURLException {
		CouchDbConnector conn = Conexion.connection();
		ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("registered-student");
		List<StudentClass> studentClass = conn.queryView(query, StudentClass.class);
		List<String> filteredClasses = new ArrayList<>();
		for (StudentClass result : studentClass) {
			System.out.println(result);
			if (Objects.equals(result.Student, pStudentName)) {
				filteredClasses.add(result.Class);
			}
		}
		System.out.println("Es este: " + filteredClasses);
		return filteredClasses;
	}

//	public static void main(String[] args){
//		
//		try {
//			AllGroups();
//		} catch (MalformedURLException ex) {
//			Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}

// ------------------------------------------------------------------------------    
//    //PRUEBA- ELIMINAR
//    public static void StudentNames() throws MalformedURLException {
//        CouchDbConnector conn = connection.connection();
//        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("Student-names");
//        List<users> Groups = conn.queryView(query, users.class);
//        List<String> Test = new ArrayList<>();
//        for (users test : Groups) {
//            if (test.name != null) {
//                Test.add(test.name);
//            }
//        }
//        List<String> Result = Test.stream().sorted().toList();
//        Result.forEach(System.out::println);
//    }
//
//    //PRUEBA - SE DEBE ELIMINAR
//    public static void TotalSelections() throws MalformedURLException {
//        CouchDbConnector conn = connection.connection();
//        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("total-selections");
//        List<groups> Groups = conn.queryView(query, groups.class);
//        List<String> Test = new ArrayList<>();
//        for (groups result : Groups) {
//            if (result.GroupSelected != 0) {
//                Test.add(String.valueOf(result.GroupSelected));
//            }
//        }
//        List<String> Resultado = Test.stream().sorted(Comparator.reverseOrder()).toList();
//        Resultado.forEach(System.out::println);
//    }
// ------------------------------------------------------------------------------  
// CONSULTAS     
	//CONSULTA 1
	public String ClassPerCategory() throws MalformedURLException {
		CouchDbConnector conn = Conexion.connection();
		ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("class-per-category");
		List<groups> Groups = conn.queryView(query, groups.class);
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();

		List<String> Test = new ArrayList<>();
		for (groups result : Groups) {
			if (result.GroupCategory != null) {
				Test.add(result.GroupCategory);
			}
		}
		for (String i : Test) {
			Integer retrievedValue = map.get(i);
			if (null == retrievedValue) {
				map.put(String.valueOf(i), 1);
			} else {
				map.put(String.valueOf(i), retrievedValue + 1);
			}
		}
		formated = map.toString().replace("{", "").replace("}", "");

		System.out.println("Categorias: " + Test);
		System.out.println(map);

		return formated;
	}

	//CONSULTA 2
     public String TopStudents() throws MalformedURLException {
        String format1;
        String format2;
         
        CouchDbConnector conn = Conexion.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("students-class");
        List<StudentClass> TopStudents = conn.queryView(query, StudentClass.class);
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        List<String> Test = new ArrayList<>();
        for (StudentClass result : TopStudents) {
            if (result.Student != null) {
                Test.add(String.valueOf(result.Student));
            }
        }
        for (String i : Test) {
            Integer retrievedValue = map.get(i);
            if (null == retrievedValue) {
                map.put(String.valueOf(i), 1);
            } else {
                map.put(String.valueOf(i), retrievedValue + 1);
            }
        }
        List<String> result = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("Usuarios: " + Test);
        System.out.println(result);
        
        format1=Test.toString();
        format2= result.toString();
        
        return format1 + format2;
       
    }

//    public static void main(String[] args) throws MalformedURLException {
//        //StudentNames();
//        //TotalSelections();
//        //ClassPerCategory();
//        //TopClubs();
//        //BottomClubs();
//        //AllGroups();
//        TopStudents();
//    }
	//CONSULTA 3
public String TopClubs() throws MalformedURLException {
		String format;
		String resultado;

		CouchDbConnector conn = Conexion.connection();
		ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("top-clubs");
		List<groups> Groups = conn.queryView(query, groups.class);
		ArrayList<groups> Test = new ArrayList<>();
		for (groups result : Groups) {
			if (result.GroupName != null) {
				Test.add(new groups(result.GroupName, result.GroupCategory, result.GroupSelected));
			}
		}
		Collections.sort(Test, new Comparator<groups>() {

			@Override
			public int compare(groups o1, groups o2) {
				return Integer.valueOf(o1.GroupSelected).compareTo(o2.GroupSelected);
			}
		});
		List<String> PreResult = new ArrayList<>();
		for (int iCount = 0; iCount < Test.size(); iCount++) {
			PreResult.add(Test.get(iCount).GroupName + " " + Test.get(iCount).GroupCategory + " " + Test.get(iCount).GroupSelected + " ");
			//System.out.println(Test.get(iCount).GroupName+ " "+ Test.get(iCount).GroupCategory+" "+Test.get(iCount).GroupSelected);
		}
		List<String> Top5 = new ArrayList<>(PreResult.subList(PreResult.size() - 5, PreResult.size()));

		format = Top5.toString().replace("[", "").replace("]", "");
		resultado = reverseWords(format);

		return resultado;
	}

	//Auxiliar para invertir orden de string
	static String reverseWords(String str) {

		Pattern pattern = Pattern.compile("\\s");

		String[] temp = pattern.split(str);
		String result = "";

		for (int i = 0; i < temp.length; i++) {
			if (i == temp.length - 1) {
				result = temp[i] + result;
			} else {
				result = " " + temp[i] + result;
			}
		}
		return result;
	}

	//CONSULTA 4
	public String BottomClubs() throws MalformedURLException {
		String format;
		String resultado;

		CouchDbConnector conn = Conexion.connection();
		ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("top-clubs");
		List<groups> Groups = conn.queryView(query, groups.class);
		ArrayList<groups> Test = new ArrayList<>();
		for (groups result : Groups) {
			if (result.GroupName != null) {
				Test.add(new groups(result.GroupName, result.GroupCategory, result.GroupSelected));
			}
		}
		Collections.sort(Test, new Comparator<groups>() {

			@Override
			public int compare(groups o1, groups o2) {
				return Integer.valueOf(o2.GroupSelected).compareTo(o1.GroupSelected);
			}
		});
		List<String> PreResult = new ArrayList<>();
		for (int iCount = 0; iCount < Test.size(); iCount++) {
			PreResult.add(Test.get(iCount).GroupName + " " + Test.get(iCount).GroupCategory + " " + Test.get(iCount).GroupSelected + " ");
			//System.out.println(Test.get(iCount).GroupName+ " "+ Test.get(iCount).GroupCategory+" "+Test.get(iCount).GroupSelected);
		}
		List<String> Bottom3 = new ArrayList<>(PreResult.subList(PreResult.size() - 3, PreResult.size()));
		System.out.println(Bottom3);

		format = Bottom3.toString().replace("[", "").replace("]", "");
		resultado = reverseWords(format);

		return resultado;
	}
}
