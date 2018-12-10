package dto;

public class Teacher {
	private Boolean teacherCheck;
	private Integer teacherId;
	private String teacherName;

	public Teacher(boolean check, int id, String name) {
		super();
		this.teacherCheck = check;
		this.teacherId = id;
		this.teacherName = name;
	}

	public Boolean getTeacherCheck() {
		return teacherCheck;
	}

	public void setTeacherCheck(Boolean teacherCheck) {
		this.teacherCheck = teacherCheck;
	}

	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}


	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
