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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;
import util.ConfigCheckBoxColumn;

public class ConfirmationController implements Initializable{
	private final String cttPage = "/Fxml/CreateTime.fxml";
	private final String dcregiPage = "/Fxml/CourseRoom.fxml";
	private final String crregiPage = "/Fxml/SubTea.fxml";
	private final String dcdelPage = "/Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "/Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "/Fxml/help.fxml";
	private final String ConfirmationPage = "/Fxml/ConfirmationTimetable.fxml";

	private Alert alertinfo = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
	private Alert alertwar = new Alert(AlertType.WARNING, "", ButtonType.YES, ButtonType.NO);

	ObservableList<DepartmentCourse> dcList;
	ObservableList<String> dcnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<ClassRoom> crList;
	ObservableList<String> crnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Teacher> taList;
	ObservableList<String> tanameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Timetable> timeList = FXCollections.observableArrayList(new ArrayList<Timetable>());
	ObservableList<String> dccheckList = FXCollections.observableArrayList(new ArrayList<String>());

	@FXML
	private AnchorPane PDFAnchor;
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
	private TableView<Timetable> dcchecktable;
	@FXML
	private TableColumn<Timetable, String> time1, monday1, tuesday1, wednesday1, thursday1, friday1,
	time2, monday2, tuesday2, wendesday2, thursday2, friday2;
	@FXML
	private TableColumn<?,?> DCcell;
	@FXML
	private TableColumn<Timetable, Boolean> checkcell;
	@FXML
	private Label timetabletitle;
	@FXML
	private Text period;
	@FXML
	private Button PDFman, deleteButton;

	int index0 = 0;
	int index1 = 0;
	int index2= 0;
	int index3 = 0;
	int index4 = 0;
	int index5 = 0;
	int index6 = 0;
	int index7 = 0;
	int index8 = 0;
	int index9 = 0;
	int index10 = 0;
	int index11 = 0;
	int index12 = 0;
	int index13 = 0;
	int index14 = 0;

