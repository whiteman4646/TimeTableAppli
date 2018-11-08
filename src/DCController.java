package Fxml;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DCController  {

	@FXML
	private Button RegistButton;


	@FXML
    public void clickregi(ActionEvent e){
    	System.out.println("登録が押されました");
    }



}
