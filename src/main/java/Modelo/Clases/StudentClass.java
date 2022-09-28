package Modelo.Clases;

public class StudentClass {

	public String Student;
	public String Class;

	public StudentClass() {
	}

	public StudentClass(String pStudent, String pCourse) {
		this.Class = pCourse;
		this.Student = pStudent;
	}

	public void setStudent(String pStudent) {
		Student = pStudent;
	}

	public String getStudent() {
		return Student;
	}

	public void setCourse(String pCourse) {
		Class = pCourse;
	}

	public String getCourse() {
		return Class;
	}
}
