<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset = "UTF-8">
	<link rel = "stylesheet" href = "./css/radish.css">
	<link rel = "stylesheet" href = "./css/table.css">
	<title>商品購入履歴画面</title>

</head>
<body>
	<div id = "header">
	<!-- include、他で作ったものを使いまわす際に使用ここではheaderを挿入している -->
	<jsp:include page="header.jsp" />
	</div>
	<div id = "contents">
		<div id = "top">
			<h1>商品購入履歴画面</h1>
		</div>

		<s:if test = "purchaseHistoryInfoDTOList!=null && purchaseHistoryInfoDTOList.size()>0">
			<table class = "buyTable">
			<thead>
				<tr>
					<th><s:label value = "商品名"/></th>
					<th><s:label value = "ふりがな"/></th>
					<th><s:label value = "商品画像"/></th>
					<th><s:label value = "発売会社名"/></th>
					<th><s:label value = "発売年月日"/></th>
					<th><s:label value = "値段"/></th>
					<th><s:label value = "個数"/></th>
					<th><s:label value = "合計金額"/></th>
					<th><s:label value = "宛先名前"/></th>
					<th><s:label value = "宛先住所"/></th>
				</tr>
			</thead>
				<tbody>
					<s:iterator value = "purchaseHistoryInfoDTOList">
						<tr>
							<td><s:property value = "productName"/></td>
							<td><s:property value = "productNameKana"/></td>
							<td><img src = '<s:property value = "imageFilePath"/>/<s:property value = "imageFileName"/>' width="50px" height="50px"/></td>
							<td><s:property value = "releaseCompany"/></td>
							<td><s:property value = "releaseDate"/></td>
							<td><s:property value = "price"/>円</td>
							<td><s:property value = "productCount"/>個</td>
							<td><s:property value = "totalPrice"/>円</td>
							<td><s:property value = "familyName"/><span>　</span><s:property value="firstName"/></td>
							<td><s:property value = "userAddress"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

			<div class = "submitBtnBox">
				<s:form	action = "DeletePurchaseHistoryAction">
					<s:submit value="履歴削除" class = "submitBtn" />
				</s:form>
			</div>
		</s:if>

		<s:else>
			<div class  ="info">
				商品購入履歴情報がありません。
			</div>
		</s:else>
	</div>
</body>
</html>