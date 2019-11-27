package com.internousdev.radish.dto;

import java.util.Date;

public class PurchaseHistoryInfoDTO {

	//共通
	private int id;
	private int productId;
	private String userId;

	//商品情報テーブル
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private String releaseCompany;
	private Date releaseDate;

	//購入履歴情報テーブル
	private int productCount;
	private int price;

	//宛先情報テーブル
	private String familyName;
	private String firstName;
	private String userAddress;

	//price * productCount
	private Long totalPrice;

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getProductId(){
		return productId;
	}
	public void setProductId(int productId){
		this.productId = productId;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	public String getProductNameKana(){
		return productNameKana;
	}
	public void setProductNameKana(String productNameKana){
		this.productNameKana = productNameKana;
	}
	public String getImageFilePath(){
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath){
		this.imageFilePath = imageFilePath;
	}
	public String getImageFileName(){
		return imageFileName;
	}
	public void setImageFileName(String imageFileName){
		this.imageFileName = imageFileName;
	}
	public Date getReleaseDate(){
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate){
		this.releaseDate = releaseDate;
	}
	public String getReleaseCompany(){
		return releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany){
		this.releaseCompany = releaseCompany;
	}
	public int getProductCount(){
		return productCount;
	}
	public void setProductCount(int	productCount){
		this.productCount = productCount;
	}
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public String getFamilyName(){
		return	familyName;
	}
	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getUserAddress(){
		return userAddress;
	}
	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}
	public long getTotalPrice(){
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice){
		this.totalPrice = totalPrice;
	}
}
