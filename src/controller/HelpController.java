package controller;

import Fxml.HelpMain;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelpController {
	private final String cttPage = "CreateTime.fxml";

	@FXML
	private Menu cttmenu;
	@FXML
	private MenuItem cttmenuitem;

	@FXML
	public void movenextcttPage(){
		HelpMain.getInstance().setPage(cttPage);
	}
}
