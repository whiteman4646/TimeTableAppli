package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class sqlite_create_table {

	//ビルドパス→ソースのリンク→データベースのフォルダを指定すると
	//時下に接続できる jdbc:sqlite:timetable.db
	String dbName = "timetable.db";
	String url = "jdbc:sqlite:" + dbName;

	//データベースに接続する
	private Connection connect() {
		// SQLite connection string
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	//テーブルを削除する
	public void dropTable(String name) {
		try (Connection con = this.connect();
				Statement stmt = con.createStatement()) {
			stmt.executeUpdate("DROP TABLE IF EXISTS  " + name);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createNewTable(String create_sql) {

		Connection con = null;
		Statement stmt = null;


		try {

			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			stmt.execute(create_sql);

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}

	}

	public void SelectAll(String tableName) {

		Connection con = null;
		Statement stmt = null;


		try {
			String sql= "select * from "+tableName;

			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			stmt.execute(sql);

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}

	}


	public void insertTable(String sql,String crname) {
		PreparedStatement pstmt = null;
		Connection con =null;

		try {
			String insert_sql = "INSERT INTO classroom(crname) VALUES(?);";

			con = this.connect();
			pstmt = con.prepareStatement(insert_sql);
			pstmt.setString(1, crname);
			pstmt.executeUpdate();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}




	public ArrayList<ArrayList<String>> importCSV(String filename) {

		ArrayList<ArrayList<String>> oResult = new ArrayList<ArrayList<String>>();

		//ファイル読み込みで使用する３つのクラス
		String[] data = null;

		try {
			FileInputStream fi = null;
			InputStreamReader is = null;
			BufferedReader br = null;
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream(filename);
			is = new InputStreamReader(fi, "SJIS");
			br = new BufferedReader(is);

			//読み込み行
			String sLine;

			//読み込み行数の管理
			int i = 0;

			//列名を管理する為の配列
			String[] arr = null;

			//1行ずつ読み込みを行う
			while ((sLine = br.readLine()) != null) {

				String temp[] = sLine.split(",",-1);
				ArrayList al = new ArrayList();

				int colno = 0;
				int il = temp.length;

				//先頭行は列名
				if (i == 0) {
					//カンマで分割した内容を配列に格納する
					arr = sLine.split(",");
				} else {

					//データ内容をコンソールに表示する
				//	 System.out.println("-------------------------------");

					//データ件数を表示
				//	System.out.println("データ" + i + "件目");

					//カンマで分割した内容を配列に格納する

					while( colno< il ){
					//	System.out.println(arr[colno] + ":" + temp[colno]);
						al.add(temp[colno]);
						colno++;
					}
					oResult.add(al);
					//行数のインクリメント

				}
				//読み込み行数++
				i++;
			}
			br.close();
			is.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oResult;

	}

	public static void main(String[] args) {

		sqlite_create_table app = new sqlite_create_table();

		System.out.println("::::::::INSERT classroom DATA::::::::::::");
		//すでにあるテーブルを削除する
		app.dropTable("classroom");
		//DDL CREATE TABLE
		String ddl_classroom = "create table classroom(\n"
				+ "	crid INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "	crname  NOT NULL"
				+ ");";
		//テーブルを作成する
		app.createNewTable(ddl_classroom);
		//CSVデータを読み込み
		ArrayList<ArrayList<String>>  classroom = app.importCSV("database/Classroom.csv");
		//ArrayList を　SQLのデータとしてINSERT
		for (ArrayList<String> list : classroom) {
			for(String data : list  ) {
			System.out.println(data);
			String sql = "INSERT INTO classroom(crname) VALUES(?);";
			app.insertTable(sql,data);
			}
		}
		//表示させる
		app.SelectAll("classroom");





		System.out.println("::::::::INSERT department DATA::::::::::::");
		app.dropTable("departmentcourse");
		String ddl_departmentcourse = "create table departmentcourse(\n"
				+ "	dcid INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "	dcname TEXT NOT NULL"
				+ ");";

		app.createNewTable(ddl_departmentcourse);
		ArrayList<ArrayList<String>>  department = app.importCSV("database/Department.csv");
		for (ArrayList<String> list : department) {
			for(String data : list  ) {
			System.out.println(data);
			String sql = "INSERT INTO departmentcourse(dcname) VALUES(?);";
			app.insertTable(sql,data);
			}
		}
		app.SelectAll("departmentcourse");




		System.out.println("::::::::INSERT teacher DATA::::::::::::");
		app.dropTable("teacher");
		String ddl_teacher = "create table teacher(\n"
				+ "	teacherid INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "	teachername TEXT NOT NULL"
				+ ");";
		app.createNewTable(ddl_teacher);
		ArrayList<ArrayList<String>>  teacher = app.importCSV("database/Teacher.csv");
		for (ArrayList<String> list : teacher) {
			for(String data : list  ) {
			System.out.println(data);
			String sql = "INSERT INTO teacher(teachername) VALUES(?);";
			app.insertTable(sql,data);
			}
		}
		app.SelectAll("departmentcourse");




		System.out.println("::::::::INSERT subject DATA::::::::::::");
		app.dropTable("subject");
		String ddl_subject = "create table subject(\n"
				+ "	subjectid INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "	teachername TEXT NOT NULL"
				+ ");";
		app.createNewTable(ddl_subject);
		ArrayList<ArrayList<String>>  subject = app.importCSV("database/Subject.csv");
		for (ArrayList<String> list : subject) {
			for(String data : list  ) {
			System.out.println(data);
			String sql = "INSERT INTO subject(subjectname) VALUES(?);";
			app.insertTable(sql,data);
			}
		}
		app.SelectAll("subject");




	}

	}
