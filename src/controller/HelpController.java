package controller;

import Fxml.CreateTimetableMain;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelpController {
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
	dcdeleteMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem;


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
