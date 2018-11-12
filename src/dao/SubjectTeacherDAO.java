package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SubjectTeacherDAO {
	public static void insertSubject(String key) {
		final String dbConnect = "jdbc:sqlite:C:/tools/sqlite3/timetable.db";
		final String sql = "INSERT INTO teacher(teachername) VALUES(?);";
		Connection con = null;
		PreparedStatement prst = null;
		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection(dbConnect);
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
}
