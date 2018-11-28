package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Fxml.CreateTimetableMain;
import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dao.SubjectTeacherDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import dto.Subject;
import dto.Teacher;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class CreateTimetableController implements Initializable {
	private final String cttPage = "CreateTime.fxml";
	private final String dcregiPage = "CourseRoom.fxml";
	private final String crregiPage = "SubTea.fxml";
	private final String dcdelPage = "DeleteCourseRoom.fxml";
	private final String crdelPage = "DeleteTeaSub.fxml";
	private final String helpPage = "help.fxml";

	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem;

	ObservableList<DepartmentCourse> dcList;
	ObservableList<String> dcnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<ClassRoom> crList;
	ObservableList<String> crnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Subject> sbList;
	ObservableList<String> sbnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Teacher> taList;
	ObservableList<String> tanameList = FXCollections.observableArrayList(new ArrayList<String>());

	@FXML
	private ChoiceBox<String> dcchoice;
	@FXML
	private ChoiceBox<String> crchoice;
	@FXML
	private ChoiceBox<String> subchoice;
	@FXML
	private ChoiceBox<String> teachoice;
	@FXML
	private Label dcLabel;		//学科・コース
	@FXML
	private Label subLabel;	//教科
	@FXML
	private Label teaLabel;	//教員
	@FXML
	private Label crLabel;		//教室

	public void initialize(URL location, ResourceBundle resources){

		//各種choiceboxにテーブルから名前の情報を取得させて格納
		dcList = DepartmentCourseDAO.selectDAO();
		for (int i = 0; i < dcList.size(); i++){
			dcnameList.add(dcList.get(i).getDcname());
		}
		dcchoice.setItems(dcnameList);

		crList  = ClassRoomDAO.selectDAO();
		for(int j = 0; j < crList.size(); j++){
			crnameList.add(crList.get(j).getCrname());
		}
		crchoice.setItems(crnameList);

		sbList  = SubjectTeacherDAO.selectSubject();
		for(int k = 0; k < sbList.size(); k++){
			sbnameList.add(sbList.get(k).getSubjectName());
		}
		subchoice.setItems(sbnameList);

		taList  = SubjectTeacherDAO.selectTeacher();
		for(int j = 0; j < taList.size(); j++){
			tanameList.add(taList.get(j).getTeacherName());
		}
		teachoice.setItems(tanameList);

		//学科・コースのチョイスボックス選択後ラベルに反映する
		dcchoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					dcLabel.setText(dcList.get((int) ov.getValue()).getDcname());
				});
		//教科のチョイスボックス選択後ラベルに反映する
		subchoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					subLabel.setText(sbList.get((int) ov.getValue()).getSubjectName());
				});
		//教員のチョイスボックス選択後ラベルに反映する
		teachoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					teaLabel.setText(taList.get((int) ov.getValue()).getTeacherName());
				});
		//教室のチョイスボックス選択後ラベルに反映する
		crchoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					crLabel.setText(crList.get((int) ov.getValue()).getCrname());
				});
	}

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

}
