package com.diworksdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.ecsite.dao.BuyItemDAO;
import com.diworksdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {

	public Map<String, Object> session;

	public String execute() {

		String result = "login";

		//ログイン済み判定を行います。
		//一度ログインしている場合はログイン認証画面に遷移させることなく、商品画面へ遷移させます。
		if (session.containsKey("login_user_id")) {

			BuyItemDAO buyItemDAO = new BuyItemDAO();
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			//DBから取得した商品情報を、sessionに格納
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price ", buyItemDTO.getItemPrice());

			result = SUCCESS;
		}

		//「excecute」メソッドの戻り値として、ログイン状態の場合は「SUCCESS」
		//ログインしていない場合は「“login”」を返します。
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public Map<String, Object> getSession() {
		return this.session;

	}

}
