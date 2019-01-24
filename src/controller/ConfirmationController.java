package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dao.SubjectTeacherDAO;
import dao.TimetableDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import dto.Teacher;
import dto.Timetable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;

public class ConfirmationController implements Initializable{
	private final String cttPage = "../Fxml/CreateTime.fxml";
	private final String dcregiPage = "../Fxml/CourseRoom.fxml";
	private final String crregiPage = "../Fxml/SubTea.fxml";
	private final String dcdelPage = "../Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "../Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "../Fxml/help.fxml";
	private final String ConfirmationPage = "../Fxml/ConfirmationTimetable.fxml";

	ObservableList<DepartmentCourse> dcList;
	ObservableList<String> dcnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<ClassRoom> crList;
	ObservableList<String> crnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Teacher> taList;
	ObservableList<String> tanameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Timetable> timeList;
	ObservableList<String> dccheckList = FXCollections.observableArrayList(new ArrayList<String>());

	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu, fileopen, ConfirmationMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem, file, ConfirmationMenuItem;
	@FXML
	private ChoiceBox<String> depcoursechoice1, deptcoursechoice2, teacherchoice1, teacherchoice2,
	classroomchoice1, classroomchoice2;
	@FXML
	private TableView<Timetable> ConfirmationTable1;
	@FXML
	private TableView<String> dcchecktable;
	@FXML
	private TableColumn<Timetable, String> time1, monday1, tuesday1, wednesday1, thursday1, friday1,
	time2, monday2, tuesday2, wendesday2, thursday2, friday2, checkcell, DCcell;
	@FXML
	private Label timetabletitle;
	@FXML
	private Text period;
	@FXML
	private Button PDFman;

	public void initialize(URL location, ResourceBundle resources){
		//各種choiceboxにテーブルから名前の情報を取得させて格納
		dcList = DepartmentCourseDAO.selectDAO();
		/*for (int i = 0; i < dcList.size(); i++){
			dcnameList.add(dcList.get(i).getDcname());
		}*/
		for(DepartmentCourse dc : dcList){

			if(TimetableDAO.selectTimeatableDC(dc.getDcid()).isEmpty()){
				continue;
			}else{
				System.out.println(TimetableDAO.selectTimeatableDC(dc.getDcid()).get(0).getDcname());
				dccheckList.add(TimetableDAO.selectTimeatableDC(dc.getDcid()).get(0).getDcname());
			}
		}
		dccheckList.add(0, "学科・コース");
		depcoursechoice1.setItems(dccheckList);

		crList  = ClassRoomDAO.selectDAO();
		for(ClassRoom cr : crList){
			if(TimetableDAO.selectTimeatableCR(cr.getCrid()).isEmpty()){
				continue;
			}else{
				crnameList.add(TimetableDAO.selectTimeatableCR(cr.getCrid()).get(0).getCrname());
			}
		}
		crnameList.add(0, "教室");
		classroomchoice1.setItems(crnameList);

		taList  = SubjectTeacherDAO.selectTeacher();
		for(Teacher ta : taList){
			if(TimetableDAO.selectTimeatableTA(ta.getTeacherId()).isEmpty()){
				continue;
			}else{
				tanameList.add(TimetableDAO.selectTimeatableTA(ta.getTeacherId()).get(0).getTeachername());
			}
		}
		tanameList.add(0, "教員");
		teacherchoice1.setItems(tanameList);

		depcoursechoice1.getSelectionModel().selectFirst();
		depcoursechoice1.setOnAction(event -> keychoiced());
		teacherchoice1.getSelectionModel().selectFirst();
		teacherchoice1.setOnAction(event -> teachoice1());
		classroomchoice1.getSelectionModel().selectFirst();
		classroomchoice1.setOnAction(event -> crchoice1());


	}

