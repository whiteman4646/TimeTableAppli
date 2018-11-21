package dto;

public class Subject {
	private Boolean check;
	private Integer subjectId;
	private String subjectName;


	public Subject(boolean check, int subjectId, String subjectName) {
		super();
		this.check = check;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
