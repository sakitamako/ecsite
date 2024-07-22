package com.diworksdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.ecsite.dao.BuyItemDAO;
import com.diworksdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

//ログイン認証機能

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
public class HomeAction extends ActionSupport implements SessionAware {

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//変数・文字列 result=login
		//初期値
		String result = "login";

		//ログイン済み判定を行います。
		//一度ログインしている場合はログイン認証画面に遷移させることなく、商品画面へ遷移させます。
		//もしsessionに特定のキーが辞書内に存在するかどうかを確認するために使用される、存在する場合にtrueを返します
		//containsKey=マップに特定の値が存在するかどうかを調べたい場合、 あるいはキーと値の両方について調べたい場合は exists メソッドを使用
		if (session.containsKey("login_user_id")) {

			//DAOとDTOのインスタンス化（コピーして代入）
			BuyItemDAO buyItemDAO = new BuyItemDAO();

			//DTOにインスタンス化したDAOのgetBuyItemInfoを代入
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			//DBから取得した商品情報を、sessionに格納、記憶、保存
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

			//「excecute」メソッドの戻り値として、ログイン状態の場合は「SUCCESS」
			result = SUCCESS;
		}

		//ログインしていない場合は「“login”」を返します。
		return result;
	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、sessionフィールドの値をActionに渡す
	public Map<String, Object> getSession() {

		//「this を使う場所 は フィールド変数名の 頭
		//フィールド変数の処理結果を返す
		return this.session;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のsessionフィールドに格納
	@Override
	public void setSession(Map<String, Object> session) {

		//「this を使う場所 は フィールド変数名の 頭
		//クラス内の変数=フィールド変数
		this.session = session;

	}

}
