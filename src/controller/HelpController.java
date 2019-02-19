package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import main.CreateTimetableMain;

public class HelpController {
	private final String cttPage = "/Fxml/CreateTime.fxml";
	private final String dcregiPage = "/Fxml/CourseRoom.fxml";
	private final String crregiPage = "/Fxml/SubTea.fxml";
	private final String dcdelPage = "/Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "/Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "/Fxml/help.fxml";
	private final String ConfirmationPage = "/Fxml/ConfirmationTimetable.fxml";

	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu,fileopen, ConfirmationMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleteMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem,file, ConfirmationMenuItem;


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
	protected void nexrfile(ActionEvent a) {
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
