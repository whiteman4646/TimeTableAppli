package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Timetable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimetableDAO {
	final static String DB_CONNECT = "jdbc:sqlite:C:/tools/sqlite3/timetable.db";

	//SQL:Teacherの参照メソッド
	public static ObservableList<Timetable> selectTeacher() {
		ObservableList<Timetable> tableList = FXCollections.observableArrayList(new ArrayList<Timetable>());
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			String sql = "SELECT * FROM timetable";
			prst = con.prepareStatement(sql);
			rs = prst.executeQuery();

			while(rs.next() == true ){
				int timetableid = rs.getInt("timetableid");
				String week = rs.getString("week");
				String time = rs.getString("time");
				String period = rs.getString("period");
				int dcid = rs.getInt("dcid");
				int teacherid = rs.getInt("teacherid");
				int subjectid = rs.getInt("subjectid");
				int crid = rs.getInt("crid");
				tableList.add(new Timetable(timetableid, week, time, period, dcid, teacherid, subjectid, crid));
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

		return tableList;
	}

	//SQL:Teacherのインサートメソッド
	public static void insertTeacher(String[][] key) {
		final String sql = "INSERT INTO Timetable(week, time, period, dcid, teacherid, subjectid, crid) VALUES(?, ?, ?, ?, ?, ?, ?);";
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			for(int i = 0;i < key.length; i++) {
				for(int j = 0; j < key.length; j++) {
					prst = con.prepareStatement(sql);
					prst.setString(1, key[i][j]);
					prst.setString(2, key[i][j]);
					prst.setString(3, key[i][j]);
					prst.setInt(4, Integer.parseInt(key[i][j]));
					prst.setInt(5, Integer.parseInt(key[i][j]));
					prst.setInt(6, Integer.parseInt(key[i][j]));
					prst.setInt(7, Integer.parseInt(key[i][j]));
					prst.executeUpdate();
				}
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
	}

	//SQL:Teacherの更新メソッド
	public static void updateTeacher(ArrayList<ArrayList<String>> key) {
		final String sql = "UPDATE Timetable SET week = ?, time = ?, period = ?, dcid = ?, teacherid = ?, subjectid = ?, crid = ? where timetableid = ?;";
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			for(int i = 0;i < key.size(); i++) {
				for(int j = 0; j < key.size(); j++) {
					prst = con.prepareStatement(sql);
					prst.setString(1, key.get(i).get(j));
					prst.setString(2, key.get(i).get(j));
					prst.setString(3, key.get(i).get(j));
					prst.setInt(4, Integer.parseInt(key.get(i).get(j)));
					prst.setInt(5, Integer.parseInt(key.get(i).get(j)));
					prst.setInt(6, Integer.parseInt(key.get(i).get(j)));
					prst.setInt(7, Integer.parseInt(key.get(i).get(j)));
					prst.setInt(8, Integer.parseInt(key.get(i).get(j)));
					prst.executeUpdate();
				}
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
	}
}
