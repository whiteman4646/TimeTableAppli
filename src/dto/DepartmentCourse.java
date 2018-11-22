package dto;

public class DepartmentCourse {
	private Boolean dccheck;
	private int dcid;
	private String dcname;

	public DepartmentCourse(boolean dccheck, int dcid, String dcname) {
		super();
		this.dccheck = dccheck;
		this.dcid = dcid;
		this.dcname = dcname;
	}



	public Boolean isDccheck() {
		return dccheck;
	}


	public void setDccheck(Boolean dccheck) {
		this.dccheck = dccheck;
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
