package com.internousdev.radish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.radish.dto.PurchaseHistoryInfoDTO;
import com.internousdev.radish.util.DBConnector;
public class PurchaseHistoryInfoDAO{
	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryList(String userId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<PurchaseHistoryInfoDTO> purchaseHistoryDTOList = new ArrayList<PurchaseHistoryInfoDTO>();

		String sql =
				//テーブルから必要な情報を選択
				"SELECT"
						+ " phi.id,"/* ID */
						+ " phi.user_id,"/* ユーザーID */
						+ " phi.product_count," /* 個数 */
						+ " phi.product_id,"/* 商品ID */
						+ " pi.product_name,"/*商品名*/
						+ " pi.product_name_kana,"/* 商品名かな */
						+ " pi.product_description,"/* 商品詳細 */
						+ " pi.category_id,"/* カテゴリID */
						+ " pi.image_file_name,"/* 画像ファイルパス */
						+ " pi.image_file_path,"/* 画像ファイル名 */
						+ " pi.release_company," /* 発売会社名 */
						+ " pi.release_date," /* 発売年月日 */
						+ " phi.price,"/* 値段 */
						//price * product_countの合計金額を変数名totalPriceへ置き換え
						+ " phi.price * phi.product_count as total_price,"/* 合計金額 */
						+ " phi.regist_date,"/* 登録日 */
						+ " di.family_name,"/* 姓 */
						+ " di.first_name,"/* 名 */
						+ " di.user_address"/* 住所 */
						//3つのテーブルを結合
						+ " FROM purchase_history_info phi"
						+ " LEFT JOIN product_info pi"
						+ " ON phi.product_id = pi.product_id"
						+ " LEFT JOIN destination_info di"
						+ " ON phi.destination_id = di.id"
						+ " WHERE phi.user_id = ?"
						+ " ORDER BY regist_date DESC";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userId);
			ResultSet rs = ps.executeQuery();

			//rs.nextで情報をとってきてリストへまとめている
			while(rs.next()){
				PurchaseHistoryInfoDTO	phdto = new PurchaseHistoryInfoDTO();
				phdto.setId(rs.getInt("id"));
				phdto.setUserId(rs.getString("user_id"));
				phdto.setProductCount(rs.getInt("product_count"));
				phdto.setProductId(rs.getInt("product_id"));
				phdto.setProductName(rs.getString("product_name"));
				phdto.setProductNameKana(rs.getString("product_name_kana"));
				phdto.setImageFileName(rs.getString("image_file_name"));
				phdto.setImageFilePath(rs.getString("image_file_path"));
				phdto.setReleaseCompany(rs.getString("release_company"));
				phdto.setReleaseDate(rs.getDate("release_date"));
				phdto.setPrice(rs.getInt("price"));
				phdto.setTotalPrice(rs.getLong("total_price"));
				phdto.setFamilyName(rs.getString("family_name"));
				phdto.setFirstName(rs.getString("first_name"));
				phdto.setUserAddress(rs.getString("user_address"));
				purchaseHistoryDTOList.add(phdto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return purchaseHistoryDTOList;
	}
	//registメソッドを使って5つの項目のデータをpurchase_history_infoへinsertしている
	public int regist(String userId, int productId, int productCount, int destinationId, int price){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "insert into purchase_history_info"
				+ " (user_id, product_id, product_count, price, destination_id, regist_date, update_date)"
				+ " values (?,?,?,?,?,now(),now())";
		int count = 0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setInt(2,productId);
			ps.setInt(3,productCount);
			ps.setInt(4,price);
			ps.setInt(5,destinationId);
			//メソッドが実行された回数をcountへ代入
			count = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException	e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int deleteAll(String userId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		//delete from テーブル名  where カラム名
		String sql = "delete from purchase_history_info where user_id=?";
		int count = 0;

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userId);
			//メソッドが実行された回数をcountへ代入
			count = ps.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
