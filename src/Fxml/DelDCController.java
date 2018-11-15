package Fxml;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ClassRoomDAO;
import dao.DepartmetCourseDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DelDCController implements Initializable {

	@FXML
	private TableView<DepartmentCourse> dctable;
	@FXML
	private TableView<ClassRoom> crtable;
	@FXML
	private TableColumn<?, ?> dcnameColumn;
	@FXML
	private TableColumn<?, ?> crnameColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		dctable.setItems(DepartmetCourseDAO.selectDAO());
		crtable.setItems(ClassRoomDAO.selectDAO());
		dcnameColumn.setCellValueFactory(new PropertyValueFactory<>("dcname"));
		crnameColumn.setCellValueFactory(new PropertyValueFactory<>("crname"));
	}



}

