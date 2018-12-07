package dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {
	private SimpleBooleanProperty teacherCheck;
	private SimpleIntegerProperty teacherId;
	private SimpleStringProperty teacherName;

	public Teacher(boolean check, int id, String name) {
		super();
		this.teacherCheck = new SimpleBooleanProperty(check);
		this.teacherId = new SimpleIntegerProperty(id);
		this.teacherName = new SimpleStringProperty(name);
	}
	public BooleanProperty teaCheckProperty() {
		return teacherCheck;
	}
	public Boolean getCheck() {
		return teacherCheck.get();
	}
	public void setCheck(Boolean teacherCheck) {
		this.teacherCheck.set(teacherCheck);
	}

	public IntegerProperty teaIdProperty() {
		return teacherId;
	}
	public Integer getTeacherId() {
		return teacherId.get();
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId.set(teacherId);
	}

	public StringProperty teaNameProperty() {
		return teacherName;
	}
	public String getTeacherName() {
		return teacherName.get();
	}
	public void setTeacherName(String teacherName) {
		this.teacherName.set(teacherName);
	}
}
