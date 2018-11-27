package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.SubjectTeacherDAO;
import dto.Subject;
import dto.Teacher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DSCheckBoxColumn;
import util.DTCheckBoxColumn;

public class DSTController extends TableColumn<Subject, Boolean> implements Initializable{
	ObservableList<Subject> subList;
	ObservableList<Teacher> teaList;

	@FXML
	private Button deleteSTButton;

	@FXML
	private TableView<Subject> subjectTable;

	@FXML
	private TableColumn<Subject, String> subjectColumn;

	@FXML
	private TableColumn<Subject, Boolean> sCheckColumn;

	@FXML
	private TableView<Teacher> teacherTable;

	@FXML
	private TableColumn<Teacher, String> teacherColumn;

	@FXML
	private TableColumn<Teacher, Boolean> tCheckColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		subList = SubjectTeacherDAO.selectSubject();
		teaList = SubjectTeacherDAO.selectTeacher();

		//DSCheckBoxColumn dscolumn = new DSCheckBoxColumn();
		//DTCheckBoxColumn dtcolumn = new DTCheckBoxColumn();

		subjectTable.setItems(subList);
		teacherTable.setItems(teaList);

		subjectTable.getColumns().set(0, new DSCheckBoxColumn());
		teacherTable.getColumns().set(0, new DTCheckBoxColumn());


		//sCheckColumn.setCellFactory(CheckBoxTableCell.forTableColumn(dscolumn));
		//tCheckColumn.setCellFactory(CheckBoxTableCell.forTableColumn(dtcolumn));

		subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
		teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));

		subjectTable.setEditable(true);
		teacherTable.setEditable(true);

	}

	@FXML
	private void deleteST() {
		for(Subject sub : subList){
			System.out.println(sub.getCheck());
			if(sub.getCheck()){
				System.out.println(sub.getSubjectId());
				SubjectTeacherDAO.deleteSubject(sub.getSubjectId());

			}
		}
		for(Teacher tea : teaList){
			System.out.println(tea.getCheck());
			if(tea.getCheck()){
				System.out.println(tea.getTeacherId());
				SubjectTeacherDAO.deleteTeacher(tea.getTeacherId());

			}
		}
		System.out.println("onAction delete");
		initialize(null, null);
	}


}