	public void initialize(URL location, ResourceBundle resources){


		dccheckList = FXCollections.observableArrayList(new ArrayList<String>());
		crnameList = FXCollections.observableArrayList(new ArrayList<String>());
		tanameList = FXCollections.observableArrayList(new ArrayList<String>());
		timeList = FXCollections.observableArrayList(new ArrayList<Timetable>());
		//各種choiceboxにテーブルから名前の情報を取得させて格納
		dcList = DepartmentCourseDAO.selectDAO();
		/*for (int i = 0; i < dcList.size(); i++){
			dcnameList.add(dcList.get(i).getDcname());
		}*/
		for(DepartmentCourse dc : dcList){

			if(TimetableDAO.selectTimeatableDC(dc.getDcid()).isEmpty()){
				continue;
			}else{
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

		//timeList = TimetableDAO.selectTimetable();
		for(DepartmentCourse dc : dcList){
			if(TimetableDAO.selectTimeatableDC(dc.getDcid()).isEmpty()){
				continue;
			}else{
				System.out.println(dc);
				timeList.add(TimetableDAO.selectTimeatableDC(dc.getDcid()).get(0));
			}
		}
		dcchecktable.setItems(timeList);
		DCcell.setCellValueFactory(new PropertyValueFactory<>("dcname"));
		dcchecktable.getColumns().set(0, new ConfigCheckBoxColumn());
		dcchecktable.setEditable(true);


	}


	public void keychoiced(){
		index0 = -1;
		index1 = -1;
		index2 = -1;
		index3 = -1;
		index4 = -1;

		for(DepartmentCourse d : dcList){
			if(d.getDcname().equals(depcoursechoice1.getSelectionModel().getSelectedItem())){
				timetabletitle.setText(d.getDcname());

				ObservableList<Timetable> list = dcOList(d.getDcid());
				ConfirmationTable1.setItems(list);
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));

				monday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index0 < 7){
									if(index0 >= 0){
										String color = aOList(d.getDcid(), 0).get(index0).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index0++;
									}else {
										index0++;
									}
								}
							}
						}
					};
				});

				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				tuesday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index1< 7){
									if(index1>= 0){
										String color = aOList(d.getDcid(), 1).get(index1).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index1++;
									}else {
										index1++;
									}
								}
							}
						}
					};
				});
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				wednesday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index2< 7){
									if(index2>= 0){
										String color = aOList(d.getDcid(), 2).get(index2).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index2++;
									}else {
										index2++;
									}
								}
							}
						}
					};
				});
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				thursday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index3 < 7){
									if(index3 >= 0){
										String color = aOList(d.getDcid(), 3).get(index3).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index3++;
									}else {
										index3++;
									}
								}
							}
						}
					};
				});
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				friday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index4 < 7){
									if(index4 >= 0){
										String color = aOList(d.getDcid(), 4).get(index4).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index4++;
									}else {
										index4++;
									}
								}
							}
						}
					};
				});
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
		index5 = -1;
		index6 = -1;
		index7 = -1;
		index8 = -1;
		index9 = -1;
		for(Teacher t : taList){
			if(t.getTeacherName().equals(teacherchoice1.getSelectionModel().getSelectedItem())){
				System.out.println(t.getTeacherId() + t.getTeacherName());
				timetabletitle.setText(t.getTeacherName());
				ConfirmationTable1.setItems(teaOList(t.getTeacherId()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));
				monday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index5 < 7){
									if(index5 >= 0){
										String color = cOList(t.getTeacherId(), 0).get(index5).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index5++;
									}else {
										index5++;
									}
								}
							}
						}
					};
				});
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				tuesday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index6 < 7){
									if(index6 >= 0){
										String color = cOList(t.getTeacherId(), 1).get(index6).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index6++;
									}else {
										index6++;
									}
								}
							}
						}
					};
				});
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				wednesday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index7 < 7){
									if(index7 >= 0){
										String color = cOList(t.getTeacherId(), 2).get(index7).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index7++;
									}else {
										index7++;
									}
								}
							}
						}
					};
				});
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				thursday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index8 < 7){
									if(index8 >= 0){
										String color = cOList(t.getTeacherId(), 3).get(index8).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index8++;
									}else {
										index8++;
									}
								}
							}
						}
					};
				});
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				friday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index9 < 7){
									if(index9 >= 0){
										String color = cOList(t.getTeacherId(), 4).get(index9).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index9++;
									}else {
										index9++;
									}
								}
							}
						}
					};
				});
				period.setText("期間");
				depcoursechoice1.getSelectionModel().selectFirst();
				classroomchoice1.getSelectionModel().selectFirst();
				break;
			}
		}
	}

	public void crchoice1(){
		index10 = -1;
		index11 = -1;
		index12 = -1;
		index13 = -1;
		index14 = -1;
		for(ClassRoom c : crList){
			if(c.getCrname().equals(classroomchoice1.getSelectionModel().getSelectedItem())){
				System.out.println(c.getCrname() + c.getCrid());
				timetabletitle.setText(c.getCrname());
				ConfirmationTable1.setItems(crOList(c.getCrid()));
				time1.setCellValueFactory(new PropertyValueFactory<>("time"));
				monday1.setCellValueFactory(new PropertyValueFactory<>("komaMon"));
				monday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index10 < 7){
									if(index10 >= 0){
										String color = bOList(c.getCrid(), 0).get(index10).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index10++;
									}else {
										index10++;
									}
								}
							}
						}
					};
				});
				tuesday1.setCellValueFactory(new PropertyValueFactory<>("komaTue"));
				tuesday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index11 < 7){
									if(index11 >= 0){
										String color = bOList(c.getCrid(), 1).get(index11).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index11++;
									}else {
										index11++;
									}
								}
							}
						}
					};
				});
				wednesday1.setCellValueFactory(new PropertyValueFactory<>("komaWed"));
				wednesday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index12 < 7){
									if(index12 >= 0){
										String color = bOList(c.getCrid(), 2).get(index12).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index12++;
									}else {
										index12++;
									}
								}
							}
						}
					};
				});
				thursday1.setCellValueFactory(new PropertyValueFactory<>("komaThu"));
				thursday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index13 < 7){
									if(index13 >= 0){
										String color = bOList(c.getCrid(), 3).get(index13).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index13++;
									}else {
										index13++;
									}
								}
							}
						}
					};
				});
				friday1.setCellValueFactory(new PropertyValueFactory<>("komaFri"));
				friday1.setCellFactory(e -> {
					return new TableCell<Timetable, String>() {
						@Override
						protected void updateItem(final String item, final boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);
								if( index14 < 7){
									if(index14 >= 0){
										String color = bOList(c.getCrid(), 4).get(index14).getColor().replace("0x","");
										this.setStyle("-fx-background-color: #" + color + ";");
										index14++;
									}else {
										index14++;
									}
								}
							}
						}
					};
				});
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
		String[] list = {"\t\n\t\n\t","\t\n\t\n\t","\t\n\t\n\t","\t\n\t\n\t","\t\n\t\n\t"};
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
				list[j] = "\t\n\t\n\t";
			}

			++j;
		}
		result.add(new Timetable(String.valueOf(timeNum + 1), list[0], list[1], list[2], list[3], list[4]));



		return result;
	}
	public ObservableList<Timetable> aOList(int num, int week) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < 7; i++) {
			timetable = TimetableDAO.selectTimeatableChoiceDC(num, i + 1);
			result = oaList(timetable, i, week);
		}
		return result;
	}
	public ObservableList<Timetable> bOList(int num, int week) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < 7; i++) {
			timetable = TimetableDAO.selectTimeatableChoiceCR(num, i + 1);
			result = oaList(timetable, i, week);
		}
		return result;
	}
	public ObservableList<Timetable> cOList(int num, int week) {
		result = FXCollections.observableArrayList(new ArrayList<Timetable>());
		for(int i = 0; i < 7; i++) {
			timetable = TimetableDAO.selectTimeatableChoiceTEA(num, i + 1);
			result = oaList(timetable, i, week);
		}
		return result;
	}
	public ObservableList<Timetable> oaList(ObservableList<Timetable> object,  int timeNum, int week) {
		int j = 0;
		String[] list = {"","","","",""};
		for(Timetable str: object) {
			switch(str.getWeek()) {
			case "月曜日":
				list[0] = str.getColor();
				break;
			case "火曜日":
				list[1] = str.getColor();
				break;
			case "水曜日":
				list[2] = str.getColor();
				break;
			case"木曜日":
				list[3] = str.getColor();
				break;
			case"金曜日":
				list[4] = str.getColor();
				break;
			default:
				list[j] = "";
			}

			++j;
		}
		result.add(new Timetable(list[week]));



		return result;
	}

	@FXML
	private void deleteTimetable() {
		alertwar.setTitle("警告");
		alertwar.setHeaderText("削除確認");
		alertwar.setContentText("削除すると戻すことはできません。\n本当に削除してよろしいですか？");
		alertwar.showAndWait()
		.filter(response -> response == ButtonType.YES)
		.ifPresent(response -> delete());
	}

	@FXML
	public void delete(){
		for(Timetable t : timeList){
			System.out.println(t.getCheck());
			if(t.getCheck()){
				System.out.println(t.getDcid());
				TimetableDAO.deleteDAO(t.getDcid());
			}
		}
		initialize(null, null);
		alertinfo.setTitle("確認");
		alertinfo.setHeaderText("削除完了");
		alertinfo.setContentText("削除されました。");
		alertinfo.show();
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


		job.printPage(PDFAnchor);
		job.endJob();
	}
}