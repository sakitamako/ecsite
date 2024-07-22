package com.diworksdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diworksdev.ecsite.dto.LoginDTO;
import com.diworksdev.ecsite.util.DBConnector;

//ログイン認証機能

//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class LoginDAO {

	//②DBConnectorのインスタンス化
	//DBへの接続準備、DBと会話するためのコード、これでログインできる
	//Connectionは特定のデータベースとの接続
	private DBConnector dbConnector = new DBConnector();

	//③getConnectionの呼び出し（DBと接続する）
	private Connection connection = dbConnector.getConnection();

	//LoginDTOインスタンス化
	//DTOと会話するためのコード
	private LoginDTO loginDTO = new LoginDTO();

	//①クラス、メソッドの定義
	//DTO型を最後に呼び出し元に渡すので、DTO型を戻り値にしたメソッドを作る
	//Actionクラスの値を引数として受け取る
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword) {

		//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため）
		//SELECT データを抽出する
		//＊ テーブルに含まれる項目全て
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		//WHERE ＜条件＞抽出条件を指定
		//login_user_transactionに入っているデータid=? pass=?に入る条件を満たしたデータがsqlに代入される
		String sql = "SELECT * FROM login_user_transaction where login_id=? AND login_pass=?";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			//セキュリティを考慮し、javaではPreparedStatementを利用
			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginPassword);

			//⑦executeQuery()/executeUpdate()で実行
			//sql文の値をセットしたものがresultsetに入ってる
			ResultSet resultSet = preparedStatement.executeQuery();

			//下に1行ずらすこと
			//データが存在していれば戻り値を true で返す。存在しなければ falseで返す
			if (resultSet.next()) {

				//もしresultsetに入っている値が存在していればDBから取得した情報をDTOに格納する
				loginDTO.setLoginId(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));

				//もしresultsetに入っている値(“login_id”) と nullが等しくない場合
				//DBに保管されているIDとresultsetに入っているIDが等しくない場合はtrue!
				if (resultSet.getString("login_id") != null) {

					//DTOにtrueを格納する
					loginDTO.setLoginFlg(true);

				}

			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (Exception e) {
			e.printStackTrace();

		}

		//dtoに入った値を呼び出し元であるアクションクラスに渡す
		return loginDTO;

	}

}
