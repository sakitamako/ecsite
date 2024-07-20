package com.diworksdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.diworksdev.ecsite.dto.MyPageDTO;
import com.diworksdev.ecsite.util.DBConnector;

public class MyPageDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	//DBから購入履歴を取得するためのメソッド
	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id, String user_master_id) throws SQLException {
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();

		//「LEFT JOIN」を用いて複数のテーブルを結合することによってユーザ情報と履歴情報を紐付けて一括して取得することができる
		String sql = "SELECT ubit.id, iit.item_name, ubit.total_price, ubit.total_count, ubit.pay,"
				+ "ubit.insert_date FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction "
				+ "iit ON ubit.item_transaction_id = iit.id WHERE ubit.item_transaction_id= ? "
				+ "AND ubit.user_master_id= ? ORDER BY insert_date DESC";

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MyPageDTO dto = new MyPageDTO();

				//取得した結果を1件ずつDTOに格納し、更にDTOをArrayListに格納している
				dto.setId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setTotalPrice(resultSet.getString("total_price"));
				dto.setTotalCount(resultSet.getString("total_count"));
				dto.setPayment(resultSet.getString("pay"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				myPageDTO.add(dto);

			}

		} catch(Exception e) {
			e.printStackTrace();

		} finally {
			connection.close();

		}

		return myPageDTO;

		//DBから購入履歴を削除するためのメソッド
		public int buyItemHistoryDelete (String item_transaction_id, String user_master_id) throws SQLException {

			String sql = "DELETE FROM user_buy_item_transaction WHERE item_transaction_id= ? AND user_master_id = ?";

			PreparedStatement preparedStatement;

			int result =0;

			try {

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, item_transaction_id);
				preparedStatement.setString(2, user_master_id);
				result = preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				connection.close();

			}

			//Actionクラスに削除した件数を返す
			return result;

		}
	}

}
