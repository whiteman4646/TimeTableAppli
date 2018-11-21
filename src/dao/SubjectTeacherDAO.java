package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Subject;
import dto.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class SubjectTeacherDAO {
	final static String DB_CONNECT = "jdbc:sqlite:C:/tools/sqlite3/timetable.db";

	//SQL:Teacherの参照メソッド
	public static ObservableList<Teacher> selectTeacher() {
		ObservableList<Teacher> tList = FXCollections.observableArrayList(new ArrayList<Teacher>());
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			String sql = "SELECT * FROM teacher";
			prst = con.prepareStatement(sql);
			rs = prst.executeQuery();

			while(rs.next() == true ){
				boolean check = false;
				int id = rs.getInt("teacherid");
				String name = rs.getString("teachername");
				tList.add(new Teacher(check, id, name));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBアクセスに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("値を指定してください");
		} finally {
			try{
				if( prst != null){
					prst.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}

		return tList;
	}

	//SQL:Teacherのインサートメソッド
	public static void insertTeacher(String key) {
		final String sql = "INSERT INTO teacher(teachername) VALUES(?);";
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			prst = con.prepareStatement(sql);
			prst.setString(1, key);
			prst.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBアクセスに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("値を指定してください");
		} finally {
			try{
				if( prst != null){
					prst.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	//SQL:Teacherのデリートメソッド
	public static void deleteTeacher(int key) {
		final String sql = "DELETE FROM teacher where teacherid = ?;";
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			prst = con.prepareStatement(sql);
			prst.setInt(1, key);
			prst.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBアクセスに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("値を指定してください");
		} finally {
			try{
				if( prst != null){
					prst.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}


	//SQL:Subjectの参照メソッド
	public static ObservableList<Subject> selectSubject() {
		ObservableList<Subject> sList = FXCollections.observableArrayList(new ArrayList<Subject>());
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			String sql = "SELECT * FROM subject";
			prst = con.prepareStatement(sql);
			rs = prst.executeQuery();

			while(rs.next() == true ){
				boolean check = false;
				int id = rs.getInt("subjectid");
				String name = rs.getString("subjectname");
				sList.add(new Subject(check, id, name));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBアクセスに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("値を指定してください");
		} finally {
			try{
				if( prst != null){
					prst.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}

		return sList;
	}

	//SQL:Subjectのインサートメソッド
	public static void insertSubject(String key) {
		final String sql = "INSERT INTO subject(subjectname) VALUES(?);";
		Connection con = null;
		PreparedStatement prst = null;
		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			prst = con.prepareStatement(sql);
			prst.setString(1, key);
			prst.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBアクセスに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("値を指定してください");
		} finally {
			try{
				if( prst != null){
					prst.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	//SQL:Subjectのデリートメソッド
	public static void deleteSubject(Integer key) {
		final String sql = "DELETE FROM subject where subjectid = ?;";
		Connection con = null;
		PreparedStatement prst = null;
		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			prst = con.prepareStatement(sql);
			prst.setInt(1, key);
			prst.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBアクセスに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("値を指定してください");
		} finally {
			try{
				if( prst != null){
					prst.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}
}
