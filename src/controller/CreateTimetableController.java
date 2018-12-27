package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dao.SubjectTeacherDAO;
import dao.TimetableDAO;
import dto.ClassRoom;
import dto.DepartmentCourse;
import dto.Subject;
import dto.Teacher;
import dto.Timetable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import javafx.stage.FileChooser;
import main.CreateTimetableMain;

public class CreateTimetableController implements Initializable {
	private final String cttPage = "../Fxml/CreateTime.fxml";
	private final String dcregiPage = "../Fxml/CourseRoom.fxml";
	private final String crregiPage = "../Fxml/SubTea.fxml";
	private final String dcdelPage = "../Fxml/DeleteCourseRoom.fxml";
	private final String crdelPage = "../Fxml/DeleteTeaSub.fxml";
	private final String helpPage = "../Fxml/help.fxml";
	private final String ConfirmationPage = "../Fxml/ConfirmationTimetable.fxml";


	@FXML
	private ArrayList<VBox> TimeBox;
	@FXML
	private Menu cttmenu, registmenu, deleteMenu, helpMenu, fileopen, ConfirmationMenu;
	@FXML
	private MenuItem cttmenuitem, dcregimenuItem, crregimenuItem,
	dcdeleMenuItem, crdeleMenuItem, nexthelpMenuItem, helpMenuItem, file, ConfirmationMenuItem;

	ObservableList<DepartmentCourse> dcList;
	ObservableList<String> dcnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<ClassRoom> crList;
	ObservableList<String> crnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Subject> sbList;
	ObservableList<String> sbnameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Teacher> taList;
	ObservableList<String> tanameList = FXCollections.observableArrayList(new ArrayList<String>());
	ObservableList<Timetable> timeteacherList;

