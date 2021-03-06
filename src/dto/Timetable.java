package dto;

public class Timetable {
	private Boolean check;
	private int timetableid;
	private String week;
	private String time;
	private String period;
	private String dcname;
	private String teachername;
	private String subjectname;
	private String subjectname2;
	private String subjectname3;
	private String subjectname4;
	private String subjectname5;
	private String subjectname6;
	private String subjectname7;
	private String crname;
	private String objectname;
	private String komaMon;
	private String komaTue;
	private String komaWed;
	private String komaThu;
	private String komaFri;
	private String color;
	private String color1;
	private String color2;
	private String color3;
	private String color4;
	private int dcid;
	private int teacherid;
	private int subjectid;
	private int crid;

	public String getKomaMon() {
		return komaMon;
	}

	public void setKomaMon(String komaMon) {
		this.komaMon = komaMon;
	}

	public String getKomaTue() {
		return komaTue;
	}


	public void setKomaTue(String komaTue) {
		this.komaTue = komaTue;
	}

	public String getKomaWed() {
		return komaWed;
	}

	public void setKomaWed(String komaWed) {
		this.komaWed = komaWed;
	}

	public String getKomaThu() {
		return komaThu;
	}

	public void setKomaThu(String komaThu) {
		this.komaThu = komaThu;
	}

	public String getKomaFri() {
		return komaFri;
	}

	public void setKomaFri(String komaFri) {
		this.komaFri = komaFri;
	}

	public Timetable(String time, String komaMon, String komaTue, String komaWed, String komaThu, String komaFri) {
		super();
		this.time = time;
		this.komaMon = komaMon;
		this.komaTue = komaTue;
		this.komaWed = komaWed;
		this.komaThu = komaThu;
		this.komaFri = komaFri;
	}

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

	/*public Timetable(int timetableid, String week, String time, String period, String dcname, String teachername,
			String subjectname, String crname) {
		super();
		this.timetableid = timetableid;
		this.week = week;
		this.time = time;
		this.period = period;
		this.dcname = dcname;
		this.teachername = teachername;
		this.subjectname = subjectname;
		this.crname = crname;
	}*/

	public Timetable(String objectname, String subjectname, String subjectname2, String subjectname3,
			String subjectname4, String subjectname5, String subjectname6, String subjectname7) {
		super();
		this.objectname = objectname;
		this.subjectname = subjectname;
		this.subjectname2 = subjectname2;
		this.subjectname3 = subjectname3;
		this.subjectname4 = subjectname4;
		this.subjectname5 = subjectname5;
		this.subjectname6 = subjectname6;
		this.subjectname7 = subjectname7;
	}

	public Timetable(String time, String teachername, String subjectname, String crname) {
		super();
		this.time = time;
		this.teachername = teachername;
		this.subjectname = subjectname;
		this.crname = crname;
	}

	public Timetable(int teacherid, String teachername){
		this.teacherid = teacherid;
		this.teachername = teachername;
	}

	public  Timetable(String crname, int crid){
		this.crid = crid;
		this.crname = crname;
	}

	public Timetable(String week, String time, String period, String dcname, String teachername, String subjectname,
			String crname, int dcid, int teacherid, int subjectid, int crid, String color) {
		super();
		this.week = week;
		this.time = time;
		this.period = period;
		this.dcname = dcname;
		this.teachername = teachername;
		this.subjectname = subjectname;
		this.crname = crname;
		this.dcid = dcid;
		this.teacherid = teacherid;
		this.subjectid = subjectid;
		this.crid = crid;
		this.color = color;
	}

	public Timetable(Boolean check, String week, String time, String period, String dcname, String teachername, String subjectname,
			String crname, int dcid, int teacherid, int subjectid, int crid) {
		super();
		this.check = check;
		this.week = week;
		this.time = time;
		this.period = period;
		this.dcname = dcname;
		this.teachername = teachername;
		this.subjectname = subjectname;
		this.crname = crname;
		this.dcid = dcid;
		this.teacherid = teacherid;
		this.subjectid = subjectid;
		this.crid = crid;
	}

	public Timetable(String week, String time, String period, String dcname, String teachername,
			String subjectname, String crname, int dcid, int teacherid, int subjectid, int crid) {
		this.week = week;
		this.time = time;
		this.period = period;
		this.dcname = dcname;
		this.teachername = teachername;
		this.subjectname = subjectname;
		this.crname = crname;
		this.dcid = dcid;
		this.teacherid = teacherid;
		this.subjectid = subjectid;
		this.crid = crid;
	}



	public Timetable(String color) {
		super();
		this.color = color;

	}

	public Boolean getCheck(){
		return check;
	}
	public void setCheck(boolean check){
		this.check = check;
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
	public String getDcname() {
		return dcname;
	}

	public void setDcname(String dcname) {
		this.dcname = dcname;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
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

	public String getSubjectname2() {
		return subjectname2;
	}

	public void setSubjectname2(String subjectname2) {
		this.subjectname2 = subjectname2;
	}

	public String getSubjectname3() {
		return subjectname3;
	}

	public void setSubjectname3(String subjectname3) {
		this.subjectname3 = subjectname3;
	}

	public String getSubjectname4() {
		return subjectname4;
	}

	public void setSubjectname4(String subjectname4) {
		this.subjectname4 = subjectname4;
	}

	public String getSubjectname5() {
		return subjectname5;
	}

	public void setSubjectname5(String subjectname5) {
		this.subjectname5 = subjectname5;
	}

	public String getSubjectname6() {
		return subjectname6;
	}

	public void setSubjectname6(String subjectname6) {
		this.subjectname6 = subjectname6;
	}

	public String getSubjectname7() {
		return subjectname7;
	}

	public void setSubjectname7(String subjectname7) {
		this.subjectname7 = subjectname7;
	}

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColor3() {
		return color3;
	}

	public void setColor3(String color3) {
		this.color3 = color3;
	}

	public String getColor4() {
		return color4;
	}

	public void setColor4(String color4) {
		this.color4 = color4;
	}

}
