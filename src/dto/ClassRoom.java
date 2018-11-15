package dto;

public class ClassRoom {
	private int crid;
	private String crname;

	public ClassRoom(int crid, String crname) {
		super();
		this.crid = crid;
		this.crname = crname;
	}

	public int getId() {
		return crid;
	}

	public void setId(int crid) {
		this.crid = crid;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}
}
