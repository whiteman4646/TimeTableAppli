package Fxml;

import java.net.URL;
import java.util.ResourceBundle;

import dao.SubjectTeacherDAO;
import dto.Subject;
import dto.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DSTController implements Initializable{
	@FXML
	private Button deleteSTButton;

	@FXML
	private TableView<Subject> subjectTable;

	@FXML
	private TableColumn<Subject, String> subjectColumn;

	@FXML
	private TableColumn<Subject, CheckBox> sCheckColumn;

	@FXML
	private TableView<Teacher> teacherTable;

	@FXML
	private TableColumn<Teacher, String> teacherColumn;

	@FXML
	private TableColumn<Teacher, CheckBox> tCheckColumn;

	@FXML
	private void deleteST() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		subjectTable.setItems(SubjectTeacherDAO.selectSubject());
		teacherTable.setItems(SubjectTeacherDAO.selectTeacher());
		subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
		teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
	}

}
