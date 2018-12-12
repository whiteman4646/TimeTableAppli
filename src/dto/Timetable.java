package dto;

public class Timetable {
	private int timetableid;
	private String week;
	private String time;
	private String period;
	private int dcid;
	private int teacherid;
	private int subjectid;
	private int crid;

	public Timetable(int timetableid, String week, String time, String period, int dcid, int teacherid, int subjectid,
			int crid) {
		super();
		this.timetableid = timetableid;
		this.week = week;
		this.time = time;
		this.period = period;
		this.dcid = dcid;
		this.teacherid = teacherid;
		this.subjectid = subjectid;
		this.crid = crid;
	}



	public int getTimetableid() {
		return timetableid;
	}
	public void setTimetableid(int timetableid) {
		this.timetableid = timetableid;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getDcid() {
		return dcid;
	}
	public void setDcid(int dcid) {
		this.dcid = dcid;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	public int getCrid() {
		return crid;
	}
	public void setCrid(int crid) {
		this.crid = crid;
	}
}
