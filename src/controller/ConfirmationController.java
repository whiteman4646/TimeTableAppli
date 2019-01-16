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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableColumn<Timetable, String> time1, monday1, tuesday1, wednesday1, thursday1, friday1,

	time2, monday2, tuesday2, wendesday2, thursday2, friday2;
	@FXML
	private Button PDFman;

	public void initialize(URL location, ResourceBundle resources){
		//各種choiceboxにテーブルから名前の情報を取得させて格納
		dcList = DepartmentCourseDAO.selectDAO();
		for (int i = 0; i < dcList.size(); i++){
			dcnameList.add(dcList.get(i).getDcname());
		}
		depcoursechoice1.setItems(dcnameList);

		crList  = ClassRoomDAO.selectDAO();
		for(int j = 0; j < crList.size(); j++){
			crnameList.add(crList.get(j).getCrname());
		}
		classroomchoice1.setItems(crnameList);

		taList  = SubjectTeacherDAO.selectTeacher();
		for(int l = 0; l < taList.size();l++){
			tanameList.add(taList.get(l).getTeacherName());
		}
		teacherchoice1.setItems(tanameList);

		depcoursechoice1.setOnAction(event -> keychoiced());
		teacherchoice1.setOnAction(event -> teachoice1());
		classroomchoice1.setOnAction(event -> crchoice1());

	}

	public void keychoiced(){
		for(DepartmentCourse d : dcList){
			if(d.getDcname().equals(depcoursechoice1.getSelectionModel().getSelectedItem())){
				System.out.println(d.getDcid());
				ConfirmationTable1.setItems(dOList(d.getDcid()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				break;
			}
		}
	}

	public void teachoice1(){
		for(Teacher t : taList){
			if(t.getTeacherName().equals(teacherchoice1.getSelectionModel().getSelectedItem())){
				ConfirmationTable1.setItems(TimetableDAO.selectTimeatableChoiceTEA(t.getTeacherId()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("teachername"));
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("crname"));
				thursday1.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
				friday1.setCellValueFactory(new PropertyValueFactory<>("teachername"));
				break;
			}
		}
	}

	public void crchoice1(){
		for(ClassRoom c : crList){
			if(c.getCrname().equals(classroomchoice1.getSelectionModel().getSelectedItem())){
				ConfirmationTable1.setItems(TimetableDAO.selectTimeatableChoiceCR(c.getCrid()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("teachername"));
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("crname"));
				thursday1.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
				friday1.setCellValueFactory(new PropertyValueFactory<>("teachername"));
				break;
			}
		}
	}

	ObservableList<Timetable> result;
	ObservableList<ClassRoom> classroom;
	ObservableList<Timetable> timetable;
	String[] week = {"月","火","水","木","金"};
	public ObservableList<Timetable> dOList(int num) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < timetable.size(); i++) {
			timetable = TimetableDAO.selectTimeatableChoiceDC(num);
			/*if(timetable.isEmpty()) {
				continue;
			}*/
			int j = 0;
			String[] list = {"","","","","","",""};
			for(Timetable str: timetable) {

				switch (timetable.get(j).getTime()) {
				case "1":
					if(list[0].equals("")) j++;
					list[0] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					break;
				case "2":
					list[1] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					j++;
					break;
				case "3":
					list[2] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					j++;
					break;
				case "4":
					list[3] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					j++;
					break;
				case "5":
					list[4] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					j++;
					break;
				case "6":
					list[5] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					j++;
					break;
				case "7":
					list[6] = str.getSubjectname() + "\n" + str.getTeachername() + "\n" + str.getCrname();
					j++;
					break;
				default:
					list[j] = "";
					break;
				}
				j++;
			}
			result.add(new Timetable(String.valueOf(j), list[0], list[1], list[2], list[3], list[4]));

		}

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