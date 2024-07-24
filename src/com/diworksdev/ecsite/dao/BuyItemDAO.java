package com.diworksdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diworksdev.ecsite.dto.BuyItemDTO;
import com.diworksdev.ecsite.util.DBConnector;

//ログイン認証機能

//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class BuyItemDAO {

	//②DBConnectorのインスタンス化
	//DBへの接続準備、DBと会話するためのコード、これでログインできる
	//Connectionは特定のデータベースとの接続
	private DBConnector dbConnector = new DBConnector();

	//③getConnectionの呼び出し（DBと接続する）
	private Connection connection = dbConnector.getConnection();

	//BuyItemDTOインスタンス化
	//DTOと会話するためのコード
	private BuyItemDTO buyItemDTO = new BuyItemDTO();

	//①クラス、メソッドの定義
	//DTO型を最後に呼び出し元に渡すので、DTO型を戻り値にしたメソッドを作る
	//Actionクラスの値を引数として受け取る
	public BuyItemDTO getBuyItemInfo() {

		//商品情報をすべて取得するSQL文を書く
		//④sql文を書く
		//SELECT item_info_transactionのデータを抽出する
		String sql = "SELECT id, item_name, item_price FROM item_info_transaction";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			ResultSet resultSet = preparedStatement.executeQuery();

			//もしsqlから取得したデータが存在していれば下に一行ずらすこと
			//データが存在していれば戻り値を true で返す。存在しなければ falseで返す
			if (resultSet.next()) {

				//DBから取得した情報をDTOクラスに格納
				//もしresultsetに入っている値が存在していればDTOに格納する
				buyItemDTO.setId(resultSet.getInt("id"));
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));

			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch(Exception e) {
			e.printStackTrace();

		}

		//ActionクラスにDTOクラスを返す
		return buyItemDTO;

	}

}
