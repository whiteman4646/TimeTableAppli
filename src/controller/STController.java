package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import dao.SubjectTeacherDAO;
import dto.Subject;
import dto.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;

public class STController implements Initializable {
	private final String cttPage = "../Fxml/CreateTime.fxml";
	private final String dcregiPage = "../Fxml/CourseRoom.fxml";
	private final String crregiPage = "../Fxml/SubTea.fxml";
	private final String dcdelPage = "../Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "../Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "../Fxml/help.fxml";
	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu, fileopen;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem, subOpen,
	teaOpen;


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
	private TextArea teatext, subtext;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teaOList = SubjectTeacherDAO.selectTeacher();
		subOList = SubjectTeacherDAO.selectSubject();
		teacherTableView.setItems(teaOList);
		subjectTableView.setItems(subOList);
		teacherTColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		subjectTColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
		teacherTColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		subjectTColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		teacherTColumn.setOnEditCommit(e -> {
            ((Teacher) e.getTableView().getItems().get(e.getTablePosition().getRow())).setTeacherName(e.getNewValue());
        });
		subjectTColumn.setOnEditCommit(e -> {
            ((Subject) e.getTableView().getItems().get(e.getTablePosition().getRow())).setSubjectName(e.getNewValue());
        });
	}
	@FXML
	public void tsUpdate() {
		for(Teacher oL : teacherTableView.getItems()) {
			if(oL.getTeacherName().equals("")) {
				continue;
			}
			SubjectTeacherDAO.updateTeacher(oL.getTeacherId(), oL.getTeacherName());
		}
		for(Subject oL : subjectTableView.getItems()) {
			if(oL.getSubjectName().equals("")) {
				continue;
			}
			SubjectTeacherDAO.updateSubject(oL.getSubjectId(), oL.getSubjectName());
		}
	}
	@FXML
    public void subTeaEntry(ActionEvent e){
		String[] teatext1 = teatext.getText().split("\n");
		String[] subtext1 = subtext.getText().split("\n");
		SubjectTeacherDAO.insertSubject(subtext1);
		SubjectTeacherDAO.insertTeacher(teatext1);
		System.out.println("登録完了！");
		teatext.setText("");
		subtext.setText("");
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
	@FXML
	private void teaOpenFile(ActionEvent a) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("ファイルを開く");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home"))
				);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("CSV", "*.csv"),
				new FileChooser.ExtensionFilter("All", "*.*")
				);
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			String words = readCSV(file);
			if (words != null) {
				teatext.setText(words);
			}
		}
	}
	@FXML
	protected void subOpenFile(ActionEvent a) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("ファイルを開く");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home"))
				);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("CSV", "*.csv"),
				new FileChooser.ExtensionFilter("All", "*.*")
				);
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			String words = readCSV(file);
			if (words != null) {
				subtext.setText(words);
			}
		}



	}
	boolean checkBeforeReadfile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}
		return false;
	}
	String readCSV(File file) {

		String display="";//テキストエリアに表示させるテキスト

		try {
			if (checkBeforeReadfile(file)) {
				FileInputStream in = new FileInputStream(file);
				InputStreamReader sr = new InputStreamReader(in, "UTF-8");
				//	                InputStreamReader sr = new InputStreamReader(in, "Shift_JIS");
				BufferedReader br = new BufferedReader(sr);

				String line;
				boolean isHeader = true;
				while ((line = br.readLine()) != null) {
					StringTokenizer token = new StringTokenizer(line, ",");

					if (isHeader) {
						while (token.hasMoreTokens()) {

							display=display+" " + token.nextToken();
						}
						isHeader = false;
					} else {
						while (token.hasMoreTokens()) {
							display=display+"\r\n" + token.nextToken();
						}
					}



				}
				br.close();
				return display;

			} else {
				System.out.println("No file exists or can't open.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException : " + e.getMessage());
		}
		return display;



	}


	ObservableList<String> readCSVToArray(File file) {

		//ArrayList<String> array=new ArrayList();//テキストエリアに表示させるテキスト
		ObservableList<String>array = FXCollections.observableArrayList(  );
		try {
			if (checkBeforeReadfile(file)) {
				FileInputStream in = new FileInputStream(file);
				InputStreamReader sr = new InputStreamReader(in, "UTF-8");
				//	                InputStreamReader sr = new InputStreamReader(in, "Shift_JIS");
				BufferedReader br = new BufferedReader(sr);

				String line;
				boolean isHeader = true;
				while ((line = br.readLine()) != null) {
					StringTokenizer token = new StringTokenizer(line, ",");


					if (isHeader) {
						while (token.hasMoreTokens()) {
							array.add(token.nextToken());
						}
						isHeader = false;
						array.add("--------------");

					} else {
						while (token.hasMoreTokens()) {
							array.add(token.nextToken());
						}
					}
				}
				br.close();
				return array;

			} else {
				System.out.println("No file exists or can't open.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException : " + e.getMessage());
		}
		return array;



	}
}
