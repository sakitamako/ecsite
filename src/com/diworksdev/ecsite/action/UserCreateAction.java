package com.diworksdev.ecsite.action;

import com.opensymphony.xwork2.ActionSupport;

//ユーザー登録機能

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル
//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
public class UserCreateAction extends ActionSupport {

	//新規ユーザー登録はボタンを押したらサクセスが返される、userCreate.jsp画面に遷移する
	//struts.xmlで指定しているから！
	public String execute() {

		return SUCCESS;
	}

}
