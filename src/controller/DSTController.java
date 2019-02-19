package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dao.SubjectTeacherDAO;
import dto.Subject;
import dto.Teacher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;
import util.DSCheckBoxColumn;
import util.DTCheckBoxColumn;

public class DSTController extends TableColumn<Subject, Boolean> implements Initializable{
	private final String cttPage = "/Fxml/CreateTime.fxml";
	private final String dcregiPage = "/Fxml/CourseRoom.fxml";
	private final String crregiPage = "/Fxml/SubTea.fxml";
	private final String dcdelPage = "/Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "/Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "/Fxml/help.fxml";
	private final String ConfirmationPage = "/Fxml/ConfirmationTimetable.fxml";

	private Alert alertinfo = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
	private Alert alertwar = new Alert(AlertType.WARNING, "", ButtonType.YES, ButtonType.NO);

	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu,fileopen, ConfirmationMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem,file, ConfirmationMenuItem;

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
		alertwar.setTitle("警告");
		alertwar.setHeaderText("削除確認");
		alertwar.setContentText("削除すると戻すことはできません。\n本当に削除してよろしいですか？");
		alertwar.showAndWait()
		.filter(response -> response == ButtonType.YES)
		.ifPresent(response -> delete());
	}
	private void delete() {
		for(Subject sub : subList){
			System.out.println(sub.getCheck());
			if(sub.getCheck()){
				System.out.println(sub.getSubjectId());
				SubjectTeacherDAO.deleteSubject(sub.getSubjectId());

			}
		}
		for(Teacher tea : teaList){
			System.out.println(tea.getTeacherCheck());
			if(tea.getTeacherCheck()){
				System.out.println(tea.getTeacherId());
				SubjectTeacherDAO.deleteTeacher(tea.getTeacherId());

			}
		}
		initialize(null, null);
		alertinfo.setTitle("確認");
		alertinfo.setHeaderText("削除完了");
		alertinfo.setContentText("削除されました。");
		alertinfo.show();

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
}
