package Fxml;

import dao.SubjectTeacherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class STController {

	@FXML
	private Button subTeaEntButton;

	@FXML
	private TextField teatext;

	@FXML
    public void subTeaEntry(ActionEvent e){
		System.out.println("登録が押されました。");
		SubjectTeacherDAO.insertSubject(teatext.getText());
    }
}
