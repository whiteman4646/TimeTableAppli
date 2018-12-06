package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Fxml.CreateTimetableMain;
import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dao.SubjectTeacherDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import dto.Subject;
import dto.Teacher;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CreateTimetableController implements Initializable {
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
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem;

	ObservableList<DepartmentCourse> dcList;
	ObservableList<String> dcnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<ClassRoom> crList;
	ObservableList<String> crnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Subject> sbList;
	ObservableList<String> sbnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Teacher> taList;
	ObservableList<String> tanameList = FXCollections.observableArrayList(new ArrayList<String>());

	@FXML
	private ColorPicker colorPicker;
	@FXML
	private ChoiceBox<String> dcchoice;
	@FXML
	private ChoiceBox<String> crchoice;
	@FXML
	private ChoiceBox<String> subchoice;
	@FXML
	private ChoiceBox<String> teachoice;
	@FXML
	private Label dcLabel;		//学科・コース
	@FXML
	private Label subLabel;	//教科
	@FXML
	private Label teaLabel;	//教員
	@FXML
	private Label crLabel;		//教室
	@FXML
	private VBox komabox, timeBox1;
	@FXML
	private GridPane timetablegrid;

	public void initialize(URL location, ResourceBundle resources){

		timetablegrid.setGridLinesVisible(true);
		komabox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

		//各種choiceboxにテーブルから名前の情報を取得させて格納
		dcList = DepartmentCourseDAO.selectDAO();
		for (int i = 0; i < dcList.size(); i++){
			dcnameList.add(dcList.get(i).getDcname());
		}
		dcchoice.setItems(dcnameList);

		crList  = ClassRoomDAO.selectDAO();
		for(int j = 0; j < crList.size(); j++){
			crnameList.add(crList.get(j).getCrname());
		}
		crchoice.setItems(crnameList);

		sbList  = SubjectTeacherDAO.selectSubject();
		for(int k = 0; k < sbList.size(); k++){
			sbnameList.add(sbList.get(k).getSubjectName());
		}
		subchoice.setItems(sbnameList);

		taList  = SubjectTeacherDAO.selectTeacher();
		for(int j = 0; j < taList.size(); j++){
			tanameList.add(taList.get(j).getTeacherName());
		}
		teachoice.setItems(tanameList);

		//学科・コースのチョイスボックス選択後ラベルに反映する
		dcchoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					dcLabel.setText(dcList.get((int) ov.getValue()).getDcname());
				});
		//教科のチョイスボックス選択後ラベルに反映する
		subchoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					subLabel.setText(sbList.get((int) ov.getValue()).getSubjectName());
				});
		//教員のチョイスボックス選択後ラベルに反映する
		teachoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					teaLabel.setText(taList.get((int) ov.getValue()).getTeacherName());
				});
		//教室のチョイスボックス選択後ラベルに反映する
		crchoice.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					crLabel.setText(crList.get((int) ov.getValue()).getCrname());
				});


	}

	@FXML
	public void actionSetColor() {
		System.out.println("え？色選択されたん？");
		subLabel.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
		teaLabel.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
		crLabel.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
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

	// ここからonDrag機能
	@FXML
	public void OnDragDetected(){
		komabox.setOnDragDetected(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow MOVE transfer mode */
                Dragboard db = komabox.startDragAndDrop(TransferMode.MOVE);

                String list = subLabel.getText() + " " + teaLabel.getText() + " " + crLabel.getText();
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(list);
                //content.putString(subLabel.getText());

                db.setContent(content);


                event.consume();
            }
        });

        timeBox1.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timeBox1 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });





        timeBox1.setOnDragDropped(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                	String getList = db.getString();
                	String[] toList = getList.split(" ");
                	System.out.println(getList);
                	timeBox1.getChildren().addAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);
                	/*subLabel.setText(toList[0]);
                	teaLabel.setText(toList[1]);
                    crLabel.setText(toList[2]);*/
                    /*subLabel1.setText(db.getString());
                    teaLabel1.setText(db.getString());
                    crLabel1.setText(db.getString());*/
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

	}


}
