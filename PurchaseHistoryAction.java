package com.internousdev.radish.action;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.radish.dao.PurchaseHistoryInfoDAO;
import com.internousdev.radish.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;
public class PurchaseHistoryAction extends ActionSupport implements SessionAware{

	private Map<String,Object>session;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;

	public String execute(){
		//sessionから取得したlogined(今回のケースでは1か0かnullが入ってくる)をtempLoginedへ代入
		String tempLogined = String.valueOf(session.get("logined"));
		//tempLoginedがnullだった場合0を違う場合はString型→int型へ変換してからloginedへ値を代入
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		//loginedが1でなければloginErrorを返す
		if(logined != 1) {
			return "loginError";
		}
		//リストを作成してsuccessを返している
		PurchaseHistoryInfoDAO phdao = new PurchaseHistoryInfoDAO();
		purchaseHistoryInfoDTOList = phdao.getPurchaseHistoryList(String.valueOf(session.get("userId")));
		return SUCCESS;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList(){
		return purchaseHistoryInfoDTOList;
	}
	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList){
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}
	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
