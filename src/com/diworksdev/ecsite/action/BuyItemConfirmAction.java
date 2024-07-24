package com.diworksdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.ecsite.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

//商品購入機能

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
//実際の処理を持たない、ちょっと変わったクラス=implements
//interfaceを使って型宣言を行うことができますが、メソッドの定義がないとプログラムは実行できないので、そこで使うのがimplements
/*Actionクラスにて、implements SessionAware を宣言（ActionSupport.SessionAware=インターフェース）
実装メソッドである setSession(Map session)にて、ActionのフィールドへHttpSessionのオブジェクトを格納する処理を実装する。this.session = session; でほぼ十分。
上記の手順で実装したフィールドを用意する
これにより、このActionクラスのsessionフィールドへ、Struts2が自動的にHttpSessionの内容をMapの型で格納します。*/
//Java7までは実装は持てず、メソッドのシグニチャのみの定義
//interfaceを使って型宣言を行うことができますが、メソッドの定義がないとプログラムは実行できないので、そこで使うのがimplements
public class BuyItemConfirmAction extends ActionSupport implements SessionAware {

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//②DAOのインスタンス化
	private BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO();

	//全てのクラス 変数 変数名(struts) throws=例外を意図的に起こすことが出来る処理のこと。
	public String execute() throws SQLException {

		//sessionの中に記憶しているデータを取得してテキストで表す
		buyItemCompleteDAO.buyItemeInfo(
				session.get("id").toString(),
				session.get("total_price").toString(),
				session.get("count").toString(),
				session.get("login_user_id").toString(),
				session.get("pay").toString());

		//resultにSUCCESS代入
		String result = SUCCESS;

		//呼び出し元であるアクションクラスにresultを返す
		return result;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のsessionフィールドに格納
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;


	}

}
