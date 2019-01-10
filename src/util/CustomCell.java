package util;

import dto.Timetable;
import javafx.scene.control.TableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomCell extends TableCell<Timetable, String> {
	private Text classRoom;
	private Text subject;
	private VBox vbox;

	public CustomCell() {
		vbox = new VBox();
		classRoom = new Text();
		subject = new Text();
		vbox.getChildren().addAll(subject, classRoom);
		
	}

}
