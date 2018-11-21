package dto;

public class Teacher {
	private Boolean teacherCheck;
	private Integer teacherId;
	private String teacherName;

	public Teacher(boolean teacherCheck, int teacherId, String teacherName) {
		super();
		this.teacherCheck = teacherCheck;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
	}

	public Boolean getCheck() {
		return teacherCheck;
	}

	public void setCheck(Boolean teacherCheck) {
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
