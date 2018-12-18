package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ClassRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassRoomDAO {
	public static void insertDAO(String[] crtext){


		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:/tools/sqlite3/timetable.db");
			String sql = "INSERT INTO classroom(crname) VALUES(?);";
			for(String str : crtext) {
				str = str.replaceAll(" ", "");
				str = str.replaceAll("　", "");
				if(str.isEmpty()) {
					continue;
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, str);
				pstmt.executeUpdate();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("数字を指定してください");
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

	public static void updateDAO(int id, String name) {
		final String sql = "UPDATE classroom SET crname = ? where crid = ?;";
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection("jdbc:sqlite:C:/tools/sqlite3/timetable.db");
			prst = con.prepareStatement(sql);
			prst.setString(1, name);
			prst.setInt(2, id);
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

	public static ClassRoom deleteDAO(int key){
		ClassRoom result = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection("jdbc:sqlite:C:/tools/sqlite3/timetable.db");

			String sql = "DELETE FROM classroom where crid = ?;";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, key);

			int del = pstmt.executeUpdate();

			System.out.println(del + "件の削除");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("数字を指定してください");
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

		return result;
	}

	public static ObservableList<ClassRoom> selectDAO(){
		ObservableList<ClassRoom> result = FXCollections.observableArrayList(new ArrayList<ClassRoom>());

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:/tools/sqlite3/timetable.db");
			String sql = "SELECT * FROM classroom;";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()){
				boolean crcheck = false;
				int crid = rs.getInt("crid");
				String crname = rs.getString("crname");
				result.add(new ClassRoom(crcheck, crid,crname));
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

		return result;

	}
}


