package Fxml;

import java.io.IOException;

import dao.ClassRoomDAO;
import dao.DepartmentCourseDAO;
import dao.SubjectTeacherDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateTimetableMain extends Application {
	public static CreateTimetableMain singleton;
	private Stage stage;
	private Pane root;

	public static void main(String[] args) {

		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception{
		try{
			singleton = this;
			stage = primaryStage;
			// フォント色がおかしくなることへの対処
			System.setProperty( "prism.lcdtext" , "false" );

			root = (Pane)FXMLLoader.load(getClass().getResource("CreateTime.fxml"));

			// シーンの作成
			Scene   scene       = new Scene( root , 1240 , 700 );

			// ウィンドウ表示
			primaryStage.setResizable(false);
			primaryStage.setScene( scene );
			scene.getStylesheets().add(
					getClass().getResource("CourseStyle.css").toExternalForm());
			primaryStage.show();

			if(ClassRoomDAO.selectDAO().isEmpty()&&DepartmentCourseDAO.selectDAO().isEmpty()&&SubjectTeacherDAO.selectSubject().isEmpty()&&SubjectTeacherDAO.selectTeacher().isEmpty()){



				//ダイアログ表示
				Alert                   alert   = new Alert( AlertType.NONE , "" ,ButtonType.YES ,
						ButtonType.NO );

				alert.setTitle( "初期設定" );
				alert.getDialogPane().setHeaderText( "はじめての起動しましたか？" );
				alert.getDialogPane().setContentText( "初期設定しますか？" );
				alert.showAndWait()
				.filter(response -> response == ButtonType.YES)

				.ifPresent(response -> nextdcregiPage());

			}


		}catch(Exception e) {
			e.printStackTrace();
			}
		}
		private final String dcregiPage = "CourseRoom.fxml";
		@FXML
		public void nextdcregiPage(){
			CreateTimetableMain.getInstance().setPage(dcregiPage);
		}


	public static CreateTimetableMain getInstance(){
		return singleton;
	}

	//画面遷移用メソッド、引数は遷移対象のfxml
	public void setPage(String fxml){
		try {
			root = (Pane)FXMLLoader.load(getClass().getResource(fxml));
			stage.setScene(new Scene(root));
			root.getStylesheets().add(
					getClass().getResource("CourseStyle.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}