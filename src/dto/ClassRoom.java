package dto;

public class ClassRoom {
	private Boolean crcheck;
	private int crid;
	private String crname;

	public ClassRoom(Boolean crcheck, int crid, String crname) {
		super();
		this.crcheck = crcheck;
		this.crid = crid;
		this.crname = crname;
	}

	public Boolean isCrcheck() {
		return crcheck;
	}

	public void setCrcheck(Boolean crcheck) {
		this.crcheck = crcheck;
	}

	public int getCrid() {
		return crid;
	}

	public void setCrid(int crid) {
		this.crid = crid;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}
}
