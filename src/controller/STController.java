package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Fxml.CreateTimetableMain;
import dao.SubjectTeacherDAO;
import dto.Subject;
import dto.Teacher;
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
	private TableView<Teacher> teacherTableView;
	@FXML
	private TableView<Subject> subjectTableView;
	@FXML
	private TableColumn<Teacher, String> teacherTColumn;
	@FXML
	private TableColumn<Subject, String> subjectTColumn;
	@FXML
	private TextField teatext1, teatext2, teatext3 ,teatext4, teatext5,
					   subtext1, subtext2, subtext3, subtext4, subtext5;

	List<TextField> teaList = new ArrayList<TextField>(5);
	List<TextField> subList = new ArrayList<TextField>(5);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherTableView.setItems(SubjectTeacherDAO.selectTeacher());
		subjectTableView.setItems(SubjectTeacherDAO.selectSubject());
		teacherTColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		subjectTColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
	}
	@FXML
    public void subTeaEntry(ActionEvent e){
		teaList.add(teatext1);
		teaList.add(teatext2);
		teaList.add(teatext3);
		teaList.add(teatext4);
		teaList.add(teatext5);

		subList.add(subtext1);
		subList.add(subtext2);
		subList.add(subtext3);
		subList.add(subtext4);
		subList.add(subtext5);

		//System.out.println("登録が押されました。");
		for(TextField t: teaList) {
			if(t.getText().equals("")) {
				continue;
			}
			SubjectTeacherDAO.insertTeacher(t.getText());
			t.setText("");
		}
		for(TextField s: subList) {
			if(s.getText().equals("")) {
				continue;
			}
			SubjectTeacherDAO.insertSubject(s.getText());
			s.setText("");
		}
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
}
