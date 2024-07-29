package com.diworksdev.ecsite.dto;

//購入履歴機能トの作成

//DTOクラスは、DAOがDBから取得した値をActionへ戻す時、値を格納するのに利用されるファイル
public class MyPageDTO {

	//テーブルカラムに対応したフィールド変数を宣言
	//テーブルから取得するデータに対応したフィールド変数を宣言
	//このクラス・変数・変数名
	public String itemName;
	public String totalPrice;
	public String totalCount;
	public String payment;
	public String userName;
	public String id;
	public String insert_date;

	//フィールド変数に対応したgetterとsetterを定義
	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのitemNameを渡している*/
	//get は値を取得、set は登録
	public String getItemName() {
		return itemName;
	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO itemNameフィールドに格納
	public void setItemName(String itemName){
		this.itemName = itemName;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのtotalPriceを渡している*/
	public String getTotalPrice() {
		return totalPrice;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO totalPriceフィールドに格納
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのtotalCountを渡している*/
	public String getTotalCount() {
		return totalCount;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO totalCountフィールドに格納
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのpaymentを渡している*/
	public String getPayment() {
		return payment;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO paymentフィールドに格納
	public void setPayment(String payment) {
		this.payment = payment;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのuserNameを渡している*/
	public String getUserName() {
		return userName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO userNameフィールドに格納
	public void setUserName(String userName) {
		this.userName = userName;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのidを渡している*/
	public String getId() {
		return id;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO idフィールドに格納
	public void setId(String id) {
		this.id = id;

	}

	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのinsert_dateを渡している*/
	public String getInsert_date() {
		return insert_date;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO insert_dateフィールドに格納
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;

	}

}
