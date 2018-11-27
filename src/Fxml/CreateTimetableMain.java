package Fxml;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

		}catch(Exception e) {
			e.printStackTrace();
		}

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