	@FXML
	private Button classroombutton;
	@FXML
	private Button createbutton;
	@FXML
	private TableView<Timetable> teacherTable1, teacherTable2, teacherTable3, teacherTable4, teacherTable5;
	@FXML
	private TableView<Timetable> classroomTable1, classroomTable2, classroomTable3, classroomTable4, classroomTable5;
	@FXML
	private TableColumn<Teacher, String> teacherColumn1, teacherColumn2, teacherColumn3, teacherColumn4, teacherColumn5,
	teachmon1;
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
	private VBox komabox, timebox1, timebox2, timebox3, timebox4, timebox5, timebox6, timebox7, timebox8, timebox9, timebox10,
	timebox11, timebox12, timebox13, timebox14, timebox15, timebox16, timebox17, timebox18, timebox19, timebox20, timebox21, timebox22,
	timebox23, timebox24, timebox25, timebox26, timebox27, timebox28, timebox29, timebox30, timebox31, timebox32, timebox33, timebox34, timebox35;
	@FXML
	private GridPane timetablegrid;
	@FXML
	private TextField startmonth, startday, endmonth, endday;

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
		for(int l = 0; l < taList.size();l++){
			tanameList.add(taList.get(l).getTeacherName());
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


		teacherTable1.setItems(TimetableDAO.allteacher());
		teacherColumn1.setCellValueFactory(new PropertyValueFactory<>("teachername"));

		teacherTable2.setItems(TimetableDAO.allteacher());
		teacherColumn2.setCellValueFactory(new PropertyValueFactory<>("teachername"));

		teacherTable3.setItems(TimetableDAO.allteacher());
		teacherColumn3.setCellValueFactory(new PropertyValueFactory<>("teachername"));

		teacherTable4.setItems(TimetableDAO.allteacher());
		teacherColumn4.setCellValueFactory(new PropertyValueFactory<>("teachername"));

		teacherTable5.setItems(TimetableDAO.allteacher());
		teacherColumn5.setCellValueFactory(new PropertyValueFactory<>("teachername"));


		classroomTable1.setItems(TimetableDAO.allclassroom());
		classroomColumn1.setCellValueFactory(new PropertyValueFactory<>("crname"));

		classroomTable2.setItems(TimetableDAO.allclassroom());
		classroomColumn2.setCellValueFactory(new PropertyValueFactory<>("crname"));

		classroomTable3.setItems(TimetableDAO.allclassroom());
		classroomColumn3.setCellValueFactory(new PropertyValueFactory<>("crname"));

		classroomTable4.setItems(TimetableDAO.allclassroom());
		classroomColumn4.setCellValueFactory(new PropertyValueFactory<>("crname"));

		classroomTable5.setItems(TimetableDAO.allclassroom());
		classroomColumn5.setCellValueFactory(new PropertyValueFactory<>("crname"));

		//初期色
		komabox.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));


	}

	//色選択
	@FXML
	public void actionSetColor() {
		komabox.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
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

		for(int a = 0; a < 35; a++){

			VBox v = TimeBox.get(a);

			v.setOnDragDetected(new EventHandler <MouseEvent>() {
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

			v.setOnDragOver(new EventHandler <DragEvent>() {
				@Override
				public void handle(final DragEvent event) {
					/* data is dragged over the target */
					System.out.println("onDragOver");

					/* accept it only if it is  not dragged from the same node
                 and if it has a string data */
					if (event.getGestureSource() != v &&
							event.getDragboard().hasString()) {
						/* allow for moving */
						event.acceptTransferModes(TransferMode.MOVE);
					}

					event.consume();
				}
			});

			v.setOnDragDropped(new EventHandler <DragEvent>() {
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
						v.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));

						Label label1 = new Label(toList[0]);
						Label label2 = new Label(toList[1]);
						Label label3 = new Label(toList[2]);
						label1.setId("Subject");
						label2.setId("Teacher");
						label3.setId("Classroom");

						v.getChildren().setAll(
								label1,
								label2,
								label3
								);

						success = true;
					}
					/* let the source know whether the string was successfully
					 * transferred and used */
					event.setDropCompleted(success);

					event.consume();
					//Node n = timeBox1.lookup("#Teacher");
					//Label l = (Label)v.lookup("#Teacher");
					//System.out.println(l.getText());

				}
			});
		}
	}

	//時間割作成
	@FXML
	public void createtimetable(){

		String [][] key = new String[35][7];
		for(int i = 0; i < 35; i++){
			if(TimeBox.get(i).getChildren().isEmpty()){
				continue;
			}
			int j = i + 1;
			if(1 <= j && j <= 7){
				key[i][0] = "月曜日";
			} else if(8 <= j && j <= 14){
				key[i][0] = "火曜日";
			} else if(15 <= j && j <= 21){
				key[i][0] = "水曜日";
			} else if(22 <= j && j <= 28){
				key[i][0] = "木曜日";
			} else if(29 <= j && j <= 35){
				key[i][0] = "金曜日";
			}

			int k = (i + 1) % 7;
			switch (k) {
			case 0:
				key[i][1] = "7";
				break;
			case 1:
				key[i][1] = "1";
				break;
			case 2:
				key[i][1] = "2";
				break;
			case 3:
				key[i][1] = "3";
				break;
			case 4:
				key[i][1] = "4";
				break;
			case 5:
				key[i][1] = "5";
				break;
			case 6:
				key[i][1] = "6";
				break;
			}
			key[i][2] = startmonth.getText() + "月" + startday.getText() + "日～" +  endmonth.getText() + "月" + endday.getText() + "日";
			//各種IDでの登録
			//String depid = "";
			for(DepartmentCourse d : dcList){
				if(d.getDcname().equals(dcLabel.getText())){
					key[i][3] = ""+d.getDcid();
					break;
				}
			}
			//key[i][3] = depid;

			//String teaid = "";
			for(Teacher t : taList){
				Label la;
				la = (Label)TimeBox.get(i).lookup("#Teacher");
				if(la != null){
					System.out.println(t.getTeacherName().equals(la.getText()));
					System.out.println(t.getTeacherId());
				}
				if(la != null && t != null && t.getTeacherName().equals(la.getText())){
					key[i][4] = ""+t.getTeacherId();
					System.out.println(""+t.getTeacherId());
					System.out.println(t.getTeacherId().toString());
					break;
				}
			}
			//key[i][4] = teaid;

			//String subid = "";
			for(Subject s : sbList){
				Label lb;
				lb = (Label)TimeBox.get(i).lookup("#Subject");
				if(lb != null){
					System.out.println(s.getSubjectName().equals(lb.getText()));
					System.out.println(s.getSubjectId());
				}
				if(lb != null && s != null && s.getSubjectName().equals(lb.getText())){
					key[i][5] = s.getSubjectId().toString();
					break;
				}
			}
			//key[i][5] = subid;

			//String crid = "";
			for(ClassRoom c : crList){
				Label lc;
				lc = (Label)TimeBox.get(i).lookup("#Classroom");
				if(lc != null){
					System.out.println(c.getCrname().equals(lc.getText()));
					System.out.println(c.getCrid());
				}
				if(lc != null && c != null && c.getCrname().equals(lc.getText())){
					key[i][6] = ""+c.getCrid();
					break;
				}
			}
			//key[i][6] = crid;

		}
		TimetableDAO.insertTimetable(key);

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
