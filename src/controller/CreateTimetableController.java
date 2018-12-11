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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Button classroombutton;
	@FXML
	private TableView<Teacher> teacherTable1, teacherTable2, teacherTable3, teacherTable4, teacherTable5;
	@FXML
	private TableView<ClassRoom> classroomTable1, classroomTable2, classroomTable3, classroomTable4, classroomTable5;
	@FXML
	private TableColumn<Teacher, String> teacherColumn1, teacherColumn2, teacherColumn3, teacherColumn4, teacherColumn5;
	@FXML
	private TableColumn<ClassRoom, String> classroomColumn1, classroomColumn2, classroomColumn3, classroomColumn4, classroomColumn5;
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
	private VBox komabox, timeBox1, timebox2, timebox3, timebox4, timebox5, timebox6, timebox7, timebox8, timebox9, timebox10,
	timebox11, timebox12, timebox13, timebox14, timebox15, timebox16, timebox17, timebox18, timebox19, timebox20, timebox21, timebox22,
	timebox23, timebox24, timebox25, timebox26, timebox27, timebox28, timebox29, timebox30, timebox31, timebox32, timebox33, timebox34, timebox35;
	@FXML
	private GridPane timetablegrid;

	public void initialize(URL location, ResourceBundle resources){

		timetablegrid.setGridLinesVisible(true);
		komabox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

		teacherTable1.setItems(SubjectTeacherDAO.selectTeacher());
		teacherColumn1.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		teacherTable2.setItems(SubjectTeacherDAO.selectTeacher());
		teacherColumn2.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		teacherTable3.setItems(SubjectTeacherDAO.selectTeacher());
		teacherColumn3.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		teacherTable4.setItems(SubjectTeacherDAO.selectTeacher());
		teacherColumn4.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		teacherTable5.setItems(SubjectTeacherDAO.selectTeacher());
		teacherColumn5.setCellValueFactory(new PropertyValueFactory<>("teacherName"));

		classroomTable1.setItems(ClassRoomDAO.selectDAO());
		classroomColumn1.setCellValueFactory(new PropertyValueFactory<>("crname"));
		classroomTable2.setItems(ClassRoomDAO.selectDAO());
		classroomColumn2.setCellValueFactory(new PropertyValueFactory<>("crname"));
		classroomTable3.setItems(ClassRoomDAO.selectDAO());
		classroomColumn3.setCellValueFactory(new PropertyValueFactory<>("crname"));
		classroomTable4.setItems(ClassRoomDAO.selectDAO());
		classroomColumn4.setCellValueFactory(new PropertyValueFactory<>("crname"));
		classroomTable5.setItems(ClassRoomDAO.selectDAO());
		classroomColumn5.setCellValueFactory(new PropertyValueFactory<>("crname"));

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
		komabox.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));

	}

	//色選択
	@FXML
	public void actionSetColor() {
		System.out.println("え？色選択されたん？");
		komabox.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
		/*subLabel.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
		teaLabel.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
		crLabel.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));*/
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
            public void handle(final DragEvent event) {
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
           // @Override
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
                	timeBox1.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timeBox1.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox2.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox2 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox2.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox2.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox2.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox3.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox3 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox3.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox3.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox3.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox4.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox4 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox4.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox4.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox4.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox5.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox5 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox5.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox5.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox5.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox6.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox6 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox6.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox6.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox6.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox7.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox7 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox7.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox7.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox7.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox8.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox8 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox8.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox8.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox8.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox9.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox9 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox9.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox9.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox9.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox10.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox10 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox10.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox10.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox10.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox11.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox11 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox11.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox11.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox11.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox12.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox12 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox12.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox12.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox12.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox13.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox13 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox13.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox13.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox13.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox14.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox14 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox14.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox14.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox14.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox15.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox15 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox15.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox15.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox15.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox16.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox16 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox16.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox16.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox16.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox17.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox17 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox17.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox17.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox17.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox18.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox18 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox18.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox18.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox18.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox19.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox19 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox19.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox19.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox19.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox20.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox20 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox20.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox20.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox20.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox21.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox21 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox21.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox21.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox21.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox22.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox22 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox22.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox22.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox22.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox23.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox23 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox23.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox23.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox23.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox24.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox24
                		&&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox24.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox24.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox24.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox25.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox25 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox25.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox25.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox25.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox26.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox26 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox26.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox26.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox26.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox27.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox27 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox27.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox27.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox27.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox28.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox28 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox28.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox28.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox28.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox29.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox29 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox29.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox29.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox29.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox30.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox30 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox30.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox30.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox30.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox31.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox31 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox31.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox31.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox31.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox32.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox32 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox32.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox32.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox32.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox33.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox33 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox33.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox33.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox33.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox34.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox34 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox34.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox34.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox34.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        timebox35.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 and if it has a string data */
                if (event.getGestureSource() != timebox35 &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        timebox35.setOnDragDropped(new EventHandler <DragEvent>() {
           // @Override
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
                	timebox35.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
                	timebox35.getChildren().setAll(
                    new Label(toList[0]),
                	new Label(toList[1]),
                	new Label(toList[2])
                	);

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

	}
	//各種画面遷移
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
