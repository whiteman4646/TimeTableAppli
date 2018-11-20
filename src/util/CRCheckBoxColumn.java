package util;

import dto.ClassRoom;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CRCheckBoxColumn extends TableColumn<ClassRoom, Boolean> {
	public CRCheckBoxColumn() {
        // DepartmentCourseのcheckedプロパティと紐づける
        this.setCellValueFactory(new PropertyValueFactory<>("crcheck"));

        this.setCellFactory(column -> {

            // CheckBoxTableCellの挙動を定義する
            CheckBoxTableCell<ClassRoom, Boolean> cell = new CheckBoxTableCell<ClassRoom, Boolean>(index -> {
                BooleanProperty selected = new SimpleBooleanProperty(
                        this.getTableView().getItems().get(index).isCrcheck());

                selected.addListener((ov, o, n) -> {
                    // チェックボックスの状態が変わったらPersonのデータも更新する
                    this.getTableView().getItems().get(index).setCrcheck(n);

                    // チェックボックスが押されたので、行を選択したことにする
                    this.getTableView().getSelectionModel().select(index);

                    // テーブルにクリックイベントを飛ばす
                    Event.fireEvent(column.getTableView(), new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,
                            MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
                });

                return selected;
            });

            return cell;
        });
    }

}
