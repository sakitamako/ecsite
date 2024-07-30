package com.diworksdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//ユーザー登録機能

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//UserCreateConfirmAction（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
//実際の処理を持たない、ちょっと変わったクラス=implements
//interfaceを使って型宣言を行うことができますが、メソッドの定義がないとプログラムは実行できないので、そこで使うのがimplements
/*Actionクラスにて、implements SessionAware を宣言（ActionSupport.SessionAware=インターフェース）
実装メソッドである setSession(Map session)にて、ActionのフィールドへHttpSessionのオブジェクトを格納する処理を実装する。this.session = session; でほぼ十分。
上記の手順で実装したフィールドを用意する
これにより、このActionクラスのsessionフィールドへ、Struts2が自動的にHttpSessionの内容をMapの型で格納します。*/
public class UserCreateConfirmAction extends ActionSupport implements SessionAware {

	//フィールド変数
	//JSPから受け取る値
	//※必ずJSPでの定義と同じ名前にする
	private String loginUserId;
	private String loginPassword;
	private String userName;

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//このクラスのみ 変数 変数名
	//初期値、
	private String errorMessage;

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//メソッドの戻り値 String result = success; を定義し、初期値としてseccessを代入
		String result = SUCCESS;

		//int型などのプリミティブ型で２つの値が等しいか比較する場合は”==”演算子で比較しますがString型などの参照型の場合はequalsメソッドで比較する
		//loginUserIdと空文字・何も入ってない時の値が等しい場合、かつ、loginPasswordと空文字・何も入ってない時の値が等しい場合、かつ、userNameと空文字・何も入ってない時の値が等しい場合はelse文に行く！
		//! aとbとcがtrueの場合処理は実行しない
		//&& aとbとcが共にtrueの時に処理を実行する
		if (!(loginUserId.equals("")) && !(loginPassword.equals("")) && !(userName.equals(""))) {

			//空文字の部分に値が入っていたらsessionにそれぞれ記憶する
			session.put("loginUserId", loginUserId);
			session.put("loginPassword", loginPassword);
			session.put("userName", userName);

		} else {

			setErrorMessage("未入力の項目があります。");

			//elseの場合エラー
			result = ERROR;

		}

		//空文字の部分に値が入っていたら処理を実行する、サクセス！userCreateConfirm.jsp画面に遷移する
		return result;

	}

	//・getterは「フィールド値を返却する事だけ」
	//・setterは「値を更新する事だけ」

	//フィールド変数に対応したgetterとsetterを定義
	//userCreate.jspの値として受け取った、loginUserIdフィールドの値をuserCreateConfirm.jspに渡している
	public String getLoginUserId() {
		return loginUserId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のsessionフィールドに代入して格納
	public void setLoginUserId(String loginUserId) {

		//thisという変数を経由して、自分自身のインスタンスのメソッドを呼び出す
		this.loginUserId = loginUserId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//userCreate.jspの値として受け取ったloginPasswordフィールドの値をuserCreateConfirm.jspに渡している
	public String getLoginPassword() {
		return loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のloginPasswordフィールドに代入して格納
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//userCreate.jspの値として受け取ったuserNameフィールドの値をuserCreateConfirm.jspに渡している
	public String getUserName() {
		return userName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のuserNameフィールドに代入して格納
	public void setUserName(String userName) {
		this.userName = userName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のsessionフィールドに代入して格納
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//userCreate.jspの値として受け取ったerrorMessageフィールドの値をuserCreateConfirm.jspに渡している
	public String getErrorMessage() {
		return errorMessage;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のerrorMessageフィールドに代入して格納
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;

	}

}
