package Fxml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeleteDCMain extends Application {


	    public static void main(String[] args) {

	        launch(args);
	    }


	    @Override
	    public void start(Stage primaryStage) throws Exception{
	        // フォント色がおかしくなることへの対処
	        System.setProperty( "prism.lcdtext" , "false" );

	        // FXMLファイルの読込
	        URL             location    = getClass().getResource( "DeleteCourseRoom.fxml" );
	        FXMLLoader      fxmlLoader  = new FXMLLoader( location );

	        // シーングラフの作成
	        Pane    root        = (Pane) fxmlLoader.load();

	        // シーンの作成
	        Scene   scene       = new Scene( root , 1240 , 700 );

	        // ウィンドウ表示
	        primaryStage.setScene( scene );
	        primaryStage.show();

	    }
}
