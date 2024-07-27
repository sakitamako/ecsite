package com.diworksdev.ecsite.dto;

//ログイン認証機能
//DTOクラスは、DAOがDBから取得した値をActionへ戻す時、値を格納するのに利用されるファイル
public class BuyItemDTO {

	//テーブルから取得するデータに対応したフィールド変数を宣言
	//このクラス・変数・変数名
	public int id;
	public String itemName;
	public String itemPrice;

	//・getterは「フィールド値を返却する事だけ」
	//・setterは「値を更新する事だけ」

	//フィールド変数に対応したgetterとsetterを定義
	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのtemNameを渡している*/
	//get は値を取得、set は登録
	public String getItemName() {
		return itemName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO itemNameフィールドに格納
	public void setItemName(String itemName) {
		this.itemName = itemName;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのtemPriceを渡している*/
	public String getItemPrice() {
		return itemPrice;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO itemPriceフィールドに格納
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのIdを渡している*/
	public int getId() {
		return id;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO idフィールドに格納
	public void setId(int id) {
		this.id = id;

	}


}
