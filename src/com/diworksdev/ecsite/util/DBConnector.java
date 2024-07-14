package com.diworksdev.ecsite.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	//MySQL接続に必要な情報を設定
	//JDBC ドライバー名
	private static String driverName = "com.mysql.jdbc.Driver";
	//データベース接続 URL
	private static String url = "jdbc:mysql://localhost/ecsite";
	//データベース接続ユーザ名
	private static String user = "root";
	//データベース接続パスワード
	private static String password = "root";

	//全てのクラス 変数 変数名
	public Connection getConnection() {

		//接続オブジェクトの定義
		Connection con = null;

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			Class.forName(driverName);

			//ドライバーがロードされ使えるような状態にしている、覚える。
			//接続情報から自分のパソコンにインストールされているMySQLへ接続する準備が整う
			con = (Connection) DriverManager.getConnection(url, user, password);

		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返します
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返します
		} catch(SQLException e) {
			e.printStackTrace();

		}

		//Mysqlに接続できたか情報を渡す
		return con ;

	}

}