	public void keychoiced(){
		for(DepartmentCourse d : dcList){
			if(d.getDcname().equals(depcoursechoice1.getSelectionModel().getSelectedItem())){
				System.out.println( d.getDcid() + d.getDcname());
				timetabletitle.setText(d.getDcname());
				ConfirmationTable1.setItems(dcOList(d.getDcid()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				if(TimetableDAO.selectTimeatableDC(d.getDcid()).isEmpty()){
					break;
				}else{
					period.setText(TimetableDAO.selectTimeatableDC(d.getDcid()).get(0).getPeriod());
				}
				classroomchoice1.getSelectionModel().selectFirst();
				teacherchoice1.getSelectionModel().selectFirst();
				break;
			}
		}
	}

	public void teachoice1(){
		for(Teacher t : taList){
			if(t.getTeacherName().equals(teacherchoice1.getSelectionModel().getSelectedItem())){
				System.out.println(t.getTeacherId() + t.getTeacherName());
				timetabletitle.setText(t.getTeacherName());
				ConfirmationTable1.setItems(teaOList(t.getTeacherId()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				period.setText("期間");
				depcoursechoice1.getSelectionModel().selectFirst();
				classroomchoice1.getSelectionModel().selectFirst();
				break;
			}
		}
	}

	public void crchoice1(){
		for(ClassRoom c : crList){
			if(c.getCrname().equals(classroomchoice1.getSelectionModel().getSelectedItem())){
				System.out.println(c.getCrname() + c.getCrid());
				timetabletitle.setText(c.getCrname());
				ConfirmationTable1.setItems(crOList(c.getCrid()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				period.setText("期間");
				teacherchoice1.getSelectionModel().selectFirst();
				depcoursechoice1.getSelectionModel().selectFirst();
				break;
			}
		}
	}

	ObservableList<Timetable> result;
	ObservableList<ClassRoom> classroom;
	ObservableList<Timetable> timetable;
	String[] week = {"月","火","水","木","金"};
	public ObservableList<Timetable> dcOList(int num) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < 7; i++) {
			timetable = TimetableDAO.selectTimeatableChoiceDC(num, i + 1);
			result = oList(timetable, i);
		}
		return result;
	}
	public ObservableList<Timetable> teaOList(int num) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < 7; i++) {
			timetable = TimetableDAO.selectTimeatableChoiceTEA(num, i + 1);
			result = oList(timetable, i);
		}
		return result;
	}
	public ObservableList<Timetable> crOList(int num) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < 7; i++) {
			timetable = TimetableDAO.selectTimeatableChoiceCR(num, i + 1);
			result = oList(timetable, i);
		}
		return result;
	}

	public ObservableList<Timetable> oList(ObservableList<Timetable> object,  int timeNum) {
		int j = 0;
		String[] list = {"","","","",""};
		//timetable = object;
		for(Timetable str: object) {
			switch(str.getWeek()) {
			case "月曜日":
				list[0] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
				break;
			case "火曜日":
				list[1] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
				break;
			case "水曜日":
				list[2] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
				break;
			case"木曜日":
				list[3] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
				break;
			case"金曜日":
				list[4] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
				break;
			default:
				list[j] = "";
			}

			++j;
		}
		result.add(new Timetable(String.valueOf(timeNum + 1), list[0], list[1], list[2], list[3], list[4]));



		return result;
	}

	//各種画面遷移
	@FXML
	public void nextcttPage(){
		CreateTimetableMain.getInstance().setPage(cttPage);
	}

	@FXML
	public void nextdcregiPage(){
		CreateTimetableMain.getInstance().setPage(dcregiPage);
	}

	@FXML
	public void nextcrregiPage(){
		CreateTimetableMain.getInstance().setPage(crregiPage);
	}

	@FXML
	public void nextdcdelPage(){
		CreateTimetableMain.getInstance().setPage(dcdelPage);
	}

	@FXML
	public void nextcrdelPage(){
		CreateTimetableMain.getInstance().setPage(crdelPage);
	}

	@FXML
	public void nexthelpPage(){
		CreateTimetableMain.getInstance().setPage(helpPage);
	}

	@FXML
	public void nextConfirmationPage(){
		CreateTimetableMain.getInstance().setPage(ConfirmationPage);
	}

	@FXML
	protected void nextfile(ActionEvent a) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("ファイルを開く");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home"))
				);
		File file = fileChooser.showOpenDialog(null);

		String url = "file:///"+file.getPath();

		System.out.println(url);
	}
	@FXML
	public void printpdf(ActionEvent event) {
		PrinterJob job = PrinterJob.createPrinterJob();

		job.printPage(ConfirmationTable1);
		job.endJob();
	}
}