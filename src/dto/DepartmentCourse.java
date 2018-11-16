package dto;

public class DepartmentCourse {
	private int dcid;
	private String dcname;

	public DepartmentCourse(int dcid, String dcname) {
		super();
		this.dcid = dcid;
		this.dcname = dcname;
	}

	public int getDcid() {
		return dcid;
	}

	public void setDcid(int dcid) {
		this.dcid = dcid;
	}

	public String getDcname() {
		return dcname;
	}

	public void setDcname(String dcname) {
		this.dcname = dcname;
	}
}
