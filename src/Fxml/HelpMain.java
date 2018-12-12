package Fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelpMain extends Application {
	//public static HelpMain singleton;
	//private Stage stage;
	private Pane root;

	public static void main(String[] args) {

		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception{

		try{
			//singleton = this;
			//stage = primaryStage;
			// フォント色がおかしくなることへの対処
			System.setProperty( "prism.lcdtext" , "false" );

			// FXMLファイルの読込
			root = (Pane)FXMLLoader.load(getClass().getResource("help.fxml"));

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

	/*public static HelpMain getInstance(){
		return singleton;
	}

	//画面遷移用メソッド、引数は遷移対象のfxml
	public void setPage(String fxml){
		try {
			root = (Pane)FXMLLoader.load(getClass().getResource(fxml));
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
