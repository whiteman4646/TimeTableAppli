package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dao.SubjectTeacherDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import dto.Subject;
import dto.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.cell.PropertyValueFactory;

public class CreateTimetableController implements Initializable {

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



	}

}
