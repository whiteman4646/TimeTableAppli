package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Fxml.CreateTimetableMain;
import dao.SubjectTeacherDAO;
import dto.Subject;
import dto.Teacher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class STController implements Initializable {
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

	@FXML
	private Button subTeaEntButton;
	@FXML
	private Button tsUpdateButt;
	ObservableList<Teacher> teaOList;
	ObservableList<Subject> subOList;
	@FXML
	private Button LetsCtt;
	@FXML
	private TableView<Teacher> teacherTableView;
	@FXML
	private TableView<Subject> subjectTableView;
	@FXML
	private TableColumn<Teacher, String> teacherTColumn;
	@FXML
	private TableColumn<Subject, String> subjectTColumn;
	@FXML
	private TextField teatext1,subtext1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teaOList = SubjectTeacherDAO.selectTeacher();
		subOList = SubjectTeacherDAO.selectSubject();
		teacherTableView.setItems(teaOList);
		subjectTableView.setItems(subOList);
		teacherTColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		subjectTColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
		teacherTColumn.setCellFactory(cell-> new TextFieldTableCell<>(new DefaultStringConverter()));
		subjectTColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	@FXML
	public void tsUpdate() {
		int i = 0;
		for(Teacher oL : teacherTableView.getItems()) {
			if(oL.getTeacherName().equals("") || oL.getTeacherName().equals(teaOList.get(i).getTeacherName())) {
				System.out.println(oL.getTeacherName());
				System.out.println(teaOList.get(i).getTeacherName());
				System.out.println("空文字ってどうなの？ダメでしょ！");
				i++;
				continue;
			}
			SubjectTeacherDAO.updateTeacher(oL.getTeacherId(), oL.getTeacherName());
		}
	}
	@FXML
    public void subTeaEntry(ActionEvent e){
		String[] teatext = teatext1.getText().split(",");
		String[] subtext = subtext1.getText().split(",");
		SubjectTeacherDAO.insertSubject(teatext);
		SubjectTeacherDAO.insertTeacher(subtext);
		System.out.println("登録完了！");
		initialize(null, null);
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
	@FXML
	public void Nextctt() {
		CreateTimetableMain.getInstance().setPage(cttPage);
	}
}
