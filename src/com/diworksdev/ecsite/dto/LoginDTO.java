package com.diworksdev.ecsite.dto;

//ログイン認証機能

//DTOクラスは、DAOがDBから取得した値をActionへ戻す時、値を格納するのに利用されるファイル
public class LoginDTO {

	//テーブルから取得するデータに対応したフィールド変数（クラス内の 変数）を宣言
	//このクラス・変数・変数名
	private String loginId;
	private String loginPassword;
	private String userName;

	//・getterは「フィールド値を返却する事だけ」
	//・setterは「値を更新する事だけ」

	//このクラスのみ  trueかfalseのデータが必ず入ることが決まっているデータ型
	//boolean型を使う主な場面は、if文やfor文などの制御文での条件分岐のとき
	//値のみを取れる論理データ型 変数名＝false
	private boolean loginFlg = false;

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、loginIdフィールドの値をActionに渡す
	//get は値を取得、set は登録
	/*privateフィールドにアクセスしてgetを呼び出して、その戻り値としてDTOクラスのloginIdを渡している*/
	public String getLoginId() {
		return loginId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO loginIdフィールドに格納
	//LoginDAOの64行目
	public void setLoginId(String loginId) {
		this.loginId = loginId;

	}

	//Actionクラスから呼び出され、loginPasswordフィールドの値をActionに渡す
	public String getLoginPassword() {
		return loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO loginPasswordフィールドに格納
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;

	}

	//Actionクラスから呼び出され、userNameフィールドの値をActionに渡す
	public String getUserName() {
		return userName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO userNameフィールドに格納
	public void setUserName(String userName) {
		this.userName = userName;

	}

	//Actionクラスから呼び出され、loginFlgフィールドの値をActionに渡す
	public boolean getLoginFlg() {
		return loginFlg;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO loginFlgフィールドに格納
	public void setLoginFlg(boolean loginFlg) {
		this.loginFlg = loginFlg;

	}

}
