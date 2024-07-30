package com.diworksdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.ecsite.dao.BuyItemDAO;
import com.diworksdev.ecsite.dao.LoginDAO;
import com.diworksdev.ecsite.dto.BuyItemDTO;
import com.diworksdev.ecsite.dto.LoginDTO;
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
public class LoginAction extends ActionSupport implements SessionAware {

	//フィールド変数
	//JSPから受け取る値
	//※必ずJSPでの定義と同じ名前にする
	private String loginUserId;
	private String loginPassword;

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//②LoginDAOとLoginDTOとbuyItemDAOのインスタンス化（コピーして代入）
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//変数・文字列 result=ERROR
		//初期値
		String result = ERROR;

		//JSPから送られてきた情報を引数として、
		//LoginDAOクラスのgetLoginUserInfoメソッドを呼び出す
		//その後、DAOで取得した結果をLoginDTOに代入する
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);

		//Map を使った場合には、put()で要素を記憶できる
		//sessionに記憶する ⇦ "loginUser"という文字列に入っているIdとかpassのこと、loginDTOのこと
		session.put("loginUser", loginDTO);

		//入力値からユーザー情報の検索を行います。
		//ログイン認証が成功した場合、次の画面で
		//「商品情報」が必要なため商品情報を取得します。
		//もしLoginDTOに記憶しているsessionのloginUserとgetLoginFlg()を取得した場合success
		if (((LoginDTO)session.get("loginUser")).getLoginFlg()) {

			//処理結果がサクセス！resultに代入
			result = SUCCESS;

			//buyItemDAOクラスのgetBuyItemInfoメソッドを呼び出す
			//その後、DAOで取得した結果をbuyItemDTOに代入する
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			//Map を使った場合には、put()で要素を記憶できる
			//sessionの中に記憶した要素をそれぞれのDTOファイルから取得する
			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

			//戻り値
			//resultに入った値を呼び出し元である上記resultに渡す
			return result;

		}

		//戻り値
		//初期値はERROR、処理結果がSUCCESSだったらbuyItem.jspに遷移する（struts.xmlの31行目、32行目）
		return result;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DTOから戻り値として受け取ったloginUserIdフィールドの値をbuyItem.jspに渡す
	public String getLoginUserId() {
		return loginUserId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のloginUserIdフィールドに代入して格納
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DTOから戻り値として受け取ったloginPasswordフィールドの値をbuyItem.jspに渡す
	public String getLoginPassword() {
		return loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のloginPasswordフィールドに代入して格納
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//全てのクラスのsetの値を自身のsessionフィールドに代入して格納
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
