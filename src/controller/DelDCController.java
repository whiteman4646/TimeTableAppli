package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;
import util.CRCheckBoxColumn;
import util.DCCheckBoxColumn;

public class DelDCController implements Initializable {
	private final String cttPage = "../Fxml/CreateTime.fxml";
	private final String dcregiPage = "../Fxml/CourseRoom.fxml";
	private final String crregiPage = "../Fxml/SubTea.fxml";
	private final String dcdelPage = "../Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "../Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "../Fxml/help.fxml";
	private final String ConfirmationPage = "../Fxml/ConfirmationTimetable.fxml";

	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu,fileopen, ConfirmationMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem,file, ConfirmationMenuItem;

	ObservableList<DepartmentCourse> dcList;
	ObservableList<ClassRoom>crList;

	@FXML
	private TableView<DepartmentCourse> dctable;
	@FXML
	private TableView<ClassRoom> crtable;
	@FXML
	private TableColumn<?, ?> dcnameColumn;
	@FXML
	private TableColumn<?, ?> crnameColumn;
	@FXML
	private TableColumn<DepartmentCourse, Boolean> dccheckColumn;
	@FXML
	private TableColumn<ClassRoom, Boolean> crcheckColumn;
	@FXML
	private Button deleteButton;

	@Override
	public void initialize(URL location, ResourceBundle resources){

		dcList = DepartmentCourseDAO.selectDAO();

		dctable.setItems(dcList);

		crList = ClassRoomDAO.selectDAO();
		crtable.setItems(crList);


		/*//カラムインスタンスの生成
		TableColumn<DepartmentCourse,Integer> dcidColumn = new TableColumn<>("dcid");
		//最小サイズの設定
		dcidColumn.setMinWidth(20);
		//カラムとDTOの関連付け
		//ジェネリクスは<DTOの型 , カラムの型 >
		//引数は(DTOクラスのフィールド名)
		dcidColumn.setCellValueFactory(
				new PropertyValueFactory<DepartmentCourse,Integer>("dcid"));*/


		dctable.getColumns().set(0, new DCCheckBoxColumn());
		crtable.getColumns().set(0, new CRCheckBoxColumn());
		//dctable.getColumns().add(dcidColumn);

		dcnameColumn.setCellValueFactory(new PropertyValueFactory<>("dcname"));
		//dccheckColumn.setCellValueFactory(new PropertyValueFactory<DepartmentCourse, Boolean>("dccheck"));
		//dccheckColumn.setCellFactory(CheckBoxTableCell.forTableColumn(dccheckColumn));
		dctable.setEditable(true);




		//crtable.setItems(ClassRoomDAO.selectDAO());
		crnameColumn.setCellValueFactory(new PropertyValueFactory<>("crname"));
		//crcheckColumn.setCellValueFactory(new PropertyValueFactory<ClassRoom, Boolean>("crcheck"));
		//crcheckColumn.setCellFactory(CheckBoxTableCell.forTableColumn(crcheckColumn));
		crtable.setEditable(true);

	}

	@FXML
	public void clickdelete(){

		for(DepartmentCourse dc : dcList){
			if(dc.isDccheck()){
				System.out.println(dc.getDcid());
				DepartmentCourseDAO.deleteDAO(dc.getDcid());

			}
		}
		for(ClassRoom cr : crList){
			if(cr.isCrcheck()){
				System.out.println(cr.getCrid());
				ClassRoomDAO.deleteDAO(cr.getCrid());

			}
		}


		initialize(null, null);
		/*dctable.refresh();
		DepartmentCourseDAO.selectDAO();
		dctable.setItems(dcList);

		crtable.refresh();
		ClassRoomDAO.selectDAO();
		crtable.setItems(crList);*/


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

