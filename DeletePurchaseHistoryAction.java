package com.internousdev.radish.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.radish.dao.PurchaseHistoryInfoDAO;
import com.internousdev.radish.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware{

	private	List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;

	private	Map<String,Object>session;
	public String execute() {
		//宣言、及び初期化
		//リザルトの初期値はerror
		String result = ERROR;
		//userId、tempLoginedに紐づいている情報をsessioｎから取得しuserIdへ代入
		String userId = String.valueOf(session.get("userId"));
		String tempLogined = String.valueOf(session.get("logined"));

		//処理
		//ログインフラグ判定((tempLoginedが1ならログイン状態)
		if(!"1".equals(tempLogined)) {
			return "loginError";
		}
		//インスタンス化
		PurchaseHistoryInfoDAO phdao = new PurchaseHistoryInfoDAO();
		//phdaoのdeleteAllメソッドを呼び出して実行された回数をint型のcountへ代入
		int count = phdao.deleteAll(userId);
		//(count > 0)がtrueであればリストを作成してからSuccessを返している。
		if(count > 0) {
			purchaseHistoryInfoDTOList = phdao.getPurchaseHistoryList(userId);
			result=SUCCESS;
		}
		return result;
	}

	public	List<PurchaseHistoryInfoDTO>getPurchaseHistoryInfoDTOList(){
		return purchaseHistoryInfoDTOList;
	}
	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO>purchaseHistoryInfoDTOList){
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}
	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session) {
		this.session = session;
	}
}
