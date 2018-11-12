package Fxml;

import java.util.Arrays;
import java.util.List;

import dao.ClassRoomDAO;
import dao.DepartmetCourseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DCController  {

	@FXML
	private Button RegistButton;

	@FXML
	private TextField dctext1,dctext2, dctext3, dctext4, dctext5, dctext6, dctext7,
					crtext1, crtext2, crtext3, crtext4, crtext5, crtext6, crtext7;

	List<TextField> dclist = Arrays.asList(dctext1/*, dctext2, dctext3, dctext4, dctext5, dctext6, dctext7*/);
	List<TextField> crlist = Arrays.asList(crtext1/*, crtext2, crtext3, crtext4, crtext5, crtext6, crtext7*/);


	@FXML
    public void clickregi(ActionEvent e){
		/*for (TextField t : dclist){
			if(t.getText() == null){
				continue;
			}*/

    		DepartmetCourseDAO.insertDAO(dctext1.getText());
		//}
		/*for (TextField f : crlist){
			if(f.getText() == null){
				continue;
			}*/
			ClassRoomDAO.insertDAO(crtext1.getText());
		//}

    }

}
