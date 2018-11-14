package Fxml;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dto.ClassRoom;
import dto.DepartmentCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DelDCController implements Initializable {

	ObservableList<DepartmentCourse> data1;//=FXCollections.observableArrayList();
	ObservableList<ClassRoom> data2;//=FXCollections.observableArrayList();

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@FXML
	private TableView<DepartmentCourse> dctable;
	@FXML
	private TableView<ClassRoom> crtable;
	@FXML
	private TableColumn<?, ?> dcnameColumn;
	@FXML
	private TableColumn<?, ?> crnameColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		dcnameColumn.setCellValueFactory(new PropertyValueFactory<>("dcname"));
		crnameColumn.setCellValueFactory(new PropertyValueFactory<>("crname"));
		loadDatabaseData();
	}
	private void loadDatabaseData(){

		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:/tools/sqlite3/timetable.db");
			data1 = FXCollections.observableArrayList();
			String sql = "SELECT * FROM departmentcourse;";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()){
				data1.add(new DepartmentCourse(rs.getInt("dcid"),
						rs.getString("dcname")));
				dctable.setItems(data1);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("数字を指定してください");
			e.printStackTrace();
		} finally {
			try{
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}

		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:/tools/sqlite3/timetable.db");
			data2 = FXCollections.observableArrayList();
			String sql = "SELECT * FROM classroom;";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()){
				data2.add(new ClassRoom(rs.getInt("crid"),
						rs.getString("crname")));
				crtable.setItems(data2);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("数字を指定してください");
			e.printStackTrace();
		} finally {
			try{
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}


	}
		//DepartmetCourseDAO.selectDAO();
		//ClassRoomDAO.selectDAO();



}

