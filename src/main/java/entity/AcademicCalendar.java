package main.java.entity;

public class AcademicCalendar
{
	private String name;
	
	private String type;
	
	private String attendanceAllowed;
	
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAttendanceAllowed() {
		return attendanceAllowed;
	}

	public void setAttendanceAllowed(String attendanceAllowed) {
		this.attendanceAllowed = attendanceAllowed;
	}

	public AcademicCalendar(String name, String type, String attendanceAllowed,String date) {
		super();
		this.name = name;
		this.type = type;
		this.attendanceAllowed = attendanceAllowed;
		this.date = date;
	}
	
	
	
	
	
}
