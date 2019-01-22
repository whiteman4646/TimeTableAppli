
package controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;

public class DCController implements Initializable {
	private final String cttPage = "../Fxml/CreateTime.fxml";
	private final String dcregiPage = "../Fxml/CourseRoom.fxml";
	private final String crregiPage = "../Fxml/SubTea.fxml";
	private final String dcdelPage = "../Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "../Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "../Fxml/help.fxml";
	private final String ConfirmationPage = "../Fxml/ConfirmationTimetable.fxml";

	private Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);

	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu,fileopen, ConfirmationMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem, dcOpen,
	crOpen, ConfirmationMenuItem;
	@FXML
	private Button NextButton;
	@FXML
	private Button RegistButton;
	@FXML
	private Button dcUpdateButt;
	@FXML
	private TableView<DepartmentCourse> dcTableView;
	@FXML
	private TableView<ClassRoom> crTableView;
	@FXML
	private TableColumn<DepartmentCourse, String> dcTColumn;
	@FXML
	private TableColumn<ClassRoom, String> crTColumn;
	@FXML
	private TextArea dctext1, crtext1;

	List<TextField> dclist = new ArrayList<TextField>();
	List<TextField> crlist = new ArrayList<TextField>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dcTableView.setItems(DepartmentCourseDAO.selectDAO());
		crTableView.setItems(ClassRoomDAO.selectDAO());
		dcTColumn.setCellValueFactory(new PropertyValueFactory<>("dcname"));
		crTColumn.setCellValueFactory(new PropertyValueFactory<>("crname"));
		dcTColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		crTColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		dcTColumn.setOnEditCommit(e -> {
            ((DepartmentCourse) e.getTableView().getItems().get(e.getTablePosition().getRow())).setDcname(e.getNewValue());
        });
		crTColumn.setOnEditCommit(e -> {
            ((ClassRoom) e.getTableView().getItems().get(e.getTablePosition().getRow())).setCrname(e.getNewValue());
        });
	}

	@FXML
	public void dcUpdate() {
		for(DepartmentCourse oL : dcTableView.getItems()) {
			if(oL.getDcname().equals("")) {
				continue;
			}
			DepartmentCourseDAO.updateDAO(oL.getDcid(), oL.getDcname());
		}
		for(ClassRoom oL : crTableView.getItems()) {
			if(oL.getCrname().equals("")) {
				continue;
			}
			ClassRoomDAO.updateDAO(oL.getCrid(), oL.getCrname());
		}
		alert.setTitle("確認");
		alert.setHeaderText("更新完了");
		alert.setContentText("更新されました。");
		alert.show();
	}
	@FXML
	public void clickregi(ActionEvent e){
		String[] crtext = crtext1.getText().split("\n");
		String[] dctext = dctext1.getText().split("\n");
		ClassRoomDAO.insertDAO(crtext);
		DepartmentCourseDAO.insertDAO(dctext);
		System.out.println("登録完了");
		crtext1.setText("");
		dctext1.setText("");
		alert.setTitle("確認");
		alert.setHeaderText("登録完了");
		alert.setContentText("登録されました。");
		alert.show();
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
	public void NextBu(){
		CreateTimetableMain.getInstance().setPage(crregiPage);
	}

	@FXML
	public void nextConfirmationPage(){
		CreateTimetableMain.getInstance().setPage(ConfirmationPage);
	}

	@FXML
	private void dcOpenFile(ActionEvent a) {
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
				dctext1.setText(words);
			}
		}
	}
	@FXML
	protected void crOpenFile(ActionEvent a) {

		crtext1.setText("初期化");
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
				crtext1.setText(words);
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

