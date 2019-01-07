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

	//SQL:時間割の参照メソッド
	public static ObservableList<Timetable> selectTimetable() {
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

	public static ObservableList<Timetable> selectTimetableJoin() {
		ObservableList<Timetable> tableList = FXCollections.observableArrayList(new ArrayList<Timetable>());
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			String sql = "SELECT timetable.timetableid, timetable.week, timetable.time, timetable.period, departmentcourse.dcname, teacher.teachername, subject.subjectname, classroom.crname from timetable"
					+ " inner join departmentcourse on timetable.dcid =  departmentcourse.dcid"
					+ " inner join teacher on timetable.teacherid = teacher.teacherid"
					+ " inner join subject on timetable.subjectid = subject.subjectid"
					+ " inner join classroom on timetable.crid = classroom.crid;";

			prst = con.prepareStatement(sql);
			rs = prst.executeQuery();

			while(rs.next() == true ){

				String time = rs.getString("time");
				String teachername = rs.getString("teachername");
				String subjectname = rs.getString("subjectname");
				String crname = rs.getString("crname");
				tableList.add(new Timetable(teachername, time, subjectname, crname));
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

	public static ObservableList<Timetable> selectTimetableVew(String teachername, String week) {
		ObservableList<Timetable> tableList = FXCollections.observableArrayList(new ArrayList<Timetable>());
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			String sql = "SELECT teacher.teachername, timetable.time, subject.subjectname, classroom.crname"
					+ " from timetable"
					+ " inner join departmentcourse on timetable.dcid =  departmentcourse.dcid"
					+ " inner join teacher on timetable.teacherid = teacher.teacherid"
					+ " inner join subject on timetable.subjectid = subject.subjectid"
					+ " inner join classroom on timetable.crid = classroom.crid"
					+ " where teachername = ? and week = ?"
					+ " order by time asc;";
			prst = con.prepareStatement(sql);
			prst.setString(1, teachername);
			prst.setString(2, week);
			rs = prst.executeQuery();

			while(rs.next() == true ){
				String time = rs.getString("time");
				String teaname = rs.getString("teachername");
				String subjectname = rs.getString("subjectname");
				String crname = rs.getString("crname");
				tableList.add(new Timetable(time, teaname, subjectname, crname));
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

	//SQL:時間割のインサートメソッド
	public static void insertTimetable(String[][] key) {
		final String sql = "INSERT INTO Timetable(week, time, period, dcid, teacherid, subjectid, crid) VALUES(?, ?, ?, ?, ?, ?, ?);";
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			System.out.println("Connection!");
			for(int i = 0;i < key.length; i++) {
				if(key[i][4] == null || key[i][5] == null || key[i][6] == null) {
					continue;
				}
				prst = con.prepareStatement(sql);
				prst.setString(1, key[i][0]);
				prst.setString(2, key[i][1]);
				prst.setString(3, key[i][2]);
				prst.setInt(4, Integer.parseInt(key[i][3]));
				prst.setInt(5, Integer.parseInt(key[i][4]));
				prst.setInt(6, Integer.parseInt(key[i][5]));
				prst.setInt(7, Integer.parseInt(key[i][6]));
				prst.executeUpdate();

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

	//SQL:時間割の更新メソッド
	public static void updateTimetable(ArrayList<ArrayList<String>> key) {
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

	public static ObservableList<Timetable> allteacher() {
		ObservableList<Timetable> teaList = FXCollections.observableArrayList(new ArrayList<Timetable>());

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
				int teacherid = rs.getInt("teacherid");
				String teachername = rs.getString("teachername");
				teaList.add(new Timetable(teacherid, teachername));
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

		return teaList;

	}

	public static ObservableList<Timetable> allclassroom() {
		ObservableList<Timetable> clroList = FXCollections.observableArrayList(new ArrayList<Timetable>());

		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(DB_CONNECT);
			String sql = "SELECT * FROM classroom";
			prst = con.prepareStatement(sql);
			rs = prst.executeQuery();

			while(rs.next() == true ){
				int id = rs.getInt("crid");
				String name = rs.getString("crname");
				clroList.add(new Timetable(name, id));
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

		return clroList;

	}


}
