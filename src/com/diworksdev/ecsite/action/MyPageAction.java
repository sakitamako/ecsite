package com.diworksdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.ecsite.dao.MyPageDAO;
import com.diworksdev.ecsite.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

//購入履歴機能トの作成

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
public class MyPageAction extends ActionSupport implements SessionAware {

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//②DTOとDAOのインスタンス化（コピーして値を代入）
	//Listインタフェースのサイズ変更可能な配列の実装です。
	//リストのオプションの操作をすべて実装し、nullを含むすべての要素を許容します。
	//このクラスは、Listインタフェースを実装するほか、リストを格納するために内部的に使われる配列のサイズを操作するメソッドを提供します
	private MyPageDAO myPageDAO = new MyPageDAO();

	//ArrayList とは、 Listインタフェース を実装した コレクションクラス である。
	//ArrayList は、 Array という名にあるように配列のような感覚で扱うことができる。
	//配列 には格納できる 要素数が決まっている が、 ArrayList は 要素数は決まっていない 。
	//ArrayList は、 プリミティブ型（int, booleanなど） を入れられない。
	private ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();

	//フィールド変数
	//JSPから受け取る値
	//※必ずJSPでの定義と同じ名前にする
	//このクラスのみ 変数 変数名
	private String deleteFlg;
	private String message;

	//全てのクラス 変数 変数名(struts) throws=例外を意図的に起こすことが出来る処理のこと。
	public String execute() throws SQLException {

		//もしsessionにuser_idが記憶されていたらエラーを表示する
		//! trueの場合処理は実行しない
		if (! session.containsKey("login_user_id")) {

			return ERROR;

		}

		//履歴の削除がされているか否か、チェックをしています。
		//もしdeleteFlgとnullが等しい場合
		if (deleteFlg == null) {

			//sessionに記憶しているIDとuser_idを取得してテキストで表示する
			//item_transaction_idとuser_master_idはDBに問い合わせて受け取ったデータ
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();

			//DBから取得した履歴情報を、「myPageList」に格納しています
			myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);

		//そうでない場合、もしdeleteFlgと１が等しい場合
		} else if (deleteFlg.equals("1")) {

			//「delete」メソッドを呼び出して、履歴の削除処理を行います。
			delete();

		}

		//SUCCESSをresultに代入して
		String result = SUCCESS;

		//resultに入った値を呼び出し元であるActionクラスに渡す
		return result;

	}

	//全てのクラス 変数 変数名(struts) throws=例外を意図的に起こすことが出来る処理のこと。
	public void delete() throws SQLException {

		//履歴の削除を行うためのメソッドです。
		//item_transaction_idとuser_master_idはDBに問い合わせて受け取ったデータ
		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		//DBから削除した履歴情報の件数を、DAOが呼び出して「res」に格納しています。
		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id, user_master_id);

		//1件以上削除されたか否かで正常に削除処理がされたか判別しています。
		//もしresが0より大きい場合
		if (res > 0) {

			//trueだった時削除する
			myPageList = null;
			setMessage("商品情報を正しく削除しました。");

		//そうでない場合もしresと0が等しい場合falseで削除できない
		} else if (res == 0) {
			setMessage("商品情報の削除に失敗しました。");

		}

	}

	//外部のSETをここに代入して元々の値を外部から持ってきた値に変えて格納する
	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のdeleteFlgフィールドに格納
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;

	}

	//外部のSETをここに代入して元々の値を外部から持ってきた値に変えて格納する
	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のsessionフィールドに格納
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	//外部からここにアクセスして、外部にデータを渡している
	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、myPageListフィールドの値をActionに渡す
	public ArrayList<MyPageDTO> getMyPageList() {
		return this.myPageList;

	}

	//外部からここにアクセスして、外部にデータを渡している
	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、messageフィールドの値をActionに渡す
	public String getMessage() {
		return this.message;

	}

	//外部のSETをここに代入して元々の値を外部から持ってきた値に変えて格納する
	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のmessageフィールドに格納
	public void setMessage(String message) {
		this.message = message;

	}

}
